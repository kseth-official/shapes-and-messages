package com.sam.panels.polygons;

import com.sam.enums.HeartAnimation;
import com.sam.UI;
import com.sam.observation.Observer;
import com.sam.observation.Subject;
import com.sam.utility.animation.heart.HeartAnimator;
import com.sam.utility.animation.heart.HeartAnimatorGrowing;
import com.sam.utility.animation.heart.HeartAnimatorRotating;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HeartPanel extends JPanel implements Subject {
    private final int WIDTH_PX = 150;
    private final int HEIGHT_PX = 120;
    private int x;
    private int y;
    private int w;
    private int h;
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

        changeHeartColor();

        HeartAnimator heartAnimator = null;

        if (currentAnimation == HeartAnimation.GROWING) {
            if (UI.currentFrame == UI.FRAME_RATE) {
                currentAnimation = HeartAnimation.ROTATING;
                notifyObservers();
            }
            
            heartAnimator = new HeartAnimatorGrowing();
        } else if (currentAnimation == HeartAnimation.ROTATING) {
            heartAnimator = new HeartAnimatorRotating();
        }

        if (Objects.nonNull(heartAnimator)) {
            heartAnimator.animateGraphic(g2d, this.w, this.h, colourChangeCounter, heartColor);
        }

        colourChangeCounter++;
    }

    private void changeHeartColor() {
        // Check if counter is a power of 2
        if ((colourChangeCounter & (UI.FRAME_RATE / 8 - 1)) == 0) {
            int red = 0;
            int green = 0;
            int blue = 0;

            // Change the color of the heart
            Random rng = new Random();

            red = Math.abs(rng.nextInt()) & (128 - 1) + 64;
            green = Math.abs(rng.nextInt()) & (128 - 1) + 64;
            blue = Math.abs(rng.nextInt()) & (128 - 1) + 64;

            this.heartColor = new Color(red,green,blue);
        }
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
