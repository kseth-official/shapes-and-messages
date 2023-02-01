package com.gprogramming.panels.polygons;

import com.gprogramming.enums.HeartAnimation;
import com.gprogramming.UI;
import com.gprogramming.observerPattern.Observer;
import com.gprogramming.observerPattern.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeartPanel extends JPanel implements Subject {
    // Size in px
    private final int HEART_WIDTH = 150;
    private final int HEART_HEIGHT = 120;

    private int x;
    private int y;
    private int w;
    private int h;
    private int red;
    private int green;
    private int blue;
    private int alpha = 255;
    private int colourChangeCounter = 0;

    private Color heartColor = Color.BLACK;
    private HeartAnimation currentAnimation;
    private List<Observer> observers = new ArrayList<>();

    public HeartPanel(int x, int y, int w, int h, HeartAnimation startingAnimation) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.currentAnimation = startingAnimation;
        setLayout(new BorderLayout());
        setBackground(Color.PINK);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (UI.isSurpriseDisplaying) {
            Image img = Toolkit.getDefaultToolkit().getImage("CatSquare.jpeg");
            g.drawImage(img, 0, 0, w,h,this);
        }

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Heart scale factor and rotation calculation
        double scale = 0;
        double angle = 0;

        if (UI.isSurpriseDisplaying && currentAnimation == HeartAnimation.GROWING) {
            scale = Math.sin(Math.PI / 2 * UI.currentFrame / UI.FRAME_RATE);

            if (UI.currentFrame == UI.FRAME_RATE) {
                currentAnimation = HeartAnimation.ROTATING;
                scale = 1;
                notifyObservers();
            }
        } else if (UI.isSurpriseDisplaying && currentAnimation == HeartAnimation.ROTATING) {
            angle = 2 * Math.PI * UI.currentFrame / UI.FRAME_RATE;
        } else {
            scale = 0;
        }

        // Draw a heart of size 150x120 pixels
        Path2D.Double heart = new Path2D.Double();
        heart.moveTo(75, 40);
        heart.curveTo(75, 37, 70, 25, 50, 25);
        heart.curveTo(20, 25, 20, 62.5, 20, 62.5);
        heart.curveTo(20, 80, 40, 102, 75, 120);
        heart.curveTo(110, 102, 130, 80, 130, 62.5);
        heart.curveTo(130, 62.5, 130, 25, 100, 25);
        heart.curveTo(85, 25, 75, 37, 75, 40);

        // Transform the heart to the center of the panel
        g2d.translate(w/2,h/2);

        if (currentAnimation == HeartAnimation.GROWING) {
            // Scale the heart
            g2d.scale(scale,scale);
        } else if (currentAnimation == HeartAnimation.ROTATING) {
            // Rotate the heart by an angle
            g2d.rotate(angle);
        }

        // Check if counter is a power of 2
        if ((colourChangeCounter & (UI.FRAME_RATE / 8 - 1)) == 0) {
            // Change the color of the heart
            Random rng = new Random();
            this.red = Math.abs(rng.nextInt()) & (128 - 1) + 64;
            this.green = Math.abs(rng.nextInt()) & (128 - 1) + 64;
            this.blue = Math.abs(rng.nextInt()) & (128 - 1) + 64;
        }

        heartColor = new Color(red,green,blue);

        g2d.setColor(heartColor);
        g2d.fill(heart);

        colourChangeCounter++;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
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
        for(Observer observer: observers) {
            observer.update(this);
        }
    }
}
