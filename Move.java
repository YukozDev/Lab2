import java.util.*;

public class Move {
    private Board board;
    int y;
    char x;
    int pieceType;    

    public Move(Board board) {
        this.board = board;
    }
    /*
        1 Petit pion noir
        2 Pion noir
        3 Petit pion rouge
        4 Pion rouge
     */
    public void movePawn(Board board) {

    }

    isValidMove(int x, int y, int nextX, int nextY){
        // Check if the move is within bounds
        if (nextX < 0 || nextX >= board.getSize() || nextY < 0 || nextY >= board.getSize()) {
            return false;
        }

        // Check if the target cell is empty
        if (board.getPown(nextX,nextY) != 0) {
            return false;
        }
        if(board.getPown(x,y) == 3 && board.getPown(x-1,y-1) != 4 && board.getPown(x+1,y-1) != 4 && board.getPown(x,y-1) != 4){
            return false;
        }
        if(board.getPown(x,y) == 1 && board.getPown(x-1,y+1) == 0 && board.getPown(x+1,y+1) == 0 && board.getPown(x,y+1) == 0){
            return false;
        }
        

        // Additional rules can be added here, such as checking for valid piece movement
        return true;
    }
}