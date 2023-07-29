package com.sam.utility.painting.squarePainter;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class BottomRightSquarePainter implements ISquarePainter {
    @Override
    public void paintGraphic(Graphics2D g2d, int x, int y, double side) {
        double half = side / 2;
        GeneralPath squareFour = new GeneralPath();
        squareFour.moveTo(x+half,y+half);
        squareFour.lineTo(x+2*half,y+half);
        squareFour.lineTo(x+2*half,y+2*half);
        squareFour.lineTo(x+half,y+2*half);
        squareFour.closePath();
        g2d.fill(squareFour);
    }
}
