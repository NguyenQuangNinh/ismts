package com.cua.tree;

import com.cua.tree.tictactoe.TicTacToeMove;

import java.util.ArrayList;

/**
 * Created by nguyenquangninh on 5/26/17.
 */
public abstract class GameState<M extends Move> {
    protected int numberOfPlayers = 2;
    protected int playerToMove = 1;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getPlayerToMove() {
        return playerToMove;
    }

    public void setPlayerToMove(int playerToMove) {
        this.playerToMove = playerToMove;
    }

    public GameState(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Return the player to the left of the specified player
     * @param p
     * @return
     */
    public int getNextPlayer(int p) {
        return (p % this.numberOfPlayers) + 1;
    }

    /**
     * Create a deep clone of this game state.
     * @return GameState
     */
    public abstract GameState clone();

    /**
     * Create a deep clone of this game state, randomizing any information not visible to the specified observer player.
     * @return GameState
     */
    public abstract GameState cloneAndRandomize(int observer);

    /**
     * Update a state by carrying out the given move.
     * Must update playerToMove.
     * @param move
     */
    public void doMove(M move) {
        this.playerToMove = this.getNextPlayer(this.playerToMove);
    }

    /**
     * Get all possible moves from this state
     * @return
     */
    public abstract ArrayList<M> GetMoves();

    /**
     * Get the game result from the viewpoint of player
     * @return
     */
    public abstract double getResult(int player);

    /**
     * Is the current game finished
     * @return
     */
    public abstract boolean isTerminal();
}

