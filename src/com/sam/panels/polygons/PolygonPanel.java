package com.sam.panels.polygons;

import com.sam.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class PolygonPanel extends JPanel {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected int red;
    protected int green;
    protected int blue;
    protected int alpha = 255;
    protected int colourChangeCounter = 0;
    protected Color polygonColor;
    protected double polygonScale;
    protected double polygonAngle;

    public PolygonPanel(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setLayout(new BorderLayout());
        setBackground(Color.PINK);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        changePolygonColor();
    }

    protected void changePolygonColor() {
        // Check if counter is a power of 2
        if ((colourChangeCounter & (UI.FRAME_RATE / 8 - 1)) == 0) {
            // Change the color of the heart
            Random rng = new Random();
            this.red = Math.abs(rng.nextInt()) & (128 - 1) + 64;
            this.green = Math.abs(rng.nextInt()) & (128 - 1) + 64;
            this.blue = Math.abs(rng.nextInt()) & (128 - 1) + 64;
        }

        polygonColor = new Color(red, green, blue);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getColourChangeCounter() {
        return colourChangeCounter;
    }

    public Color getPolygonColor() {
        return polygonColor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setColourChangeCounter(int colourChangeCounter) {
        this.colourChangeCounter = colourChangeCounter;
    }

    public void setPolygonColor(Color polygonColor) {
        this.polygonColor = polygonColor;
    }
}
