package main;

import AllInterface.*;
import model.*;
import util.CoordinateTranslator;
import util.GridUtility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Main model class that manages all state
public class MapEditorModel {
    private final List<IGrid> grids;
    private final TileData<Integer> tileData;
    private final TileData<Integer> selectedTile;
    private final List<IInformationModel> information;
    private final List<IGrid> activeGrid;
    private Consumer<Void> updateCallback;
    private final List<ICoordinate> coordinates;
    private final TileToolBar<Integer> tileToolBar;
    private final SavePath savePath;
    //
    private boolean drawingMode;
    private int selectedTileIndex = -1;
    private int tileToolBarIndex = 0;
    private final ImageModel imageModel;
    private boolean drawMapGrid = true;

    public MapEditorModel() {
        this.grids = new ArrayList<>();
        this.tileData = new TileData<>(50, 50);  // Default size
        this.information = new ArrayList<>();
        this.activeGrid = new ArrayList<>();
        this.coordinates = new ArrayList<>();
        this.tileToolBar = new TileToolBar<>(5, 20, 2);
        this.selectedTile = new TileData<>(1, 1);
        this.imageModel = new ImageModel();
        this.savePath = new SavePath();

        initializeGrids();
        initializeInformation();
        initializeActiveGrids();
        initializeCoordinate();
    }
    private void initializeCoordinate() {
        coordinates.add(new Coordinate(0, 0, false)); // Active grid ийн row col ийг  хадгалах зориулалтай
        coordinates.add(new Coordinate(0, 0, false)); // // zoneMap ийг background map ийн хэдэх row and col оос эхэлж байгааг хадгалах зориулалтай
    }
    private void initializeGrids() {
        // Initialize all required grids
        grids.add(new GridModel(756, 72, 1, 1, 80, 45, Color.magenta, 1));      // 0 Zone map
        grids.add(new GridModel(36, 36, 9, 16, 36, 36, Color.magenta, 1));      // 1 draw Map
        grids.add(new GridModel(36, 468, 5, 20, 36, 36, Color.magenta, 1));     // 2 Toolbar
        grids.add(new GridModel(756, 72, 50, 50, 5, 5, Color.BLACK, 1));        //  Background map
        grids.add(new GridModel(756, 72, 1, 1, 250, 250, Color.magenta, 1));    // 3 Main map
        grids.add(new GridModel(-50, -50, 1, 1, 36, 36, Color.YELLOW, 5));      // 4 Selector
        grids.add(new GridModel(130, 435, 1, 1, 28, 28, Color.black,1));        //5 Selected View
    }
    private void initializeActiveGrids() {
        activeGrid.add(grids.get(1)); // 0 draw map
        activeGrid.add(grids.get(2)); // 1 toolbar
        activeGrid.add(grids.remove(3)); // 2 background map
    }
    private void initializeInformation() {
        information.add(new InformationModel(" 0", 760, 537));
        information.add(new InformationModel("Selected: ", 36, 453));
        information.add(new InformationModel("Map Size 50 x 50", 789, 358));
    }

    public void repaint() {
        if (updateCallback != null) {
            updateCallback.accept(null);
        }
    }
    private void updateInformation() {
        if (!information.isEmpty()) {
            information.get(0).setMessage(" " + tileToolBarIndex);
        }
    }
    public void placeTile() {
        if (selectedTileIndex >= 0) {
            ICoordinate coord = coordinates.get(0);
            ICoordinate zoneMap = coordinates.get(1);
            tileData.setTile(selectedTileIndex,
                    coord.row() + zoneMap.row(),
                    coord.col() + zoneMap.col());
            repaint();
        }
    }
    // Setters
    public void setUpdateCallback(Consumer<Void> callback) {
        this.updateCallback = callback;
    }
    public void setXAndY(Point point, IGrid grid) {

        int x = point.x;
        int y = point.y;

        int[] limitBorder = GridUtility.calculateLimitBorder(grid);

        IGrid zoneMap = grids.get(0);

        int limitX = limitBorder[0] - zoneMap.getGapWidth();
        int limitY = limitBorder[1] - zoneMap.getGapHeight();

        x = Math.min(x, limitX);
        y = Math.min(y, limitY);

        point.setLocation(x, y);

        grids.get(0).setXAndY(x, y);
        // Zone Map coordinate-ийн row, col хоёрыг шинээр тогтооно
        CoordinateTranslator.toCoord(point, grid, coordinates.get(1));

        repaint();
    }
    public void setTileToolBarIndex(int newIndex) {
        newIndex = tileToolBarIndex + newIndex;
        selectedTileIndex = -1;
        drawingMode = false;
        drawSelector(-50, -50);
        if (newIndex < 0 || newIndex >= tileToolBar.getInitCapacity()) {
            return;
        }
        tileToolBarIndex = newIndex;
        updateInformation();
    }
    private void drawSelector(int x, int y) {
        IGrid grid = grids.get(4);
        grid.setStartX(x);
        grid.setStartY(y);
    }
    public void setSelectedTile() {
        ICoordinate coord = coordinates.get(0);
        Integer tile = tileToolBar.getSelectedTileIndex(tileToolBarIndex, coord.row(), coord.col());

        if (tile != null){
            selectedTileIndex = tile;
            drawingMode = true;
            Point point = CoordinateTranslator.toPoint(coord, grids.get(2));
            drawSelector(point.x, point.y);
            selectedTile.setTile(tile, 0, 0);
        } else {
            drawingMode = false;
            selectedTileIndex = -1;
            drawSelector(-50, -50);
            selectedTile.setTile(null, 0, 0);
        }
        repaint();
    }
    public void setDrawMapGrid(boolean drawMapGrid) {this.drawMapGrid = drawMapGrid;}

    // Getters
    public List<IGrid> getGrids() { return grids; }
    public TileData<Integer> getTileData() { return tileData; }
    public List<IInformationModel> getInformation() { return information; }
    public List<IGrid> getActiveGrid() { return activeGrid; }
    public boolean isDrawingMode() { return drawingMode; }
    public List<ICoordinate> getCoordinates() {return coordinates;}
    public int getSelectedTileIndex(){return selectedTileIndex;}
    public TileToolBar<Integer> getTileToolBar() {return tileToolBar;}
    public int getTileToolBarIndex() {return tileToolBarIndex;}
    public TileData<Integer> getSelectedTile() {return selectedTile;}
    public ImageModel getImageModel() {return imageModel;}
    public boolean getDrawMapGrid() {return drawMapGrid; }
    public SavePath getSavePath() {return savePath;}
}
