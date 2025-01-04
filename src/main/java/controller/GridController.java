package controller;

import AllInterface.ICoordinate;
import AllInterface.IGrid;
import AllInterface.IGridController;
import main.MapEditorModel;
import util.CoordinateTranslator;

import java.awt.*;

public class GridController implements IGridController {
    private final MapEditorModel model;

    public GridController(MapEditorModel model) {
        this.model = model;
    }

    @Override
    public void handleMouseAction(Point point) {
        // toolbar check
        ICoordinate coord = model.getCoordinates().get(0);
        CoordinateTranslator.toCoord(point, model.getActiveGrid().get(1), coord);
        if (coord.valid()) {
            model.setSelectedTile();
            return;
        }

        IGrid grid = model.getActiveGrid().get(2);

        if (grid.isValid(point.x, point.y)) {

            int x = Math.max(point.x - model.getGrids().get(0).getGapWidth() / 2, grid.getStartX());
            int y = Math.max(point.y - model.getGrids().get(0).getGapHeight() / 2, grid.getStartY());
            point.setLocation(x, y);

            CoordinateTranslator.toCoord(point, grid, coord);
            if (coord.valid()) {
                model.setXAndY(CoordinateTranslator.toPoint(coord, grid), grid);
                return;
            }
        }

        if (model.isDrawingMode()) {
            coord = model.getCoordinates().get(0);
            CoordinateTranslator.toCoord(point, model.getActiveGrid().get(0), coord);
            if (coord.valid()) {
                model.placeTile();
            }
        }
    }

    @Override
    public void handleMouseDrag(Point point) {
        if (model.isDrawingMode()) {
            ICoordinate coord = model.getCoordinates().get(0);
            CoordinateTranslator.toCoord(point, model.getActiveGrid().get(0), coord);
            if (coord.valid()) {
                model.placeTile();
            }
        }
    }
}
