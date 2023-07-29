package com.sam.utility.animation.heart;

import com.sam.UI;

import java.awt.*;

public class HeartAnimatorRotating extends HeartAnimator {

    @Override
    public void animateGraphic(Graphics2D g2d, int width, int height, int colourChangeCounter, Color color) {
        double angle = 2 * Math.PI * UI.currentFrame / UI.TICK_RATE;
        g2d.translate(width/2,height/2);
        g2d.rotate(angle);
        g2d.setColor(color);
        g2d.fill(drawHeart());
    }
}
