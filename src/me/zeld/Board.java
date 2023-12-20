package me.zeld;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Board(int width, int height, int colorChange){
        setLayout(new GridLayout(6,7));
        setPreferredSize(new Dimension(width / 70, height / 60));
        setVisible(true);
        setBackground(colorChange % 2 == 0? new Color(0x20194f) : Color.WHITE);
        setLocation(500,500);
    }
}
