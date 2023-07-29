package com.gprogramming.panels.polygons;

import com.gprogramming.UI;

import java.awt.*;

public class CirclePanel extends PolygonPanel {
    private int radius;

    public CirclePanel(int x, int y, int w, int h, int radius) {
        super(x, y, w, h);
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintCirclePanel(g);
    }

    private void paintCirclePanel(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.polygonScale = UI.isSurpriseDisplaying ? Math.sin(Math.PI * UI.currentFrame / UI.FRAME_RATE) : 0;

        g2d.translate(w / 2, h / 2);

        g2d.scale(this.polygonScale, this.polygonScale);

        g2d.setColor(polygonColor);

        paintCircle(g2d);

        colourChangeCounter++;
    }

    private void paintCircle(Graphics2D g2d) {
        g2d.fillOval(-3 * radius, -radius, 2 * radius, 2 * radius);
        g2d.fillOval(-radius, -radius, 2 * radius, 2 * radius);
        g2d.fillOval(radius, -radius, 2 * radius, 2 * radius);
    }
}
