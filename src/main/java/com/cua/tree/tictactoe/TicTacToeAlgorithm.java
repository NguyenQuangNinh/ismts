package com.cua.tree.tictactoe;

import static com.cua.tree.tictactoe.TicTacToeGameState.FREE;
import static com.cua.tree.tictactoe.TicTacToeGameState.GRID_SIZE;
import static com.cua.tree.tictactoe.TicTacToeGameState.WIN_SIZE;

/**
 * Created by nguyenquangninh on 5/31/17.
 */
public class TicTacToeAlgorithm {
    private enum Direction {
        HORIZONTAL(1, 0),
        VERTICAL(0, 1),
        DIAG(1, 1),
        ANTI_DIAG(1, -1)
        ;

        private int x;
        private int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static int checkRow(int[][] grid, TicTacToeMove move) {
        int winner = FREE; //No winner;
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        boolean startCounting = false;
        int lastCell = FREE;
        int rear = 0;
        int winCount = 1;
        for(int i = 0; i < GRID_SIZE; i++){
            if(startCounting) {
                if(grid[i][y] == player)
                {
                    winCount++;
                    if(winCount == WIN_SIZE){
                        winner = player;
                        return winner;
                    }
                }
                else {
                    rear = rear + lastCell * grid[i][y];
                    if(rear == 0 && winCount >= 4) {
                        winner = player;
                        return winner;
                    }
                    else {
                        break;
                    }
                }
            } else {
                startCounting = grid[i][y] == player;
                if(startCounting) {
                    rear = player * lastCell;
                }
            }

            lastCell = grid[i][y];
        }
        return winner;
    }

    public static int checkColumn(int[][] grid, TicTacToeMove move) {
        int winner = FREE; //No winner;
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        boolean startCounting = false;
        int lastCell = FREE;
        int rear = 0;
        int winCount = 1;
        for(int i = 0; i < GRID_SIZE; i++){
            if(startCounting) {
                if(grid[x][i] == player)
                {
                    winCount++;
                    if(winCount == WIN_SIZE){
                        winner = player;
                        return winner;
                    }
                }
                else {
                    rear = rear + lastCell * grid[x][i];
                    if(rear == 0 && winCount >= 4) {
                        winner = player;
                        return winner;
                    }
                    else {
                        break;
                    }
                }
            } else {
                startCounting = grid[x][i] == player;
                if(startCounting) {
                    rear = player * lastCell;
                }
            }

            lastCell = grid[x][i];
        }

        return winner;
    }

    public static int checkDiag(int[][] grid, TicTacToeMove move) {
        int winner = FREE; //No winner;
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        int rear = 0;
        int winCount = 1;
        int tempX = x - 1;
        int tempY = y - 1;
        while(tempX >=0 && tempY >=0) {
            if(grid[tempX][tempY] == player)
            {
                winCount++;
                tempX--;
                tempY--;
                if(winCount == WIN_SIZE){
                    winner = player;
                    return winner;
                }
            }
            else {
                rear = player * grid[tempX][tempY];
                break;
            }
        }

        tempX = x + 1;
        tempY = y + 1;
        while(tempX < GRID_SIZE && tempY < GRID_SIZE) {
            if(grid[tempX][tempY] == player)
            {
                winCount++;
                tempX++;
                tempY++;
                if(winCount == WIN_SIZE){
                    winner = player;
                    return winner;
                }
            }
            else {
                rear = rear + player * grid[tempX][tempY];
                if(rear == 0 && winCount >= 4) {
                    winner = player;
                    return winner;
                }
                else {
                    break;
                }
            }
        }

        return winner;
    }

    public static int checkAntiDiag(int[][] grid, TicTacToeMove move) {
        int winner = FREE; //No winner;
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        int rear = 0;
        int winCount = 1;
        int tempX = x + 1;
        int tempY = y - 1;
        winCount = 1;
        while(tempX < GRID_SIZE && tempY >= 0) {
            if(grid[tempX][tempY] == player)
            {
                winCount++;
                tempX++;
                tempY--;
                if(winCount == WIN_SIZE){
                    winner = player;
                    return winner;
                }
            }
            else {
                rear = player * grid[tempX][tempY];
                break;
            }
        }

        tempX = x - 1;
        tempY = y + 1;
        while(tempX >= 0 && tempY < GRID_SIZE) {
            if(grid[tempX][tempY] == player)
            {
                winCount++;
                tempX--;
                tempY++;
                if(winCount == WIN_SIZE){
                    winner = player;
                    return winner;
                }
            }
            else {
                rear = rear + player * grid[tempX][tempY];
                if(rear == 0 && winCount >= 4) {
                    winner = player;
                    return winner;
                }
                else {
                    break;
                }
            }
        }

        return winner;
    }

