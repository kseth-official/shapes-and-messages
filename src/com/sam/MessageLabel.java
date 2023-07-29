package com.sam;

import com.sam.observation.Observer;
import com.sam.observation.Subject;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MessageLabel extends JLabel implements Subject, Observer {
    private final int TIMER_DELAY = UI.FRAME_DELAY * 4;
    private final int FONT_SIZE = 24;
    private final String FONT_NAME = "Times New Roman";
    private Thread renderingThread;
    private String message;
    private Timer timer;

    private List<Observer> observers = new ArrayList<>();

    public MessageLabel(String message) {
        this.message = message;
        setupAttributes();
    }

    private void setupAttributes() {
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.TOP);
        setFont(new Font(FONT_NAME, Font.ITALIC,FONT_SIZE));
        setBackground(Color.PINK);
        setVisible(true);
        setOpaque(true);
        setBorder(new EtchedBorder());
        setDoubleBuffered(true);
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
        timer = new Timer(TIMER_DELAY, new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                setText("<html>" + message.substring(0,index) + "</html>");
                index++;

                if (index == message.length()) {
                    notifyObservers();
                    timer.stop();
                }

            }
        });
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(UI.HEART_PANEL_WIDTH/2,UI.HEART_PANEL_HEIGHT);
    }

    @Override
    public void update(Subject subject) {
        System.out.println("Message Label Called");
        startRenderer();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(this);
        }
    }
}
