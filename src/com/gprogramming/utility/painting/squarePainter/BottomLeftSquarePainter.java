package com.gprogramming.utility.painting.squarePainter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class BottomLeftSquarePainter implements SquarePainter {
    @Override
    public void paintGraphic(Graphics2D g2d, int x, int y, double side) {
        double half = side / 2;
        GeneralPath squareThree = new GeneralPath();
        squareThree.moveTo(x,y+half);
        squareThree.lineTo(x+half,y+half);
        squareThree.lineTo(x+half,y+2*half);
        squareThree.lineTo(x,y+2*half);
        squareThree.closePath();
        g2d.fill(squareThree);
    }
}
