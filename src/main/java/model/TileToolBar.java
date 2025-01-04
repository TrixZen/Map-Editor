package model;

import java.util.ArrayList;
import java.util.List;

public class TileToolBar<T> {
    private final List<TileData<T>> tileToolBar;
    private final int rows;
    private final int cols;
    private final int initCapacity;

    public TileToolBar(int rows, int cols, int initCapacity) {
        this.rows = rows;
        this.cols = cols;
        this.initCapacity = initCapacity;
        tileToolBar = new ArrayList<>();
        initializeTileData();
    }
    private void initializeTileData() {
        for (int index = 1; index <= initCapacity; index++) {
            tileToolBar.add(new TileData<>(rows, cols));
        }
    }
    public T getSelectedTileIndex(int index, int row, int col) {
        return tileToolBar.get(index).getTile(row, col);
    }
    public void addTileData(int index, T tile, int row, int col) {
        tileToolBar.get(index).setTile(tile, row, col);
    }

    public TileData<T> getTileToolBar(int index) {
        return tileToolBar.get(index);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getInitCapacity() {
        return initCapacity;
    }
}
