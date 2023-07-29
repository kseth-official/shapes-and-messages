package com.sam.panels;

import com.sam.UI;
import com.sam.observation.Observer;
import com.sam.observation.Subject;
import com.sam.panels.polygons.PolygonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PolygonDisplayPanel extends JPanel implements Observer {
    private final int TIMER_DELAY = UI.FRAME_DELAY;
    private List<PolygonPanel> childPanels = new ArrayList<>();
    private Thread renderingThread;
    private Timer timer;

    public List<PolygonPanel> getChildPanels() {
        return childPanels;
    }

    public void setChildPanels(List<PolygonPanel> childPanels) {
        this.childPanels = childPanels;
    }

    public PolygonDisplayPanel() {
        setLayout(new GridLayout(5,5));
        setBackground(Color.PINK);
        setOpaque(true);
    }

    private void startRenderer() {
        renderingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                setupTimer();
                timer.start();
            }
        });

        renderingThread.start();
    }

    private void setupTimer() {
        this.timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (PolygonPanel panel : childPanels)
                    panel.repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(UI.FRAME_WIDTH,UI.FRAME_HEIGHT);
    }

    @Override
    public void update(Subject subject) {
        System.out.println("Polygon Display Panel Called");
        startRenderer();
    }
}
