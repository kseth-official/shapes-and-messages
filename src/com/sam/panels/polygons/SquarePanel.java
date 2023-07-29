package com.sam.panels.polygons;

import com.sam.enums.SquarePosition;
import com.sam.UI;
import com.sam.utility.painting.squarePainter.*;

import java.awt.*;
import java.util.Objects;

public class SquarePanel extends PolygonPanel {
    private SquarePosition squarePosition;

    public SquarePanel(int x, int y, int w, int h, SquarePosition squarePosition) {
        super(x, y, w, h);
        this.squarePosition = squarePosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSquarePanel((Graphics2D) g);
    }

    private void paintSquarePanel(Graphics2D g) {
        Graphics2D g2d = g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.polygonScale = UI.isSurpriseDisplaying ? Math.sin(Math.PI * UI.currentFrame / UI.FRAME_RATE) : 0;

        g2d.scale(this.polygonScale, this.polygonScale);

        g2d.setColor(polygonColor);

        paintSquare(g2d);

        colourChangeCounter++;
    }

    private void paintSquare(Graphics2D g2d) {
        double side = w;
        ISquarePainter squarePainter = null;

        switch (squarePosition) {
            case TOP_LEFT:
                squarePainter = new TopLeftSquarePainter();
                break;
            case TOP_RIGHT:
                squarePainter = new TopRightSquarePainter();
                break;
            case BOTTOM_LEFT:
                squarePainter = new BottomLeftSquarePainter();
                break;
            case BOTTOM_RIGHT:
                squarePainter = new BottomRightSquarePainter();
                break;
            default:
                System.out.println("Invalid Square Position!");
        }

        if (Objects.nonNull(squarePainter)) {
            squarePainter.paintGraphic(g2d,this.x,this.y,side);
        }
    }
}
