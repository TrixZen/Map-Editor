package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;

import java.util.Collections;
import java.util.List;

public class EraseAllTilesOnMap implements Action {
    @Override
    public void action(MapEditor mapEditor) {
        List<List<Integer>> tileData = mapEditor.getModel().getTileData().getData();
        // Жагсаалтыг шинэ утгаар дүүргэх
        for (List<Integer> innerList : tileData) {
            Collections.fill(innerList, null); // Шинэ утгаар дүүргэх
        }
        mapEditor.getModel().repaint();
    }
}
