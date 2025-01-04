package model;

import AllInterface.IInformationModel;

public class InformationModel implements IInformationModel {
    private String message;
    private final int x;
    private final int y;

    public InformationModel(String message, int x, int y) {
        this.message = message;
        this.x = x;
        this.y = y;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
