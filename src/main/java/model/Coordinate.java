package model;

import AllInterface.ICoordinate;

public class Coordinate implements ICoordinate {

    private int row;
    private int col;
    private boolean valid;

    public Coordinate(int row, int col, boolean valid) {
        this.row = row;
        this.col = col;
        this.valid = valid;
    }

    @Override
    public int row() {
        return row;
    }

    @Override
    public int col() {
        return col;
    }

    @Override
    public boolean valid() {
        return valid;
    }

    @Override
    public void set(int row, int col, boolean valid) {
        this.row = row;
        this.col = col;
        this.valid = valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
