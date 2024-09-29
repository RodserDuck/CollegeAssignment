class Chess {
    private int row;
    private int col;
    public final static byte BLACK = 1;
    public final static byte WHITE = -1;
    public final static byte NONE=0;
    private byte turn;//轮到谁下，1黑；-1白
    private byte [][]cheese;//每个落点的状态：0无；1黑；-1白


    /**
     *
     * @param row 行数
     * @param col 列数
     */
    public Chess(int row, int col) {
        this.row = row;
        this.col = col;
        cheese = new byte[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cheese[i][j] = NONE;
            }
        }
        turn = BLACK;
    }
    public boolean exist(Corrdinate c) {
        if(cheese[c.row][c.col] == NONE)
            return false;
        else
            return true;
    }

    /**
     *
     * @param c 最后落子的位置
     */
    public byte win(Corrdinate c){
        if(count(c,0,1)+count(c,0,-1)==4)
        {
            return turn;
        }
        if(count(c,1,-1)+count(c,-1,1)==4)
        {
            return turn;
        }
        if(count(c,1,0)+count(c,-1,0)==4)
        {
            return turn;
        }
        if(count(c,1,1)+count(c,-1,-1)==4)
        {
            return turn;
        }
        return NONE;
    }

    /**
     *
     * @param c 落子位置
     */
    public void add(Corrdinate c){
        cheese[c.row][c.col] = turn;
    }

    public void changeTurn(){
        if(turn == BLACK)
            turn = WHITE;
        else
            turn = BLACK;
    }

    public byte getTurn(){
        return turn;
    }

    /**
     * 从某个位置出发，按照某个方向数相同颜色的个数
     * @param c 指定位置
     * @param offX X轴的变化，0-不变；1-递增；-1-递减
     * @param offY Y轴的变化，0-不变；1-递增；-1-递减
     * @return 同颜色的个数
     */
    private int count(Corrdinate c,int offX,int offY){
        int num = 0;
        int row = c.row;
        int col = c.col;
        while(true) {
            row += offY;
            col += offX;
            if (row >= cheese.length || row < 0 || col >= cheese[0].length || col < 0) {
                break;
            }
            if (cheese[row][col] != cheese[c.row][c.col]){
                break;
            }
            num++;
        }
        return num;
    }
}