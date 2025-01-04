package controller.button;

import AllInterface.Click;
import AllInterface.ICoordinate;
import AllInterface.IGrid;
import main.MapEditorModel;
import model.TileData;

public class Fill implements Click {
    @Override
    public void onClick(MapEditorModel model) {
        int index = model.getSelectedTileIndex();
        if (index == -1) return;
        TileData<Integer> tileData = model.getTileData();
        IGrid drawMap = model.getGrids().get(1);
        ICoordinate coordinate = model.getCoordinates().get(1);

        for (int row = 0; row < drawMap.getRows(); row++) {
            for (int col = 0; col < drawMap.getCols(); col++) {
                tileData.setTile(index, row + coordinate.row(), col + coordinate.col());
            }
        }
    }
}
