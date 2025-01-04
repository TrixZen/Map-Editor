package controller.button;

import AllInterface.Click;
import AllInterface.IGrid;
import main.MapEditorModel;
import util.CoordinateTranslator;
import util.GridUtility;

import java.awt.*;
import java.util.List;

public class Down implements Click {
    @Override
    public void onClick(MapEditorModel model) {

        List<IGrid> grids = model.getGrids();
        IGrid zoneMap = grids.get(0);
        IGrid map = grids.get(3);

        int newStartY = zoneMap.getStartY() + 40;
        int[] mapBounds = GridUtility.calculateLimitBorder(map);
        int limitY = mapBounds[1] - zoneMap.getGapHeight();

        if (newStartY > limitY)
            newStartY = limitY;
        else if (newStartY < map.getStartY())
            newStartY = map.getStartY();

        zoneMap.setStartY(newStartY);

        CoordinateTranslator.toCoord(new Point(zoneMap.getStartX(), zoneMap.getStartY()),
                model.getActiveGrid().get(2), model.getCoordinates().get(1));
    }
}
