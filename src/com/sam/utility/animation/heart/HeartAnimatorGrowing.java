package com.sam.utility.animation.heart;

import com.sam.UI;
import com.sam.enums.HeartAnimation;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

public class HeartAnimatorGrowing extends HeartAnimator {
    @Override
    public void animateGraphic(Graphics2D g2d, int width, int height, int colourChangeCounter, Color color) {
        double scale = Math.sin(Math.PI / 2 * UI.currentFrame / UI.FRAME_RATE);
        g2d.translate(width/2,height/2);
        g2d.scale(scale, scale);
        g2d.setColor(color);
        g2d.fill(drawHeart());
    }
}
