package com.gprogramming.util.squarePainter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class TopLeftSquarePainter implements SquarePainter {
    @Override
    public void paintGraphic(Graphics2D g2d, int x, int y, double side) {
        double half = side / 2;
        GeneralPath squareOne = new GeneralPath();
        squareOne.moveTo(0,0);
        squareOne.lineTo(x+ half,y);
        squareOne.lineTo(x+ half,y+ half);
        squareOne.lineTo(x,y+ half);
        squareOne.closePath();
        g2d.fill(squareOne);
    }
}
