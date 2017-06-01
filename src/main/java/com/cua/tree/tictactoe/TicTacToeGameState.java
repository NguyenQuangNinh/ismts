package com.cua.tree.tictactoe;

import com.cua.tree.GameState;
import com.cua.tree.Move;

import java.util.ArrayList;

/**
 * Created by nguyenquangninh on 5/29/17.
 */
public class TicTacToeGameState extends GameState<TicTacToeMove> {
    public static final int FREE       = 0;
    public static final int PLAYER_X   = 1; // X
    public static final int PLAYER_O   = 2; // O

    public static final int DRAW   = 100; // O

    public static final int GRID_SIZE  = 7;
    public static final int WIN_SIZE  = 5;

    /** The grid */
    private final int[][] grid;
    private int turn = 0;
    private int winner = FREE; //No winner;

    public TicTacToeGameState(int[][] testGrid, int turn, int player) {
        super(2);
        this.grid = testGrid;
        this.turn = turn;
        this.playerToMove = player;
    }

    public TicTacToeGameState() {
        super(2);
        this.grid = new int[GRID_SIZE][GRID_SIZE];
        newGame();
    }

    public void newGame() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = FREE;
            }
        }
        // X start to play
        playerToMove = PLAYER_X;
        turn = 0;
        winner = FREE; // No winner
    }

    @Override
    public boolean isTerminal() {
        return winner != FREE;
    }

    @Override
    public GameState clone() {
        TicTacToeGameState st = new TicTacToeGameState();
        st.turn = turn;
        st.winner = winner;
        st.playerToMove = playerToMove;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                st.grid[i][j] = grid[i][j];
            }
        }

        return st;
    }

    @Override
    public void doMove(TicTacToeMove move) {
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        int winCount = 1;
        int lastCell = FREE;
        int rear = 0;
        boolean startCounting = false;

        grid[move.getX()][move.getY()] = player;
        turn++;

        //check end conditions

        //check row
        winner = TicTacToeAlgorithm.checkRow(grid, move);
        if(winner != FREE) {
            return;
        }

        //check col
        winner = TicTacToeAlgorithm.checkColumn(grid, move);
        if(winner != FREE) {
            return;
        }

        //check diag
        winner = TicTacToeAlgorithm.checkDiag(grid, move);
        if(winner != FREE) {
            return;
        }

        //check Anti Diag
        winner = TicTacToeAlgorithm.checkAntiDiag(grid, move);
        if(winner != FREE) {
            return;
        }

        //check Double Three
        winner = TicTacToeAlgorithm.checkDoubleThree(grid, move);
        if(winner != FREE) {
            return;
        }

        //check Four Three
        winner = TicTacToeAlgorithm.checkFourThree(grid, move);
        if(winner != FREE) {
            return;
        }

        //check draw
        if(turn == (GRID_SIZE * GRID_SIZE)){
            //report draw
            winner = DRAW;
            return;
        }

        this.playerToMove = this.getNextPlayer(this.playerToMove);
    }

    @Override
    public ArrayList<TicTacToeMove> GetMoves() {
        if(isTerminal()) {
            return new ArrayList<>();
        }

        ArrayList<TicTacToeMove> moves = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == FREE) {
                    moves.add(new TicTacToeMove(i, j, playerToMove));
                }
            }
        }
        // moves can be sorted to optimize alpha-beta pruning
        // {1,1} is always the best move when available
        return moves;
    }

    @Override
    public GameState cloneAndRandomize(int observer) {
        return this.clone();
    }

    @Override
    public double getResult(int player) {
        int winner = getWinner();
        if(winner == DRAW) {
            return 0.5;
        }
        else if( winner == player) {
            return 1.0;
        }
        else {
            return 0.0;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                sb.append(grid[i][j] == FREE ? " " : (grid[i][j] == PLAYER_O ? "O" : "X"));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getWinner() {
        return winner;
    }
}
