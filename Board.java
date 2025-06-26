import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private int[][] board;
    private Move move;
    private List<String> test = new ArrayList<>();

    public Board() {
        this.board = new int[8][8];
        move = new Move(this);
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

    public ArrayList<Move> getValidMoves(Board board) {
        ArrayList<Move> validMoves = new ArrayList<>();
        Move move = new Move();
        for(int x = 0; x < board.getBoard().length; x++) {
            for (int y = 0; y < board.getBoard()[x].length; y++){

              if(isRed(x,y)){  
                if(isPusher(x,y)){
                    if(isEmpty(x+1,y+1)){
                        move.x = (char) (x + 1 + 'A'); // Convert to char for column
                        move.y = y + 1; // Convert to 1-based index for row
                        move.pieceType = board.getBoard()[x][y]; // Assuming piece type is stored in the board
                        validMoves.add(move);
                    }
                    if(isEmpty(x,y+1)){
                        move.x = (char) (x + 'A'); // Convert to char for column
                        move.y = y + 1; // Convert to 1-based index for row
                        move.pieceType = board.getBoard()[x][y]; // Assuming piece type is stored in the board
                        validMoves.add(move);
                    }
                    if(isEmpty(x-1,y+1)){
                        move.x = (char) (x - 1 + 'A'); // Convert to char for column
                        move.y = y + 1; // Convert to 1-based index for row
                        move.pieceType = board.getBoard()[x][y]; // Assuming piece type is stored in the board
                        validMoves.add(move);
                    }
                    if(isPushed(x, y+1)){
                        if(isEmpty(x,y+2)){
                            move.x = (char) (x + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x][y+1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                        if(isEmpty(x-1,y+2)){
                            move.x = (char) (x - 1 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x][y+1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                        if(isEmpty(x+1,y+2)){
                            move.x = (char) (x + 1 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x][y+1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                    }
                    if(isPushed(x-1,y+1)){
                        if(isEmpty(x-2,y+2)){
                            move.x = (char) (x - 2 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x-1][y+1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                    }
                    if(isPushed(x+1,y+1)){
                        if(isEmpty(x+2,y+2)){
                            move.x = (char) (x + 2 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x+1][y+1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                    }

                }
            }
            if(isBlack(x,y)){  
                if(isPusher(x,y)){
                    if(isEmpty(x-1,y-1)){
                        move.x = (char) (x - 1 + 'A'); // Convert to char for column
                        move.y = y + 1; // Convert to 1-based index for row
                        move.pieceType = board.getBoard()[x][y]; // Assuming piece type is stored in the board
                        validMoves.add(move);
                    }
                    if(isEmpty(x,y-1)){
                        move.x = (char) (x + 'A'); // Convert to char for column
                        move.y = y - 1; // Convert to 1-based index for row
                        move.pieceType = board.getBoard()[x][y]; // Assuming piece type is stored in the board
                        validMoves.add(move);
                    }
                    if(isEmpty(x+1,y-1)){
                        move.x = (char) (x + 1 + 'A'); // Convert to char for column
                        move.y = y - 1; // Convert to 1-based index for row
                        move.pieceType = board.getBoard()[x][y]; // Assuming piece type is stored in the board
                        validMoves.add(move);
                    }
                    if(isPushed(x, y - 1)){
                        if(isEmpty(x,y - 2)){
                            move.x = (char) (x + 'A'); // Convert to char for column
                            move.y = y - 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x][y-1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                        if(isEmpty(x-1,y-2)){
                            move.x = (char) (x - 1 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x][y-1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                        if(isEmpty(x+1,y-2)){
                            move.x = (char) (x + 1 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x][y-1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                    }
                    if(isPushed(x-1,y-1)){
                        if(isEmpty(x-2,y-2)){
                            move.x = (char) (x - 2 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x-1][y-1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                    }
                    if(isPushed(x+1,y-1)){
                        if(isEmpty(x+2,y-2)){
                            move.x = (char) (x + 2 + 'A'); // Convert to char for column
                            move.y = y + 2; // Convert to 1-based index for row
                            move.pieceType = board.getBoard()[x+1][y-1]; // Assuming piece type is stored in the board
                            validMoves.add(move);
                        }
                    }

                }
            }



            }
        }

        
       
        return validMoves;
        }
    
    public boolean isPusher(int x, int y) {
        // Check if the piece at (x, y) is a pusher
        return board[x][y] == 2 || board[x][y] == 4; // Assuming 1 and 2 are pusher piece types
    }
    public boolean isPushed(int x, int y) {
        // Check if the piece at (x, y) is pushed
        return board[x][y] == 1 || board[x][y] == 3; // Assuming 3 and 4 are pushed piece types
    }
    public boolean isRed(int x, int y) {
        // Check if the piece at (x, y) is red
        return board[x][y] == 3 || board[x][y] == 4; // Assuming 3 and 4 are red piece types
    }
    public boolean isBlack(int x, int y) {
        // Check if the piece at (x, y) is black
        return board[x][y] == 1 || board[x][y] == 2; // Assuming 1 and 2 are black piece types
    }
    public boolean isEmpty(int x, int y) {
        // Check if the piece at (x, y) is empty
        if (x < 0 || x >= board.length) {
            return false; // Out of bounds
        }
        return board[x][y] == 0; // Assuming 0 represents an empty square
    }



        

}
