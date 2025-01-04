package util;

import AllInterface.ICoordinate;
import AllInterface.IGrid;

import java.awt.*;

public class CoordinateTranslator {

    public static Point toPoint(ICoordinate coord, IGrid grid) {
        int x = grid.getStartX() + coord.col() * grid.getGapWidth();
        int y = grid.getStartY() + coord.row() * grid.getGapHeight();
        return new Point(x, y);
    }

    public static void toCoord(Point point, IGrid grid, ICoordinate coordinate) {

        int gridCol = (point.x - grid.getStartX()) / grid.getGapWidth();
        int gridRow = (point.y - grid.getStartY()) / grid.getGapHeight();

        if (gridRow <= -1 || gridCol <= -1 || !grid.isValid(point.x, point.y)) {
            coordinate.setValid(false);
            return;
        }
        coordinate.set(gridRow, gridCol, true);
    }
}
