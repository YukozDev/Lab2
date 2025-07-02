import java.util.*;

public class CPUPlayer {
    PawnColor player;
    PawnColor opponent;

    public CPUPlayer(PawnColor player) {
        this.player = player;
        this.opponent = player != PawnColor.RED ? PawnColor.BLACK : PawnColor.RED;
    }
    /*
        ArrayList<Move> nextBestMove = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move m : board.getAvailableMoves()) {
            board.play(m, cpuMark);
            int val = miniMax(board, 0, false);
            board.undoMove(m);

            if (val > bestScore) {
                bestScore = val;
                nextBestMove.clear();
                nextBestMove.add(m);

            } else if (val == bestScore) {
                nextBestMove.add(m);
            }
        }

        return nextBestMove;
     */

    public String getBestMove(Board board, int depth) {
        int bestScore = Integer.MIN_VALUE;
        String bestMv = null;
        Move move = board.getMove();
        List<String> moves = move.getAllMoves(player);

        for (String mv : moves) {
            Board copy = new Board(board); // MAUVAIS
            copy.updateBoard(mv);

            int score = minimax(copy, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

            if (score > bestScore) {
                bestScore = score;
                bestMv  = mv;
            }
        }
        return bestMv;
    }

    private int minimax(Board b, int depth, int alpha, int beta, boolean maximizing) {
        if (depth == 0) {
            return evaluate(b);
        }

        if (maximizing) {
            int maxEval = Integer.MIN_VALUE;

            for (String mv : b.getMove().getAllMoves(player)) {
                Board c = new Board(b); // MAUVAIS
                c.updateBoard(mv);

                int eval = minimax(c, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha   = Math.max(alpha, eval);

                if (beta <= alpha) 
                    break;
            }
            return maxEval;
        } 
        
        else {
            int minEval = Integer.MAX_VALUE;
            for (String mv : b.getMove().getAllMoves(opponent)) {
                Board c = new Board(b);
                c.updateBoard(mv);

                int eval = minimax(c, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);

                beta = Math.min(beta, eval);

                if (beta <= alpha) 
                    break;
            }
            return minEval;
        }
    }

    // TODO EVALUATE COMPLETEMENT DEBILE
    private int evaluate(Board b) {
        int score = 0;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int p = b.getPawn(x, y);

                if (p == PawnType.RED_BIG.ordinal())   
                    score += 100;

                if (p == PawnType.RED_SMALL.ordinal()) 
                    score += 50;

                if (p == PawnType.BLACK_BIG.ordinal()) 
                    score -= 100;

                if (p == PawnType.BLACK_SMALL.ordinal()) 
                    score -= 50;
            }
        }

        // si CPU est noir, on inverse le signe
        return (player == PawnColor.RED) ? score : -score;
    }

}
