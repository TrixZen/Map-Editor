package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileData<T> {
    private List<List<T>> data;

    public TileData(int rows, int cols) {
        data = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            data.add(new ArrayList<>(Collections.nCopies(cols, null)));
        }
    }

    public void setTile(T tile, int row, int col) {
        if (isValidPosition(row, col)) {
            data.get(row).set(col, tile);
        }
    }

    public T getTile(int row, int col) {
        return isValidPosition(row, col) ? data.get(row).get(col) : null;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < data.size() && col >= 0 && col < data.get(0).size();
    }

    public List<List<T>> getData() {
        return data;
    }

    public void setData(List<List<T>> data) {
        this.data = data;
    }
}
