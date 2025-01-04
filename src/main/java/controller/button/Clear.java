package controller.button;

import AllInterface.Click;
import AllInterface.ICoordinate;
import AllInterface.IGrid;
import main.MapEditorModel;
import model.TileData;

public class Clear implements Click {
    @Override
    public void onClick(MapEditorModel model) {
        TileData<Integer> tileData = model.getTileData();
        IGrid drawMap = model.getGrids().get(1);
        ICoordinate coordinate = model.getCoordinates().get(1);

        for (int row = 0; row < drawMap.getRows(); row++) {
            for (int col = 0; col < drawMap.getCols(); col++) {
                tileData.setTile(null, row + coordinate.row(), col + coordinate.col());
            }
        }
    }
}
