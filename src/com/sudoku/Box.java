package com.sudoku;

import javax.swing.*;
import java.awt.*;

public class Box extends JTextField {
    private int row;
    private int col;

    public Box(int row, int col) {
        super();  // JTextField constructor
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(new Font("Roboto", Font.BOLD, 20));
        super.setForeground(Color.black);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
