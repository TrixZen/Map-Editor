package model;

import AllInterface.IGrid;

import java.awt.*;

public class GridModel implements IGrid {
    private int startX;
    private int startY;
    private int rows;
    private int cols;
    private int gapWidth;
    private int gapHeight;
    private final Color color;
    private final int stroke;

    public GridModel(int startX, int startY, int rows, int cols, int gapWidth, int gapHeight, Color color, int stroke) {
        this.startX = startX;
        this.startY = startY;
        this.rows = rows;
        this.cols = cols;
        this.gapWidth = gapWidth;
        this.gapHeight = gapHeight;
        this.color = color;
        this.stroke = stroke;
    }

    // Getters and Setters
    @Override
    public int getStartX() {
        return startX;
    }
    @Override
    public int getStartY() {
        return startY;
    }
    @Override
    public int getRows() {
        return rows;
    }
    @Override
    public int getCols() {
        return cols;
    }
    @Override
    public int getGapWidth() {
        return gapWidth;
    }
    @Override
    public int getGapHeight() {
        return gapHeight;
    }
    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public boolean isValid(int x, int y) {
        return x >= startX && y >= startY &&
                x < (startX +(gapWidth * cols)) && y < (startY + (gapHeight * rows));
    }
    @Override
    public void setStartX(int startX) {
        this.startX = startX;
    }
    @Override
    public void setStartY(int startY) {
        this.startY = startY;
    }
    @Override
    public void setGapWidth(int gapWidth) {
        this.gapWidth = gapWidth;
    }
    @Override
    public void setGapHeight(int gapHeight) {
        this.gapHeight = gapHeight;
    }
    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }
    @Override
    public void setCols(int cols) {
        this.cols = cols;
    }
    @Override
    public int getStroke() {
        return stroke;
    }
    @Override
    public void setXAndY(int x, int y) {setStartX(x);setStartY(y);}

}

