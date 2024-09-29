import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Game {
    private static Frame frame;//窗体
    private static Canvas canvas;//画布
    private static Graphics pen;//画笔
    private static Game game;
    private Board board;
    private Chess chess;

    private void drawBoard(Graphics pen) {
        board.drawBoard(pen);
    }

    private void drawChess(Graphics pen, Corrdinate c,Color color) {
        board.drawChess(pen,c,color);
    }

    public static void main(String[] args) {
        int rows = 20, cols = 20;
        int size = 30, margin = 20;
        int width = size * (cols - 1) + 2 * margin;
        int height = size * (rows - 1) + 2 * margin;
        Game game = new Game(rows, cols, size, margin);
        frame = new Frame("我的五子棋");
        frame.setSize(width, height + 150);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);//显示窗体
        canvas = new Canvas();
        canvas.setBackground(Color.darkGray);
        canvas.setSize(width, height);
        frame.add(canvas);
        Corrdinate c = new Corrdinate();

        Button button = new Button("Start");
        button.setSize(100, 50);
        frame.add(button);
        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pen = canvas.getGraphics();
                game.drawBoard(pen);
            }
        });
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                byte m=game.handle(e.getX(),e.getY());
                if(m==1)
                {
                    int option = JOptionPane.showConfirmDialog(null, "黑方获胜！是否继续？", "五子棋",JOptionPane.YES_NO_OPTION);
                    System.out.println("黑方获胜！");

                }
                else if (m==-1)
                {
                    int option = JOptionPane.showConfirmDialog(null, "白方获胜！是否继续？", "五子棋",JOptionPane.YES_NO_OPTION);
                    System.out.println("白方获胜");
                }
            }
        });

    }

    public Game(int rows, int cols, int size, int margin) {
        chess = new Chess(rows, cols);
        board = new Board(margin, size, rows, cols);
    }

    /**
     * 处理用户的落子指令
     *
     * @param x
     * @param y
     * @return 落子后的胜负结果
     */
    public  byte handle(int x, int y) {
        Corrdinate c = board.convert(x, y);
        System.out.println(c.row+" "+c.col);
        if (chess.exist(c)) {
            return Chess.NONE;
        }
        chess.add(c);
        Color color = chess.getTurn()==Chess.BLACK?Color.BLACK:Color.WHITE;
        board.drawChess(pen,c,color);
        byte res = chess.win(c);
        if (res == Chess.NONE) {
            chess.changeTurn();
        }
        return chess.win(c);
    }
}



