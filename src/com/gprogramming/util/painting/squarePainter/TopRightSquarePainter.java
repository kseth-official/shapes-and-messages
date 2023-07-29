package com.gprogramming.util.painting.squarePainter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class TopRightSquarePainter implements SquarePainter {
    @Override
    public void paintGraphic(Graphics2D g2d, int x, int y, double side) {
        double half = side / 2;
        GeneralPath squareTwo = new GeneralPath();
        squareTwo.moveTo(x+half,y);
        squareTwo.lineTo(x+2*half,y);
        squareTwo.lineTo(x+2*half,y+half);
        squareTwo.lineTo(x+half,y+half);
        squareTwo.closePath();
        g2d.fill(squareTwo);
    }
}
