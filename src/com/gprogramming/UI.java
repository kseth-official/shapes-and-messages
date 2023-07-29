package com.gprogramming;

import com.gprogramming.enums.HeartAnimation;
import com.gprogramming.enums.SquarePosition;
import com.gprogramming.panels.polygons.CirclePanel;
import com.gprogramming.panels.polygons.HeartPanel;
import com.gprogramming.panels.PolygonDisplayPanel;
import com.gprogramming.panels.polygons.PolygonPanel;
import com.gprogramming.panels.polygons.squarePanel.SquarePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    public static final String MESSAGE = "<b>Hi insert_name,<br><br>This is a sample message<br><br>This is a sample message<br><br>This is a sample message<br><br>concluding_line<br><br>signoff<br>insert_name<br></b>";

    public static final int FRAME_RATE = 128;
    public static final int FRAME_DELAY = 1000 / FRAME_RATE; // in milliseconds

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 500;
    public static final int HEART_PANEL_WIDTH = FRAME_WIDTH;
    public static final int HEART_PANEL_HEIGHT = FRAME_HEIGHT;
    public static final int POLYGON_PANEL_WIDTH = FRAME_WIDTH / 5;
    public static final int POLYGON_PANEL_HEIGHT = FRAME_HEIGHT / 5;

    public static int currentFrame;
    public static boolean isSurpriseDisplaying;
    public static MessageLabel messageLabel;

    private Timer timer;
    private JButton surpriseButton;
    private HeartPanel heartPanel;
    private PolygonDisplayPanel masterPanel;

    public UI() {
        super("Concurrent Animations");
        setupAttributes();
        setupTimer();
        addComponentsToFrame();
        setVisible(true);
        pack();
    }

    private void setupAttributes() {
        setBackground(Color.PINK);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupTimer() {
        this.timer = new Timer(FRAME_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFrame <= FRAME_RATE) {
                    currentFrame = (currentFrame & (UI.FRAME_RATE - 1)) + 1;
                }
                repaintUI();
            }
        });
    }

    private void repaintUI() {
        heartPanel.repaint();
    }

    private void addComponentsToFrame() {
        setupMasterPanel();

        setupMessageLabel();

        setupSurpriseButton();

        setupHeartPanel();
    }

    private void setupMessageLabel() {
        messageLabel = new MessageLabel(MESSAGE);
        getContentPane().add(messageLabel,BorderLayout.EAST);
        messageLabel.registerObserver(masterPanel);
    }

    private void setupSurpriseButton() {
        surpriseButton = new SurpriseButton();
        surpriseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSurpriseDisplaying = true;
                timer.start();
            }
        });
        getContentPane().add(surpriseButton, BorderLayout.SOUTH);
    }

    private void setupHeartPanel() {
        heartPanel = new HeartPanel(0,0, HEART_PANEL_WIDTH, HEART_PANEL_HEIGHT, HeartAnimation.GROWING);
        getContentPane().add(heartPanel);

        heartPanel.registerObserver(messageLabel);
    }

    private void setupMasterPanel() {
        masterPanel = new PolygonDisplayPanel();
        getContentPane().add(masterPanel, BorderLayout.WEST);

        int counter = 0;
        PolygonPanel panel;
        for (int i = 0; i < 25; i++) {
            if ((i & 1) == 0) {
                panel = new CirclePanel(0,0, POLYGON_PANEL_WIDTH, POLYGON_PANEL_HEIGHT, (int) Math.ceil(POLYGON_PANEL_WIDTH / Math.sqrt(2)));
            } else {
                PolygonPanel squarePanel;

                if ((counter & 3) == 0) {
                    panel = new SquarePanel(0, 0, POLYGON_PANEL_WIDTH, POLYGON_PANEL_HEIGHT, SquarePosition.TOP_LEFT);
                } else if ((counter & 3) == 2) {
                    panel = new SquarePanel(0, 0, POLYGON_PANEL_WIDTH, POLYGON_PANEL_HEIGHT, SquarePosition.TOP_RIGHT);
                } else if ((counter & 3) == 1) {
                    panel = new SquarePanel(0, 0, POLYGON_PANEL_WIDTH, POLYGON_PANEL_HEIGHT, SquarePosition.BOTTOM_LEFT);
                } else {
                    panel = new SquarePanel(0, 0, POLYGON_PANEL_WIDTH, POLYGON_PANEL_HEIGHT, SquarePosition.BOTTOM_RIGHT);
                }
                counter++;
            }
            masterPanel.add(panel);
            masterPanel.getChildPanels().add(panel);
        }
    }
}
