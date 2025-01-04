package controller.button;

import AllInterface.Click;
import AllInterface.IGrid;
import main.MapEditorModel;
import util.GridUtility;

import java.util.List;

public class ZoomOut implements Click {
    private final int MOVING = 40;

    @Override
    public void onClick(MapEditorModel model) {

        List<IGrid> grids = model.getGrids();
        IGrid zoneMap = grids.get(0);

        int newGapWidth = zoneMap.getGapWidth() + MOVING;
        int newGapHeight = zoneMap.getGapHeight() + MOVING;

        if (newGapWidth <= 80 + MOVING && newGapHeight <= 45 + MOVING) {
            int[] mapBounds = GridUtility.calculateLimitBorder(grids.get(3));
            int zoneMapBoundX = zoneMap.getStartX() + newGapWidth;
            int zoneMapBoundY = zoneMap.getStartY() + newGapHeight;

            if (zoneMapBoundX <= mapBounds[0] && zoneMapBoundY <= mapBounds[1]) {
                zoneMap.setGapWidth(newGapWidth);
                zoneMap.setGapHeight(newGapHeight);

                updateDrawMap(grids.get(1), model.getActiveGrid().get(2));
            }
        }
    }
        private void updateDrawMap(IGrid drawMap, IGrid backgroundMap) {
            int newCols, newRows;

            newCols = drawMap.getCols() + MOVING / backgroundMap.getGapWidth();
            newRows = drawMap.getRows() + MOVING / backgroundMap.getGapHeight();


            int newGapWidth = drawMap.getGapWidth() * drawMap.getCols() / newCols;
            int newGapHeight = drawMap.getGapHeight() * drawMap.getRows() / newRows;

            drawMap.setCols(newCols);
            drawMap.setRows(newRows);
            drawMap.setGapWidth(newGapWidth);
            drawMap.setGapHeight(newGapHeight);
    }
}
