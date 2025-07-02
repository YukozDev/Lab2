import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {

    

    private int[][] board;
<<<<<<< HEAD
    private Move move;
=======
    private ArrayList<Move> valid = new ArrayList<>();
    private Move move;

    
    public ArrayList<Move> getValid() {
        return valid;
    }

    /**
     * Initializes the board to the standard starting position.
     * 1 = small black, 2 = big black, 3 = small red, 4 = big red, 0 = empty
     */
    public void initializeBoard() {
        // Clear board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        // Place black pieces (top two rows)
        for (int j = 0; j < 8; j++) {
            board[0][j] = 2; // big black
            board[1][j] = 1; // small black
        }
        // Place red pieces (bottom two rows)
        for (int j = 0; j < 8; j++) {
            board[6][j] = 3; // small red
            board[7][j] = 4; // big red
        }
    }



    /**
     * Returns a deep copy of this board with the given move applied.
     */
    public Board cloneWithMove(Move move) {
        Board newBoard = new Board();
        int[][] current = this.getBoard();
        int[][] copy = new int[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(current[i], 0, copy[i], 0, 8);
        }
        // Remove piece from original position
        int fromRow = 8 - move.y;
        int fromCol = move.x - 'A';
        int toRow = 8 - move.toY;
        int toCol = move.toX - 'A';
        copy[toRow][toCol] = copy[fromRow][fromCol];
        copy[fromRow][fromCol] = 0;
        newBoard.board = copy;
        return newBoard;
    }

    
>>>>>>> 67adf45b8be18311777c7863f434ca1a5a0c7c7b

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
<<<<<<< HEAD
        System.out.println("Last Move (Adversaire) : " + move);
        String[] data = move.trim().split(" ");
=======
        valid = (ArrayList<Move>) new Move().getValidMoves(this);
        System.out.println("DATA RECU : " + move);
        String[] t = move.trim().split(" ");
        System.out.println(t[0]);
>>>>>>> 67adf45b8be18311777c7863f434ca1a5a0c7c7b

        int oldX = data[0].charAt(0) - 65; // A en ascii c'est 65
        int oldY = data[0].charAt(1) - 49; // 1 en ascii c'est 49

<<<<<<< HEAD
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
=======
    



        

>>>>>>> 67adf45b8be18311777c7863f434ca1a5a0c7c7b
}
