import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] board;
    private Move move;

    public Board() {
        this.board = new int[8][8];
        move = new Move(this);
    }

    //Constructeur par copie profonde
    public Board(Board other) {
        this.board = new int[8][8];
        move = other.getMove();
        
        for(int i = 0; i < 8; i++) {
            System.arraycopy(other.getBoard()[i], 0, this.board[i], 0, 8);
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public Move getMove() {
        return move;
    }

    public int getPawn(int x, int y) {
        if(x >= 8 || x < 0 || y >= 8 || y < 0)
            return -1;

        return board[x][y];
    }

    public void showBoard() {
        for (int y = 7; y >= 0; y--) {
            System.out.print((y+1) + " |");
            for (int x = 0; x < 8; x++) {
                System.out.print(board[x][y] + " ");
            }
            System.out.println();
        }

        System.out.println("  ------------------");
        System.out.print("   ");
        for (int x = 0; x < 8; x++) {
            System.out.print((char)('A' + x) + " ");
        }

        System.out.println("");
    }

    /*
     * Permet de mettre a jour le tableau lorsque le Joueur adverse a jouer.
    */ 
    public void updateBoard(String move) {
        System.out.println("Last Move (Adversaire) : " + move);
        String[] data = move.trim().split(" ");

        int oldX = data[0].charAt(0) - 65; // A en ascii c'est 65
        int oldY = data[0].charAt(1) - 49; // 1 en ascii c'est 49

        int newX = data[2].charAt(0) - 65; // A en ascii c'est 65
        int newY = data[2].charAt(1) - 49; // 1 en ascii c'est 49

        if(this.move.isValideMove(oldX, oldY, newX, newY)) {
            board[newX][newY] = getPawn(oldX, oldY);
            board[oldX][oldY] = 0;

            System.out.println("Tableau UPDATED | oldX : " + oldX + " oldY : " + oldY + " newX : " + newX + " newY : " + newY);
            showBoard();
        }
        else {
            System.out.println("INVALIDE BY iSVALIDE MOVE");
        }
    }
}
