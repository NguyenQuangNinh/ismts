package com.cua.tree;

import com.cua.tree.tictactoe.TicTacToeGameState;
import com.cua.tree.tictactoe.TicTacToeMove;

/**
 * Created by nguyenquangninh on 5/29/17.
 */
public class Main {
    public static void main(String[] args) {
        int correctCount = 0;
        int totalRound = 50;
        for(int i = 0; i < totalRound; i++) {
            System.out.println("===========================================");
            System.out.println("Round: " + i);
            correctCount += play();
        }

        System.out.println("===========================================");
        System.out.println("SUMMARY");
        System.out.println("Correct Action: " + correctCount);
        System.out.println("Error: " + (1.0 - Double.valueOf(correctCount)/Double.valueOf(totalRound)));

    }

    public static int play() {
//                int[][] grid = new int[7][7];
//                String s = "       " +
//                        "    X  " +
//                        "   OXX " +
//                        "  OXO  " +
//                        "  XO X " +
//                        " OO X  " +
//                        "       ";
//        for(int i = 0; i < 7; i++) {
//            for(int j = 0; j < 7; j++) {
//                int index = i + j*7;
//                if(s.charAt(index) == 'O')
//                {
//                    grid[j][i] = TicTacToeGameState.PLAYER_O;
//                } else if (s.charAt(index) == 'X') {
//                    grid[j][i] = TicTacToeGameState.PLAYER_X;
//                }
//                else {
//                    grid[j][i] = TicTacToeGameState.FREE;
//                }
//            }
//        }
//        TicTacToeGameState state = new TicTacToeGameState(grid, 13, TicTacToeGameState.PLAYER_O);

        TicTacToeGameState state = new TicTacToeGameState();
        ISMCTS<TicTacToeMove> ai = new ISMCTS<TicTacToeMove>();
        TicTacToeMove move = null;

        while (!state.isTerminal())
        {
            System.out.print(state.toString());
            if(state.playerToMove == 1)
            {
                move = ai.search(state, 10000, false );
            }
            else {
                move = ai.search(state, 10000, false);
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
                return 1;
        }

        return 0;
    }
}
