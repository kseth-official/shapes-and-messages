package com.sam;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class SurpriseButton extends JButton {
    public SurpriseButton() {
        setText("Click for a surprise!");
        setFont(new Font("Times New Roman",Font.BOLD,24));
        setBackground(Color.PINK);
        setFocusPainted(false);
        setOpaque(true);
        setPreferredSize(new Dimension(UI.HEART_PANEL_WIDTH, UI.HEART_PANEL_HEIGHT / 10));
        setBorder(new EtchedBorder());
    }
}
