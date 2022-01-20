package com.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {
    private JFrame frame;
    private Board board;

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

    public GUI() {
        board = new Board();
        board.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 32) {
                    fillBoard(board.boxes);
                }
            }
        });
        board.setFocusable(true);
        board.requestFocusInWindow();

        frame = new JFrame("Sudoku");
        frame.setSize(500, 500);
        frame.add(board, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public boolean fillBoard(Box[][] boxes) {
        int[] emptySpace = findEmptySpace(boxes);
        if (emptySpace[0] == -1) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            if (isValid(boxes, emptySpace, i)) {
                boxes[emptySpace[0]][emptySpace[1]].setText(i + "");
                if (fillBoard(boxes)) {
                    return true;
                }
                boxes[emptySpace[0]][emptySpace[1]].setText("");
            }
        }
        return false;
    }

    public int[] findEmptySpace(Box[][] boxes) {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                if (boxes[i][j].getText().equals("")) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public boolean isValid(Box[][] boxes, int[] position, int insertedNum) {
        // check if the same row contains the same inserted number
        for (int col = 0; col < boxes[0].length; col++) {
            if (boxes[position[0]][col].getText().equals(insertedNum + "") && col != position[1]) {
                return false;
            }
        }
        // check if the same column contains the same inserted number
        for (int row = 0; row < boxes.length; row++) {
            if (boxes[row][position[1]].getText().equals(insertedNum + "") && row != position[0]) {
                return false;
            }
        }
        // check if the same 3 x 3 box contains the same inserted number
        for (int row = position[0] / 3 * 3; row < position[0] / 3 * 3 + 3; row++) {
            for (int col = position[1] / 3 * 3; col < position[1] / 3 * 3 + 3; col++) {
                if (boxes[row][col].getText().equals(insertedNum + "") && row != position[0] && col != position[1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
