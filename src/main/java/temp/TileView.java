package temp;

import AllInterface.IGrid;
import AllInterface.ITileView;
import model.ImageModel;
import model.TileData;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Implementation of tile view
public class TileView implements ITileView {
    private static final Color DEFAULT_TILE_COLOR = new Color(100, 149, 237); // Cornflower blue
    private final Map<Integer, Color> tileColors;

    public TileView() {
        // Өөр өөр tile-д өөр өнгө оноох
        tileColors = new HashMap<>();
        initializeTileColors();
    }

    private void initializeTileColors() {
        // Жишээ байдлаар хэдэн өнгө үүсгэж байна
        // Бодит хэрэглээнд tile-ийн төрөл бүрт тохирох өнгө оноох
        tileColors.put(0, new Color(34, 139, 34));    // Forest Green
        tileColors.put(1, new Color(205, 133, 63));   // Peru
        tileColors.put(2, new Color(70, 130, 180));   // Steel Blue
        tileColors.put(3, new Color(218, 165, 32));   // Goldenrod
        tileColors.put(4, new Color(139, 69, 19));    // Saddle Brown
    }

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
                    renderTile(g, startX + col * gapWidth,
                            startY + row * gapHeight,
                            gapWidth, gapHeight, tileIndex);
                }
            }
        }
    }

    private void renderTile(Graphics g, int x, int y, int width, int height, int tileIndex) {
        // Graphics2D-г ашиглан илүү сайн чанартай зурах
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Tile-ийн өнгийг авах
        Color tileColor = tileColors.getOrDefault(tileIndex, DEFAULT_TILE_COLOR);

        // Tile-ийг зурах
        g2d.setColor(tileColor);
        g2d.fillRect(x, y, width - 1, height - 1);

        // Tile-ийн хүрээг зурах
        g2d.setColor(tileColor.darker());
        g2d.drawRect(x, y, width - 1, height - 1);

        // Tile-ийн дугаарыг бичих (хэрэв шаардлагатай бол)
        drawTileNumber(g2d, x, y, width, height, tileIndex);
    }

    private void drawTileNumber(Graphics2D g2d, int x, int y, int width, int height, int tileIndex) {
        // Текстийн тохиргоо
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));

        String text = String.valueOf(tileIndex);
        FontMetrics metrics = g2d.getFontMetrics();

        // Текстийг tile-ийн төвд байрлуулах
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.drawString(text, textX, textY);
    }

    // Tile-ийн өнгө нэмэх функц
    public void addTileColor(int tileIndex, Color color) {
        tileColors.put(tileIndex, color);
    }
}