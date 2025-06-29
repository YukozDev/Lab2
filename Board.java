import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {

    

    private int[][] board;
    private List<String> test = new ArrayList<>();


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

    

    public Board() {
        this.board = new int[8][8];
    }

    public int[][] getBoard() {
        return board;
    }

    public void showBoard() {
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y <board.length; y++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println("");
        }
    }

    public void updateBoard(String move) {
        System.out.println("DATA RECU : " + move);
        String[] t = move.trim().split(" ");
        System.out.println(t[0]);

        showBoard();
    }

    



        

}
