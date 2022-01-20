package com.sudoku;

public class SudokuSolver {

    public static void main(String[] args) {
	    int[][] board = {{0, 0, 8, 3, 0, 0, 4, 0, 2},
                         {0, 0, 0, 4, 0, 0, 3, 0, 0},
                         {2, 0, 0, 6, 0, 0, 5, 9, 1},
                         {6, 1, 9, 0, 0, 4, 0, 0, 0},
                         {0, 0, 0, 0, 9, 0, 0, 0, 0},
                         {0, 0, 0, 2, 0, 0, 9, 1, 5},
                         {1, 4, 3, 0, 0, 7, 0, 0, 9},
                         {0, 0, 6, 0, 0, 3, 0, 0, 0},
                         {9, 0, 2, 0, 0, 5, 8, 0, 0}};
        printBoard(board);
        System.out.println();
        fillBoard(board);
        printBoard(board);
    }

    /**
     * Recursively fill in the empty spaces in the board, backtrack if an element is not valid
     * @param board
     * @return true if all the empty spaces are filled in
     */
    public static boolean fillBoard(int[][] board) {
        int[] emptySpace = findEmptySpace(board);
        if (emptySpace[0] == -1) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            if (isValid(board, emptySpace, i)) {
                board[emptySpace[0]][emptySpace[1]] = i;
                if (fillBoard(board)) {
                    return true;
                }
                board[emptySpace[0]][emptySpace[1]] = 0;
            }
        }
        return false;
    }

    /**
     * Find an empty space in the board to fill in
     * @param board
     * @return - the row and column index of the empty space
     */
    public static int[] findEmptySpace(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Check if inserting a certain number into a certain empty space is valid
     * @param board
     * @param position - the empty space
     * @param insertedNum - number inserted into the position
     * @return true if it is valid
     */
    public static boolean isValid(int[][] board, int[] position, int insertedNum) {
        // check if the same row contains the same inserted number
        for (int col = 0; col < board[0].length; col++) {
            if (board[position[0]][col] == insertedNum && col != position[1]) {
                return false;
            }
        }
        // check if the same column contains the same inserted number
        for (int row = 0; row < board.length; row++) {
            if (board[row][position[1]] == insertedNum && row != position[0]) {
                return false;
            }
        }
        // check if the same 3 x 3 box contains the same inserted number
        for (int row = position[0] / 3 * 3; row < position[0] / 3 * 3 + 3; row++) {
            for (int col = position[1] / 3 * 3; col < position[1] / 3 * 3 + 3; col++) {
                if (board[row][col] == insertedNum && row != position[0] && col != position[1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("- - - - - - - - - - -");
            }
            for (int col = 0; col < board[row].length; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
