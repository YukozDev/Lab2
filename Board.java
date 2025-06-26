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
