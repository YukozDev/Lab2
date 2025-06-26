import java.util.*;
public class Move {

    int y;// 1-8
    char x;// A-H
    int pieceType;// 0 = empty, 1 = petitPionNoir, 2 = grandPionNoir, 3 = petitPionRouge, 4 = grandPionRouge
    Board board = Client.board; // Assuming Client.board is accessible

    public Move() {
        this.y = 0;
        this.x = 'A';
        this.pieceType = 0; // Default to empty
    }

    


    
}