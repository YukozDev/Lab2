import java.util.*;

public class Move {

    /**
     * Returns the move as a string in the format D6-D5 (fromXfromY-toXtoY)
     */
    
    int y;
    char x;
    int toY;
    char toX;
    int pieceType;

    /*
        1 Petit pion noir
        2 Pion noir
        3 Petit pion rouge
        4 Pion rouge
     */
    public void movePawn(Board board) {

    }

    @Override
    public String toString() {
        return "" + x + y + "-" + toX + toY;
    }

    public boolean isValidMove(Board board, int fromY, char fromX, int toY, char toX) {
        int[][] boardState = board.getBoard();
        int fromRow = 8 - fromY;
        int fromCol = fromX - 'A';
        int toRow = 8 - toY;
        int toCol = toX - 'A';

        int piece = boardState[fromRow][fromCol];
        int target = boardState[toRow][toCol];

        // No piece to move or moving to same spot
        if (piece == 0 || (fromRow == toRow && fromCol == toCol)) return false;

        // Determine direction: black moves down (+1), red moves up (-1)
        int dir = 0;
        boolean isBig = false, isSmall = false, isBlack = false, isRed = false;
        if (piece == 2) { dir = 1; isBig = true; isBlack = true; }
        else if (piece == 4) { dir = -1; isBig = true; isRed = true; }
        else if (piece == 1) { dir = 1; isSmall = true; isBlack = true; }
        else if (piece == 3) { dir = -1; isSmall = true; isRed = true; }
        else return false;

        // Only allow forward moves
        if (toRow - fromRow != dir) return false;
        if (Math.abs(toCol - fromCol) > 1) return false;

        // Big piece logic
        if (isBig) {
            // Straight move (no capture)
            if (toCol == fromCol && target == 0) return true;
            // Diagonal move (capture)
            if (Math.abs(toCol - fromCol) == 1 && target != 0) {
                // Black captures red, red captures black
                if (isBlack && (target == 3 || target == 4)) return true;
                if (isRed && (target == 1 || target == 2)) return true;
            }
            // Diagonal move (no capture)
            if (Math.abs(toCol - fromCol) == 1 && target == 0) return true;
            return false;
        }

        // Small piece logic: can only move if pushed by big piece directly behind
        if (isSmall) {
            int behindRow = fromRow - dir; // behind is opposite to movement direction
            if (behindRow < 0 || behindRow > 7) return false;
            int behindPiece = boardState[behindRow][fromCol];
            if (isBlack && behindPiece != 2) return false;
            if (isRed && behindPiece != 4) return false;
            // Can only move forward or diagonally forward, and only if target is empty or (if diagonal) is opponent
            if (toCol == fromCol && target == 0) return true;
            if (Math.abs(toCol - fromCol) == 1) {
                if (target == 0) return true;
                if (isBlack && (target == 3 || target == 4)) return true;
                if (isRed && (target == 1 || target == 2)) return true;
            }
            return false;
        }

        return false;
}

    public List<Move> getValidMoves(Board board) {
        List<Move> validMoves = new ArrayList<>();
        int[][] boardState = board.getBoard();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int piece = boardState[y][x];
                if (piece == 0) continue; // No piece here

                char fromX = (char) (x + 'A');
                int fromY = 8 - y;

                // Try all possible forward moves (straight, diag left, diag right)
                int[] dx = {-1, 0, 1};
                int dir = (piece == 1 || piece == 2) ? 1 : -1;
                for (int i = 0; i < 3; i++) {
                    int ny = y + dir;
                    int nx = x + dx[i];
                    if (ny >= 0 && ny < 8 && nx >= 0 && nx < 8) {
                        char toX = (char) (nx + 'A');
                        int toY = 8 - ny;
                        if (isValidMove(board, fromY, fromX, toY, toX)) {
                            Move move = new Move();
                            move.x = fromX;
                            move.y = fromY;
                            move.toX = toX;
                            move.toY = toY;
                            move.pieceType = piece;
                            validMoves.add(move);
                        }
                    }
                }
            }
        }

        return validMoves;
    }



}