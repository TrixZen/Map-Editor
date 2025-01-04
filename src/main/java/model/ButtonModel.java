package model;

public enum ButtonModel {
    UP("src/main/resources/controlImage/up.png", 144, 664),
    DOWN("src/main/resources/controlImage/down.png", 216, 664),

    LEFT("src/main/resources/controlImage/left.png", 180, 628),
    RIGHT("src/main/resources/controlImage/right.png", 180, 700),

    ZOOM_IN("src/main/resources/controlImage/zoomIn.png", 288, 648),
    ZOOM_OUT("src/main/resources/controlImage/zoomOut.png", 324, 648),

    FILL("src/main/resources/controlImage/fill.png", 288, 684),
    CLEAR("src/main/resources/controlImage/clear.png", 324, 684),

    SWITCHUP("src/main/resources/controlImage/up.png", 468, 756),
    SWITCHDOWN("src/main/resources/controlImage/down.png", 540, 756);

//    BLOCK("src/main/resources/controlImage/block.png", 576, 756);

    private final String label;
    private final int y;
    private final int x;

    ButtonModel(String label, int y, int x) {
        this.label = label;
        this.y = y;
        this.x = x;
    }

    public String getLabel() {
        return label;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
