import java.util.*;

public class Move {
    private Board board;

    public Move(Board board) {
        this.board = board;
    }


    /*
 * 0 Case vide
 * 1 Petit pion rouge
 * 2 Pion rouge
 * 3 Petit pion noir
 * 4 Pion noir
 */


    /**
     * Permet de verifier si le prochain deplacement sur le plateau est valide
     */
    public boolean isValideMove(int x, int y, int nextX, int nextY) {

        int currentPawnType = board.getPawn(x, y);
        int target = board.getPawn(nextX, nextY);
        boolean smallRed = false; 
        boolean bigRed = false;
        boolean smallBlack = false;
        boolean bigBlack = false;
        boolean hasRedPusher = false;
        boolean hasBlackPusher = false;
        boolean redStartingPosition = false;
        boolean blackStartingPosition = false;

        if(currentPawnType == 0){
            return false;
        }

        if(currentPawnType == 1){
            smallRed = true;
        }
        if(currentPawnType == 2){
            bigRed = true;
        }
        if(currentPawnType == 3){
            smallBlack = true;
        }
        if(currentPawnType == 4){
            bigBlack = true;
        }


        //Déterminer si les pions sont dans leur position de départ
        if (smallRed == true && y == 1){
            redStartingPosition = true;
        }
         if (smallBlack == true && y == 7){
            blackStartingPosition = true;
        }


        //Pion rouge sans pusher
        if (smallRed == true && board.getPawn(x - 1, y -1) != 2 && board.getPawn(x + 1, y -1) != 2 && board.getPawn(x, y - 1) != 2) {
            return false;
        } else {
            hasRedPusher = true;

        }
        //Pion noir sans pusher
        if (smallBlack == true && board.getPawn(x - 1, y + 1) != 4 && board.getPawn(x + 1, y + 1) != 4 && board.getPawn(x, y + 1) != 4) {
            return false;
        } else {
            hasBlackPusher = true;
        }
        //Petit Rouge tente une capture non diagonale
        if(smallRed == true && hasRedPusher == true && (target == 3 || target == 4) && nextX == x && nextY == y + 1) {
            return false;
            }
        //Petit Noir tente une capture non diagonale
        if(smallBlack == true && hasBlackPusher == true && (target == 1 || target == 2) && nextX == x && nextY == y - 1) {
            return false;
            }
        //Grand Rouge tente une capture non diagonale
         if(bigRed == true && (target == 3 || target == 4) && nextX == x && nextY == y + 1) {
            return false;
            }
        //Grand Noir tente une capture non diagonale                
            if(bigBlack == true && (target == 1 || target == 2) && nextX == x && nextY == y - 1) {
            return false;
            }
        //Petit rouge a un pusher mais n'est pas dans sa position de départ et tente un mouvement ne correspondant pas à la position de son pusher
        if(redStartingPosition == false && smallRed == true && hasRedPusher == true && board.getPawn(x,y-1) == 2 && nextX != x){
            return false;
        }
         if(redStartingPosition == false && smallRed == true && hasRedPusher == true && board.getPawn(x-1,y-1) == 2 && nextX != x+1){
            return false;
        }
         if(redStartingPosition == false && smallRed == true && hasRedPusher == true && board.getPawn(x+1,y-1) == 2 && nextX != x-1){
            return false;
        }
        //Petit noir a un pusher mais n'est pas dans sa position de départ et tente un mouvement ne correspondant pas à la position de son pusher
        if(blackStartingPosition == false && smallBlack == true && hasBlackPusher == true && board.getPawn(x,y+1) == 2 && nextX != x){
            return false;
        }
         if(blackStartingPosition == false && smallBlack == true && hasBlackPusher == true && board.getPawn(x-1,y+1) == 2 && nextX != x+1){
            return false;
        }
         if(blackStartingPosition == false && smallBlack == true && hasBlackPusher == true && board.getPawn(x+1,y+1) == 2 && nextX != x-1){
            return false;
        }
        
        //N'importe quel déplacement de plus de 1 case    
        if(Math.abs(nextX - x) > 1 || Math.abs(nextY - y) > 1){
            return false;
        }

        
        



    return true;
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