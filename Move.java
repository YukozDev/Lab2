import java.util.*;
public class Move {

    int y;
    char x;
    int pieceType;

    public Move(char x, char x2, int y, int y2, int pieceType) {
        this.x = x;
        this.y = y;
        this.pieceType = pieceType;

        System.out.println(pieceType + " " + x + y + "-" + x2 + y2);
        
    }


    
}