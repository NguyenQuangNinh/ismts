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

    private static final int GRID_SIZE  = 3;

    /** The grid */
    private final int[][] grid;
    private int turn = 0;

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
    }

    @Override
    public boolean isTerminal() {
        return hasWon(PLAYER_O) || hasWon(PLAYER_X) || turn == 9;
    }

    @Override
    public GameState clone() {
        TicTacToeGameState st = new TicTacToeGameState();
        st.turn = turn;
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
        grid[move.getX()][move.getY()] = move.getPlayer();
        turn++;
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
        if(winner == 100) {
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
        sb.append(grid[0][0] == FREE ? " " : (grid[0][0] == PLAYER_O ? "O" : "X"));
        sb.append(grid[1][0] == FREE ? " " : (grid[1][0] == PLAYER_O ? "O" : "X"));
        sb.append(grid[2][0] == FREE ? " " : (grid[2][0] == PLAYER_O ? "O" : "X"));
        sb.append("\n");
        sb.append(grid[0][1] == FREE ? " " : (grid[0][1] == PLAYER_O ? "O" : "X"));
        sb.append(grid[1][1] == FREE ? " " : (grid[1][1] == PLAYER_O ? "O" : "X"));
        sb.append(grid[2][1] == FREE ? " " : (grid[2][1] == PLAYER_O ? "O" : "X"));
        sb.append("\n");
        sb.append(grid[0][2] == FREE ? " " : (grid[0][2] == PLAYER_O ? "O" : "X"));
        sb.append(grid[1][2] == FREE ? " " : (grid[1][2] == PLAYER_O ? "O" : "X"));
        sb.append(grid[2][2] == FREE ? " " : (grid[2][2] == PLAYER_O ? "O" : "X"));
        sb.append("\n");
        return sb.toString();
    }

    public int getWinner() {
        if (hasWon(PLAYER_O)) {
            return PLAYER_O;
        } else if(hasWon(PLAYER_X)) {
            return PLAYER_X;
        } else {
            return 100;
        }
    }

    private boolean hasWon(int player) {
        return
                (player == grid[0][1] && player == grid[0][2] && player == grid[0][0])
                        ||
                        (player == grid[1][1] && player == grid[1][2] && player == grid[1][0])
                        ||
                        (player == grid[2][1] && player == grid[2][2] && player == grid[2][0])
                        ||
                        (player == grid[1][0] && player == grid[2][0] && player == grid[0][0])
                        ||
                        (player == grid[1][1] && player == grid[2][1] && player == grid[0][1])
                        ||
                        (player == grid[1][2] && player == grid[2][2] && player == grid[0][2])
                        ||
                        (player == grid[1][1] && player == grid[2][2] && player == grid[0][0])
                        ||
                        (player == grid[1][1] && player == grid[2][0] && player == grid[0][2]);
    }
}
