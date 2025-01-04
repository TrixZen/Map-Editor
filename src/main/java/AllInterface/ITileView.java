package AllInterface;

import model.ImageModel;
import model.TileData;

import java.awt.*;

public interface ITileView {
    void render(Graphics g, ImageModel imageModel, TileData<Integer> tilData, IGrid grid, int indexRow, int indexCol);
}
