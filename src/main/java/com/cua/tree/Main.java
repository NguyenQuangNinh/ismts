package com.cua.tree;

import com.cua.tree.tictactoe.TicTacToeGameState;
import com.cua.tree.tictactoe.TicTacToeMove;

/**
 * Created by nguyenquangninh on 5/29/17.
 */
public class Main {
    public static void main(String[] args) {
        TicTacToeGameState state = new TicTacToeGameState();
//        int[][] grid = {
//                {TicTacToeGameState.FREE, TicTacToeGameState.FREE, TicTacToeGameState.PLAYER_X},
//                {TicTacToeGameState.FREE, TicTacToeGameState.PLAYER_X, TicTacToeGameState.FREE},
//                {TicTacToeGameState.PLAYER_O, TicTacToeGameState.PLAYER_X, TicTacToeGameState.PLAYER_O}
//        };
//        TicTacToeGameState state = new TicTacToeGameState(grid, 5, TicTacToeGameState.PLAYER_O);
        ISMCTS<TicTacToeMove> ai = new ISMCTS<TicTacToeMove>();
        TicTacToeMove move = null;

        while (!state.isTerminal())
        {
            System.out.print(state.toString());
            if(state.playerToMove == 1)
            {
                move = ai.search(state, 1000, false );
            }
            else {
                move = ai.search(state, 1000, false);
            }

            System.out.println("\nBest Move: " + move.toString());

            state.doMove(move);
        }

        System.out.println(state.toString());
        String winner = "";

        switch (state.getWinner()) {
            case TicTacToeGameState.PLAYER_O:
                winner = "O";
                System.out.println("Player " + winner + " win!!!");
                break;
            case TicTacToeGameState.PLAYER_X:
                winner = "X";
                System.out.println("Player " + winner + " win!!!");
                break;
            default:
                System.out.println("Raw!!!");
                break;
        }

    }
}
