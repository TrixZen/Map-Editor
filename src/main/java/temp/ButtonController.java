package temp;

import AllInterface.ICoordinate;
import AllInterface.IGrid;
import main.MapEditorModel;
import model.TileData;
import util.CoordinateTranslator;
import util.GridUtility;

import java.awt.*;

public class ButtonController implements ButtonAction {
    private final IGrid zoneMap;
    private final IGrid map;
    private final IGrid backgroundMap;
    private final IGrid drawMap;
    private final TileData<Integer> tileData;
    private final int MOVING = 40; // Хөдөлгөөний хэмжээ
    private final ICoordinate coordinate;
    private final MapEditorModel mapEditorModel;

    public ButtonController(MapEditorModel mapEditorModel) {
         zoneMap = mapEditorModel.getGrids().get(0);
         map = mapEditorModel.getGrids().get(3);
         backgroundMap = mapEditorModel.getActiveGrid().get(2);
         coordinate = mapEditorModel.getCoordinates().get(1);
         drawMap = mapEditorModel.getGrids().get(1);
         tileData = mapEditorModel.getTileData();
         this.mapEditorModel = mapEditorModel;
    }

    @Override
    public void up() {
        moveVertically(-1);
    }

    @Override
    public void down() {
        moveVertically(1);
    }

    @Override
    public void left() {
        moveHorizontally(-1);
    }

    @Override
    public void right() {
        moveHorizontally(1);
    }

    @Override
    public void zoomIn() {
        int newGapWidth = zoneMap.getGapWidth() - MOVING;
        int newGapHeight = zoneMap.getGapHeight() - MOVING;

        if (newGapWidth >= 80 && newGapHeight >= 45) {
            zoneMap.setGapWidth(newGapWidth);
            zoneMap.setGapHeight(newGapHeight);

            updateDrawMap('-');
        }
    }

    @Override
    public void zoomOut() {
        int newGapWidth = zoneMap.getGapWidth() + MOVING;
        int newGapHeight = zoneMap.getGapHeight() + MOVING;

        if (newGapWidth <= 80 + MOVING && newGapHeight <= 45 + MOVING) {
            int[] mapBounds = GridUtility.calculateLimitBorder(map);
            int zoneMapBoundX = zoneMap.getStartX() + newGapWidth;
            int zoneMapBoundY = zoneMap.getStartY() + newGapHeight;

            if (zoneMapBoundX <= mapBounds[0] && zoneMapBoundY <= mapBounds[1]) {
                zoneMap.setGapWidth(newGapWidth);
                zoneMap.setGapHeight(newGapHeight);

                updateDrawMap( '+');
            }
        }
    }

    @Override
    public void fill() {
        if (mapEditorModel.getSelectedTileIndex() == -1) return;
        for (int row = 0; row < drawMap.getRows(); row++) {
            for (int col = 0; col < drawMap.getCols(); col++) {
                tileData.setTile(mapEditorModel.getSelectedTileIndex(), row + coordinate.row(), col + coordinate.col());
            }
        }
    }

    @Override
    public void clear() {
        for (int row = 0; row < drawMap.getRows(); row++) {
            for (int col = 0; col < drawMap.getCols(); col++) {
                tileData.setTile(null, row + coordinate.row(), col + coordinate.col());
            }
        }
    }

    @Override
    public void switchup() {
        mapEditorModel.setTileToolBarIndex(-1);
    }

    @Override
    public void switchdown() {
        mapEditorModel.setTileToolBarIndex(1);
    }

    private void updateDrawMap(char ch) {
        int newCols, newRows;
        if (ch == '-') {
            newCols = drawMap.getCols() - MOVING / backgroundMap.getGapWidth();
            newRows = drawMap.getRows() - MOVING / backgroundMap.getGapHeight();
        } else {
            newCols = drawMap.getCols() + MOVING / backgroundMap.getGapWidth();
            newRows = drawMap.getRows() + MOVING / backgroundMap.getGapHeight();
        }

        int newGapWidth = drawMap.getGapWidth() * drawMap.getCols() / newCols;
        int newGapHeight = drawMap.getGapHeight() * drawMap.getRows() / newRows;

        drawMap.setCols(newCols);
        drawMap.setRows(newRows);
        drawMap.setGapWidth(newGapWidth);
        drawMap.setGapHeight(newGapHeight);
    }
    // Хөндлөн чиглэлд хөдөлгөх функц
    private void moveHorizontally(int direction) {
        int newStartX = zoneMap.getStartX() + direction * MOVING;
        int[] mapBounds = GridUtility.calculateLimitBorder(map);
        int limitX = mapBounds[0] - zoneMap.getGapWidth();

        if (newStartX > limitX)
            newStartX = limitX;
        else if (newStartX < map.getStartX())
            newStartX = map.getStartX();

        zoneMap.setStartX(newStartX);

        CoordinateTranslator.toCoord(new Point(zoneMap.getStartX(), zoneMap.getStartY()),
                backgroundMap,coordinate);
    }

    // Босоо чиглэлд хөдөлгөх функц
    private void moveVertically(int direction) {
        int newStartY = zoneMap.getStartY() + direction * MOVING;
        int[] mapBounds = GridUtility.calculateLimitBorder(map);
        int limitY = mapBounds[1] - zoneMap.getGapHeight();

        if (newStartY > limitY)
            newStartY = limitY;
        else if (newStartY < map.getStartY())
            newStartY = map.getStartY();

        zoneMap.setStartY(newStartY);

        CoordinateTranslator.toCoord(new Point(zoneMap.getStartX(), zoneMap.getStartY()),
                backgroundMap,coordinate);
    }
}
