package com.gprogramming.panels.polygons;

import com.gprogramming.UI;
import com.gprogramming.observerPattern.Subject;

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

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Heart scale factor and rotation calculation
        double scale;

        if (UI.isSurpriseDisplaying)
            scale = Math.sin(Math.PI * UI.currentFrame / UI.FRAME_RATE);
        else
            scale = 0;

        g2d.setColor(polygonColor);

        // Translate the circle to the center of the panel
        g2d.translate(w / 2, h / 2);

        // Scale the circle
        g2d.scale(scale, scale);

        g.setColor(polygonColor);

        // Draw a circle of radius
        g2d.fillOval(-3 * radius, -radius, 2 * radius, 2 * radius);
        g2d.fillOval(-radius, -radius, 2 * radius, 2 * radius);
        g2d.fillOval(radius, -radius, 2 * radius, 2 * radius);

        colourChangeCounter++;
    }
}
