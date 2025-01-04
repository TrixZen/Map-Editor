package AllInterface;

import java.awt.*;

public interface IGrid {
    int getStartX();
    int getStartY();
    int getRows();
    int getCols();
    int getGapWidth();
    int getGapHeight();
    Color getColor();
    boolean isValid(int x, int y);
    void setXAndY(int x, int y);
    void setStartX(int startX);
    void setStartY(int startY);
    void setGapWidth(int gapWidth);
    void setGapHeight(int gapHeight);
    void setRows(int rows);
    void setCols(int cols);
    int getStroke();
}
