import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] board;
    private List<String> test = new ArrayList<>();

    public Board() {
        this.board = new int[8][8];
    }

    public int[][] getBoard() {
        return board;
    }

    public int getPown(int x, int y) {
        return board[x][y];
    }

    public void showBoard() {
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y <board.length; y++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println("");
        }
    }

    /*
     * Permet de mettre a jour le tableau lorsque le Joueur adverse a jouer.
    */ 
    public void updateBoard(String move) {
        System.out.println("Tableau Avant Update");
        showBoard();
        System.out.println("Last Move (Adversaire) : " + move);
        String[] data = move.trim().split(" ");

        int oldX = data[0].charAt(0) - 65; // A en ascii c'est 65
        int oldY = data[0].charAt(1) - 49; // 1 en ascii c'est 49

        int newX = data[2].charAt(0) - 65; // A en ascii c'est 65
        int newY = data[2].charAt(1) - 49; // 1 en ascii c'est 49

        System.out.println("oldX : " + oldX + " oldY : " + oldY + " newX : " + newX + " newY : " + newY);

        board[newX][newY] = getPown(oldX, oldY);
        board[oldX][oldY] = 0;
        showBoard();
    }
}
