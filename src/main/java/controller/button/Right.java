package controller.button;

import AllInterface.Click;
import AllInterface.IGrid;
import main.MapEditorModel;
import util.CoordinateTranslator;
import util.GridUtility;

import java.awt.*;
import java.util.List;

public class Right implements Click {
    @Override
    public void onClick(MapEditorModel model) {

        List<IGrid> grids = model.getGrids();
        IGrid zoneMap = grids.get(0);
        IGrid map = grids.get(3);
        int newStartX = zoneMap.getStartX() + 40;
        int[] mapBounds = GridUtility.calculateLimitBorder(map);
        int limitX = mapBounds[0] - zoneMap.getGapWidth();

        if (newStartX > limitX)
            newStartX = limitX;
        else if (newStartX < map.getStartX())
            newStartX = map.getStartX();

        zoneMap.setStartX(newStartX);

        CoordinateTranslator.toCoord(new Point(zoneMap.getStartX(), zoneMap.getStartY()),
                model.getActiveGrid().get(2), model.getCoordinates().get(1));
    }
}
