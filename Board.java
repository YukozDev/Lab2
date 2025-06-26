import java.util.ArrayList;

public class Board {

    private int[][] board;

    public Board(int[][] board) {
        this.board = new int[8][8];
    }
    
    public int evaluate(){

        return 0; // Placeholder for evaluation logic
    }

    public int miniMax(int depth, boolean isMaximizingPlayer) {
        // Placeholder for minimax logic
        return 0;
    }

    public int miniMaxAlphaBeta(int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        // Placeholder for minimax with alpha-beta pruning logic
        return 0;
    }

    public ArrayList<Moves> miniMaxMoves() {
        ArrayList<Moves> bestPossibleMoves = new ArrayList<>();


        return bestPossibleMoves;
    }

    public ArrayList<Moves> ABMoves() {
        ArrayList<Moves> bestPossibleMoves = new ArrayList<>();


        return bestPossibleMoves;
    }

    public ArrayList<Moves> validMoves() {
        ArrayList<Moves> possibleMoves = new ArrayList<>();


        return possibleMoves;
    }
}
