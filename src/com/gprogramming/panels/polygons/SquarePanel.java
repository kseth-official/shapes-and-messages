package com.gprogramming.panels.polygons;

import com.gprogramming.enums.SquarePosition;
import com.gprogramming.UI;
import com.gprogramming.observerPattern.Subject;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class SquarePanel extends PolygonPanel {
    private SquarePosition squarePosition;

    public SquarePanel(int x, int y, int w, int h, SquarePosition squarePosition) {
        super(x, y, w, h);
        this.squarePosition = squarePosition;
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

        // Scale the square
        g2d.scale(scale, scale);

        g2d.setColor(polygonColor);

        // Draw a square of side
        double side = w;
        double half = side/2;

        switch (squarePosition) {
            case TOP_LEFT:
                GeneralPath squareOne = setupSquareOne(half);
                g2d.fill(squareOne);
                break;
            case TOP_RIGHT:
                GeneralPath squareTwo = setupSquareTwo(half);
                g2d.fill(squareTwo);
                break;
            case BOTTOM_LEFT:
                GeneralPath squareThree = setupSquareThree(half);
                g2d.fill(squareThree);
                break;
            case BOTTOM_RIGHT:
                GeneralPath squareFour = setupSquareFour(half);
                g2d.fill(squareFour);
                break;
            default:
                System.out.println("Invalid Square Position!");
        }

        colourChangeCounter++;
    }

    private GeneralPath setupSquareFour(double half) {
        GeneralPath squareFour = new GeneralPath();
        squareFour.moveTo(x+half,y+half);
        squareFour.lineTo(x+2*half,y+half);
        squareFour.lineTo(x+2*half,y+2*half);
        squareFour.lineTo(x+half,y+2*half);
        squareFour.closePath();
        return squareFour;
    }

    private GeneralPath setupSquareThree(double half) {
        GeneralPath squareThree = new GeneralPath();
        squareThree.moveTo(x,y+half);
        squareThree.lineTo(x+half,y+half);
        squareThree.lineTo(x+half,y+2*half);
        squareThree.lineTo(x,y+2*half);
        squareThree.closePath();
        return squareThree;
    }

    private GeneralPath setupSquareTwo(double half) {
        GeneralPath squareTwo = new GeneralPath();
        squareTwo.moveTo(x+half,y);
        squareTwo.lineTo(x+2*half,y);
        squareTwo.lineTo(x+2*half,y+half);
        squareTwo.lineTo(x+half,y+half);
        squareTwo.closePath();
        return squareTwo;
    }

    private GeneralPath setupSquareOne(double half) {
        GeneralPath squareOne = new GeneralPath();
        squareOne.moveTo(0,0);
        squareOne.lineTo(x+ half,y);
        squareOne.lineTo(x+ half,y+ half);
        squareOne.lineTo(x,y+ half);
        squareOne.closePath();
        return squareOne;
    }
}