    public static int checkFourThree(int[][] grid, TicTacToeMove move) {
        int winner = FREE; //No winner;
        int player = move.getPlayer();

        if(isFourOneEnd(grid, move, Direction.DIAG) &&
                isFree(grid, move, Direction.ANTI_DIAG, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.ANTI_DIAG) &&
                isFree(grid, move, Direction.DIAG, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.DIAG) &&
                isFree(grid, move, Direction.HORIZONTAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.HORIZONTAL) &&
                isFree(grid, move, Direction.DIAG, 3)) {
            winner = player;
            return winner;
        }


        if(isFourOneEnd(grid, move, Direction.DIAG) &&
                isFree(grid, move, Direction.VERTICAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.VERTICAL) &&
                isFree(grid, move, Direction.DIAG, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.ANTI_DIAG) &&
                isFree(grid, move, Direction.HORIZONTAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.HORIZONTAL) &&
                isFree(grid, move, Direction.ANTI_DIAG, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.ANTI_DIAG) &&
                isFree(grid, move, Direction.VERTICAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.VERTICAL) &&
                isFree(grid, move, Direction.ANTI_DIAG, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.HORIZONTAL) &&
                isFree(grid, move, Direction.VERTICAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFourOneEnd(grid, move, Direction.VERTICAL) &&
                isFree(grid, move, Direction.HORIZONTAL, 3)) {
            winner = player;
            return winner;
        }
        return winner;
    }

    public static int checkDoubleThree(int[][] grid, TicTacToeMove move) {
        int winner = FREE; //No winner;
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();

        if(isFree(grid, move, Direction.DIAG, 3) &&
                isFree(grid, move, Direction.ANTI_DIAG, 3)) {
            winner = player;
            return winner;
        }

        if(isFree(grid, move, Direction.DIAG, 3) &&
                isFree(grid, move, Direction.HORIZONTAL, 3)) {
            winner = player;
            return winner;
        }


        if(isFree(grid, move, Direction.DIAG, 3) &&
                isFree(grid, move, Direction.VERTICAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFree(grid, move, Direction.ANTI_DIAG, 3) &&
                isFree(grid, move, Direction.HORIZONTAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFree(grid, move, Direction.ANTI_DIAG, 3) &&
                isFree(grid, move, Direction.VERTICAL, 3)) {
            winner = player;
            return winner;
        }

        if(isFree(grid, move, Direction.HORIZONTAL, 3) &&
                isFree(grid, move, Direction.VERTICAL, 3)) {
            winner = player;
            return winner;
        }

        return winner;
    }

    private static boolean isFree(int[][] grid, TicTacToeMove move, Direction d, int winSize) {
        boolean result = false;

        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        int tempX = x - d.getX();
        int tempY = y - d.getY();
        int winCount = 1;

        while(isValid(tempX, tempY, GRID_SIZE)) {
            if(grid[tempX][tempY] == player || grid[tempX][tempY] == FREE)
            {
                if(grid[tempX][tempY] == FREE) {
                    if(winCount > 1) {
                        break;
                    }
                }

                if(grid[tempX][tempY] == player) {
                    winCount++;
                }

                tempX -= d.getX();
                tempY -= d.getY();
                if(winCount == winSize){
                    if((isValid(tempX, tempY, GRID_SIZE)) && (grid[tempX][tempY] == FREE || grid[tempX][tempY] == player)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }

        tempX = x + d.getX();
        tempY = y + d.getY();
        while(isValid(tempX, tempY, GRID_SIZE)) {
            if(grid[tempX][tempY] == player || grid[tempX][tempY] == FREE)
            {
                if(grid[tempX][tempY] == FREE) {
                    if(winCount > 1) {
                        break;
                    }
                }

                if(grid[tempX][tempY] == player) {
                    winCount++;
                }

                tempX += d.getX();
                tempY += d.getY();
                if(winCount == winSize){
                    if((isValid(tempX, tempY, GRID_SIZE)) && (grid[tempX][tempY] == FREE || grid[tempX][tempY] == player)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
        return result;
    }

    private static boolean isFourOneEnd(int[][] grid, TicTacToeMove move, Direction d) {
        boolean result = false;

        int winSize = 4;
        int x = move.getX();
        int y = move.getY();
        int player = move.getPlayer();
        int tempX = x - d.getX();
        int tempY = y - d.getY();
        int winCount = 1;

        while(isValid(tempX, tempY, GRID_SIZE)) {
            if(grid[tempX][tempY] == player || grid[tempX][tempY] == FREE)
            {
                if(grid[tempX][tempY] == FREE) {
                    if(winCount > 1) {
                        break;
                    }
                }

                if(grid[tempX][tempY] == player) {
                    winCount++;
                }

                tempX -= d.getX();
                tempY -= d.getY();
                if(winCount == winSize){
                    tempX = x + d.getX();
                    tempY = y + d.getY();
                    if((isValid(tempX, tempY, GRID_SIZE)) && (grid[tempX][tempY] == FREE || grid[tempX][tempY] == player)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }

        tempX = x + d.getX();
        tempY = y + d.getY();
        while(isValid(tempX, tempY, GRID_SIZE)) {
            if(grid[tempX][tempY] == player || grid[tempX][tempY] == FREE)
            {
                if(grid[tempX][tempY] == FREE) {
                    if(winCount > 1) {
                        break;
                    }
                }

                if(grid[tempX][tempY] == player) {
                    winCount++;
                }

                tempX += d.getX();
                tempY += d.getY();
                if(winCount == winSize){
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return result;
    }

    public static boolean isValid(int x, int y, int size) {
        if(x < 0) {
            return false;
        }

        if(x >= size) {
            return false;
        }

        if(y < 0) {
            return false;
        }

        if(y >= size) {
            return false;
        }

        return true;
    }
}
