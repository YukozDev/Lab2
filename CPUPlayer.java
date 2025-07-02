import java.util.*;

public class CPUPlayer {
<<<<<<< HEAD
    PawnColor player;
    PawnColor opponent;
=======
        private int maxDepth;
        private int nodesEvaluated;

    /**
     * Returns a list of the best moves found by minimax for the current player.
     * @param board The current board state
     * @param isBlackTurn True if it's black's turn, false for red
     * @return ArrayList of best moves
     */
    public ArrayList<Move> getBestMoves(Board board, boolean isBlackTurn) {
        nodesEvaluated = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = isBlackTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<Move> moves = new Move().getValidMoves(board);

        for (Move move : moves) {
            Board newBoard = board.cloneWithMove(move); // You need to implement this method in Board
            int score = minimax(newBoard, maxDepth - 1, !isBlackTurn);
            if ((isBlackTurn && score > bestScore) || (!isBlackTurn && score < bestScore)) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
    }

    
    
    /**
     * Minimax algorithm for evaluating board states.
     */
    private int minimax(Board board, int depth, boolean isBlackTurn) {
        nodesEvaluated++; // Count this node
        int eval = evaluate(board);
        if (depth == 0 || eval == 1000 || eval == -1000 || eval == 0 && (isTerminal(board))) {
            return eval;
        }

        List<Move> moves = new Move().getValidMoves(board);
        if (moves.isEmpty()) {
            return evaluate(board);
        }

        int bestScore = isBlackTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (Move move : moves) {
            Board newBoard = board.cloneWithMove(move); // You need to implement this method in Board
            int score = minimax(newBoard, depth - 1, !isBlackTurn);
            if (isBlackTurn) {
                bestScore = Math.max(bestScore, score);
            } else {
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }

   

    /**
     * Helper to check if the board is in a terminal state (no moves for either player).
     */
    private boolean isTerminal(Board board) {
        List<Move> moves = new Move().getValidMoves(board);
        return moves.isEmpty();
    }

       
        
        public CPUPlayer(int depth) {
            this.maxDepth = depth;
        }

    /**
     * Evaluates the board and returns a score if the game is over.
     * Returns 1000 if black wins, -1000 if red wins, 0 for draw or not over.
     */
    public int evaluate(Board board) {
        int[][] boardState = board.getBoard();
        boolean hasBlack = false, hasRed = false;

        // Check for red piece at row 1 (top, index 0) and black at row 8 (bottom, index 7)
        for (int x = 0; x < 8; x++) {
            if (boardState[0][x] == 3 || boardState[0][x] == 4) {
                return -1000; // Red wins
            }
            if (boardState[7][x] == 1 || boardState[7][x] == 2) {
                return 1000; // Black wins
            }
        }

        // Check if either player has no pieces left
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int piece = boardState[y][x];
                if (piece == 1 || piece == 2) hasBlack = true;
                if (piece == 3 || piece == 4) hasRed = true;
            }
        }
        if (!hasBlack && hasRed) return -1000; // Red wins
        if (!hasRed && hasBlack) return 1000;  // Black wins
        if (!hasRed && !hasBlack) return 0;    // Draw

        // Game not over
        return 0;
    }





     

>>>>>>> 67adf45b8be18311777c7863f434ca1a5a0c7c7b

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
