import java.util.*;

public class Move {
    private Board board;

    public Move(Board board) {
        this.board = board;
    }

    /**
     * Permet de verifier si le prochain deplacement sur le plateau est valide
     */
    public boolean isValideMove(int x, int y, int nextX, int nextY) {

        int currentPawnType = board.getPawn(x, y);
        int target = board.getPawn(nextX, nextY);

        if(target == -1)
            return false;

        //check small pawn move
        if(currentPawnType == PawnType.BLACK_SMALL.ordinal() || currentPawnType == PawnType.RED_SMALL.ordinal()) {
            //Check if behind a small pawn we have a big pawn
            //Straight - BLACK
            if(board.getPawn(x, y + 1) == PawnType.BLACK_BIG.ordinal() && currentPawnType == PawnType.BLACK_SMALL.ordinal()) {
                //Maximum avance 1 case
                if(target == PawnType.NONE.ordinal() && nextX - x == 0 && nextY - y == -1)
                    return true;
            }

            //Straight - RED
            else if(board.getPawn(x, y - 1) == PawnType.RED_BIG.ordinal() && currentPawnType == PawnType.RED_SMALL.ordinal()) {
                //Maximum avance 1 case                
                if(target == PawnType.NONE.ordinal() && nextX - x == 0 && nextY - y == 1)
                    return true;
            }

            //Diago Gauche - BLACK
            if(board.getPawn(x - 1, y + 1) == PawnType.BLACK_BIG.ordinal() && currentPawnType == PawnType.BLACK_SMALL.ordinal()) {
                //Maximum avance 1 case
                if(nextX - x != -1 && nextY - y != -1)
                    return false;

                if(target == PawnType.NONE.ordinal() || target == PawnType.RED_BIG.ordinal() || target == PawnType.RED_SMALL.ordinal())
                    return true;
            }

            //Diago Gauche - RED
            else if(board.getPawn(x - 1, y - 1) == PawnType.RED_BIG.ordinal() && currentPawnType == PawnType.RED_SMALL.ordinal()) {
                //Maximum avance 1 case
                if(nextX - x != -1 && nextY - y != 1)
                    return false;

                if(target == PawnType.NONE.ordinal() || target == PawnType.BLACK_BIG.ordinal() || target == PawnType.BLACK_SMALL.ordinal())
                    return true;    
            }

            //Diago Droite - BLACK
            if(board.getPawn(x + 1, y + 1) == PawnType.BLACK_BIG.ordinal() && currentPawnType == PawnType.BLACK_SMALL.ordinal()) {
                //Maximum avance 1 case
                if(nextX - x != 1 && nextY - y != -1)
                    return false;

                if(target == PawnType.NONE.ordinal() || target == PawnType.RED_BIG.ordinal() || target == PawnType.RED_SMALL.ordinal())
                    return true;          
            }

            //Diago Droite - RED
            else if(board.getPawn(x + 1, y - 1) == PawnType.RED_BIG.ordinal() && currentPawnType == PawnType.RED_SMALL.ordinal()) {
                //Maximum avance 1 case
                if(nextX - x != 1 && nextY - y != 1)
                    return false;

                if(target == PawnType.NONE.ordinal() || target == PawnType.BLACK_BIG.ordinal() || target == PawnType.BLACK_SMALL.ordinal())
                    return true;                    
            }
        }

        //check big pawn move
        if(currentPawnType == PawnType.BLACK_BIG.ordinal() || currentPawnType == PawnType.RED_BIG.ordinal()) {

            if(target == PawnType.NONE.ordinal()) {
                // Diago left | Straight | Diago right
                if(currentPawnType == PawnType.BLACK_BIG.ordinal()) {
                    if((nextX - x == -1 && nextY - y == -1) || (nextX - x == 0 && nextY - y == -1) || (nextX - x == 1 && nextY - y == -1))
                        return true;
                }
                else {
                    if((nextX - x == -1 && nextY - y == 1) || (nextX - x == 0 && nextY - y == 1) || (nextX - x == 1 && nextY - y == 1))
                        return true;                    
                }
            }

            // Capture RED
            else if(currentPawnType == PawnType.RED_BIG.ordinal() && (target == PawnType.BLACK_BIG.ordinal() || target == PawnType.BLACK_SMALL.ordinal())) {
                // Diago Gauche | Diago Droite
                if((nextX - x == -1 && nextY - y == 1) || (nextX - x == 1 && nextY - y == 1))
                    return true;
            }

            else if(currentPawnType == PawnType.BLACK_BIG.ordinal() && (target == PawnType.RED_BIG.ordinal() || target == PawnType.RED_SMALL.ordinal())) {
                // Diago Gauche | Diago Droite
                if((nextX - x == -1 && nextY - y == -1) || (nextX - x == 1 && nextY - y == -1))
                    return true;
            }
        }

        return false;
    }

    /**
     * Stocke tous les mouvements possible.
     */
    public List<String> getAllMoves(PawnColor playerColor) {
        List<String> moves = new ArrayList<>();
        int count = 0;
        String color = (playerColor == PawnColor.RED) ? "RED" : "BLACK";

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {

                if(PawnType.NONE.ordinal() == board.getPawn(x, y))
                    continue;

                if(playerColor == PawnColor.RED) {
                    // Straight
                    if(isValideMove(x, y, x, y + 1)) {
                        moves.add(formatMove(x, y, x, y + 1));
                        count++;
                        System.out.println(formatMove(x, y, x, y + 1));
                    }

                    // Diago left
                    if(isValideMove(x, y, x - 1, y + 1)) {
                        moves.add(formatMove(x, y, x - 1, y + 1));
                        count++;
                        System.out.println(formatMove(x, y, x - 1, y + 1));
                    }

                    // Diago right
                    if(isValideMove(x, y, x + 1, y + 1)) {
                        moves.add(formatMove(x, y, x + 1, y + 1));
                        count++;
                        System.out.println(formatMove(x, y, x - 1, y + 1));
                    }
                }

                else {
                    // Straight
                    if(isValideMove(x, y, x, y - 1)) {
                        moves.add(formatMove(x, y, x, y - 1));
                        count++;
                        System.out.println(formatMove(x, y, x, y - 1));
                    }

                    // Diago left
                    if(isValideMove(x, y, x - 1, y - 1)) {
                        moves.add(formatMove(x, y, x - 1, y - 1));
                        count++;
                        System.out.println(formatMove(x, y, x - 1, y - 1));
                    }

                    // Diago right
                    if(isValideMove(x, y, x + 1, y - 1)) {
                        moves.add(formatMove(x, y, x + 1, y - 1));
                        count++;
                        System.out.println(formatMove(x, y, x + 1, y - 1));
                    }
                }
            }
        }
        System.out.println("MV " + color + " TOTAL : " + count);
        return moves;
    }

    private String formatMove(int x, int y, int nextX, int nextY) {
        return String.format("%c%d - %c%d", (char)('A' + x), y + 1, (char)('A' + nextX), nextY + 1);
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