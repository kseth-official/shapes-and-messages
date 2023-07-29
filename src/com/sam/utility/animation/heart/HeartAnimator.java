package com.sam.utility.animation.heart;

import com.sam.utility.animation.IAnimator;

import java.awt.*;
import java.awt.geom.Path2D;

public abstract class HeartAnimator {
    public abstract void animateGraphic(Graphics2D g2d, int width, int height, int colourChangeCounter, Color color);

    protected Path2D.Double drawHeart() {
        // Draw a heart of size 150x120 pixels
        Path2D.Double heart = new Path2D.Double();

        heart.moveTo(75, 40);
        heart.curveTo(75, 37, 70, 25, 50, 25);
        heart.curveTo(20, 25, 20, 62.5, 20, 62.5);
        heart.curveTo(20, 80, 40, 102, 75, 120);
        heart.curveTo(110, 102, 130, 80, 130, 62.5);
        heart.curveTo(130, 62.5, 130, 25, 100, 25);
        heart.curveTo(85, 25, 75, 37, 75, 40);

        return heart;
    }
}
