package util;

import AllInterface.IGrid;

public class GridUtility {
    public static int[] calculateLimitBorder(IGrid grid) {
        return new int[] {
                grid.getStartX() + grid.getCols() * grid.getGapWidth(),
                grid.getStartY() + grid.getRows() * grid.getGapHeight()
        };
    }
}
