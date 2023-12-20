package me.zeld;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final int WIDTH = 700;
    private final int HEIGHT = 600;
    private final Board[][] BOARD = new Board[6][7];

    Frame() {
        setVisible(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeBoard();
        pack();
    }

    private void initializeBoard(){
        for (int i = 0; i < BOARD.length; i++) {
            for (int j = 0; j < BOARD[0].length; j++) {
                BOARD[i][j] = new Board(WIDTH,HEIGHT, i + j);
                add(BOARD[i][j]);
            }
        }

    }
}
