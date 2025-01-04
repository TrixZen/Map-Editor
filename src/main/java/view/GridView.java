package view;

import AllInterface.IGrid;
import AllInterface.IGridView;

import java.awt.*;

public class GridView implements IGridView {
    @Override
    public void render(Graphics g, IGrid grid) {
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(grid.getColor());
        g2.setStroke(new BasicStroke(grid.getStroke()));

        for (int row = 0; row <= grid.getRows(); row++) {
            int y = grid.getStartY() + row * grid.getGapHeight();
            g.drawLine(grid.getStartX(), y,
                    grid.getStartX() + grid.getCols() * grid.getGapWidth(), y);
        }

        for (int col = 0; col <= grid.getCols(); col++) {
            int x = grid.getStartX() + col * grid.getGapWidth();
            g.drawLine(x, grid.getStartY(),
                    x, grid.getStartY() + grid.getRows() * grid.getGapHeight());
        }
    }
}
