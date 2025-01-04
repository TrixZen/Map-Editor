package view;

import AllInterface.IGrid;
import AllInterface.ITileView;
import model.ImageModel;
import model.TileData;

import javax.swing.*;
import java.awt.*;

public class ImageGridDrawer implements ITileView {
    @Override
    public void render(Graphics g, ImageModel imageModel, TileData<Integer> tileData, IGrid grid, int indexRow, int indexCol) {
        if (tileData == null || grid == null || imageModel.getImageList().isEmpty()) return;

        // Grid-ийн мэдээллийг авах
        int startX = grid.getStartX();
        int startY = grid.getStartY();
        int gapWidth = grid.getGapWidth();
        int gapHeight = grid.getGapHeight();

        // Бүх tile-ийг зурах
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                Integer tileIndex = tileData.getTile(row + indexRow, col + indexCol);
                if (tileIndex != null) {
                    renderTile(g, imageModel,startX + col * gapWidth,
                            startY + row * gapHeight,
                            gapWidth, gapHeight, tileIndex);
                }
            }
        }
    }
    private void renderTile(Graphics g, ImageModel imageModel,int x, int y, int width, int height, int tileIndex) {
        if (tileIndex > imageModel.getImageList().size())
            return;
        ImageIcon image = imageModel.getImageList().get(tileIndex);
        g.drawImage(image.getImage(), x, y, width, height, null);
    }
}
