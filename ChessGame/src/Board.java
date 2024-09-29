import java.awt.*;

class Board {
    private int margin;
    private int size;
    private int row;
    private int col;

    public Board(int margin, int size, int row, int col){
        this.margin = margin;
        this.size = size;
        this.row = row;
        this.col = col;
    }
    public  Corrdinate convert(int x,int y)
    {
        Corrdinate c=new Corrdinate();
        c.row = (x-margin+size/2)/size;
        c.col = (y-margin+size/2)/size;
        return c;
    }

    public void drawBoard(Graphics pen) {
        pen.setColor(Color.red);
        int x1=margin;
        int y1=margin;
        int x2=x1;
        int y2=y1+size*(row-1);
        for (int i = 0; i < row; i++) {
            pen.drawLine(x1, y1, x2, y2);
            pen.drawLine(y1, x1, y2, x2);
            x1+=size;
            x2+=size;

        }
    }

    public  void drawChess(Graphics pen,Corrdinate c,Color color){
        pen.setColor(color);
        int r=size/2;
        int x=margin+size*(c.row)-r/2;
        int y=margin+size*(c.col)-r/2;
        pen.fillOval(x,y,r,r);
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void setMargin(int margin) {
        this.margin = margin;
    }
}
