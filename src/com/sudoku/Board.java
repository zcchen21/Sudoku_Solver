package com.sudoku;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    public static final int BOARD_SIZE = 9;

    private int[][] board = {{0, 0, 8, 3, 0, 0, 4, 0, 2},
                             {0, 0, 0, 4, 0, 0, 3, 0, 0},
                             {2, 0, 0, 6, 0, 0, 5, 9, 1},
                             {6, 1, 9, 0, 0, 4, 0, 0, 0},
                             {0, 0, 0, 0, 9, 0, 0, 0, 0},
                             {0, 0, 0, 2, 0, 0, 9, 1, 5},
                             {1, 4, 3, 0, 0, 7, 0, 0, 9},
                             {0, 0, 6, 0, 0, 3, 0, 0, 0},
                             {9, 0, 2, 0, 0, 5, 8, 0, 0}};

    Box[][] boxes = new Box[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        super.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boxes[i][j] = new Box(i, j);
                if (board[i][j] == 0) {
                    boxes[i][j].setText("");
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setForeground(Color.blue);
                } else {
                    boxes[i][j].setText(board[i][j] + "");
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.lightGray);
                }
                boxes[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                if (i % 3 == 0 && i != 0) {
                    boxes[i][j].setBorder(BorderFactory.createMatteBorder(5, 1, 1, 1, Color.BLACK));
                }
                if (j % 3 == 0 && j != 0) {
                    boxes[i][j].setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK));
                }
                if (i % 3 == 0 && i != 0 && j % 3 == 0 && j != 0) {
                    boxes[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 1, 1, Color.BLACK));
                }
                super.add(boxes[i][j]);
            }
        }
    }
}
