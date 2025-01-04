package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;

import java.util.Collections;
import java.util.List;

public class SplashOnEntireMap implements Action {
    @Override
    public void action(MapEditor mapEditor) {
        int tileIndex = mapEditor.getModel().getSelectedTileIndex();
        if (tileIndex == -1)
            return;
        List<List<Integer>> tileData = mapEditor.getModel().getTileData().getData();
        // Жагсаалтыг шинэ утгаар дүүргэх
        for (List<Integer> innerList : tileData) {
            Collections.fill(innerList, tileIndex); // Шинэ утгаар дүүргэх
        }
        mapEditor.getModel().repaint();
    }
}
