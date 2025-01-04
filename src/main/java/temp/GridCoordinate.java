package temp;

import AllInterface.IGrid;

import java.awt.*;

/**
 * Grid дээрх байрлалыг илэрхийлэх класс.
 * Immutable байдлаар хэрэгжүүлсэн (өөрчлөх боломжгүй).
 */
public record GridCoordinate(int row, int col, boolean valid) {
    /**
     * Координат үүсгэх constructor
     *
     * @param row   Мөрийн дугаар
     * @param col   Баганын дугаар
     * @param valid Координат хүчинтэй эсэх
     */
    public GridCoordinate {
    }

    /**
     * Хүчингүй координат үүсгэх статик функц
     */
    public static GridCoordinate invalid() {
        return new GridCoordinate(-1, -1, false);
    }

    /**
     * Хүчинтэй координат үүсгэх статик функц
     */
    public static GridCoordinate of(int row, int col) {
        return new GridCoordinate(row, col, true);
    }

    /**
     * Координат grid-ийн хязгаарт байгаа эсэхийг шалгах
     *
     * @param grid Шалгах grid
     * @return Хязгаарт байгаа эсэх
     */
    public boolean isWithinBounds(IGrid grid) {

        return valid &&
                row >= 0 && row < grid.getRows() &&
                col >= 0 && col < grid.getCols();
    }

    /**
     * Координатыг өөр grid-рүү хөрвүүлэх
     *
     * @param sourceGrid Эх grid
     * @param targetGrid Зорилтот grid
     * @return Шинэ координат
     */
    public GridCoordinate translateToGrid(IGrid sourceGrid, IGrid targetGrid) {
        if (!isWithinBounds(sourceGrid)) {
            return invalid();
        }

        // Grid-үүдийн гараа цэгийн зөрүүг тооцоолох
        int deltaX = targetGrid.getStartX() - sourceGrid.getStartX();
        int deltaY = targetGrid.getStartY() - sourceGrid.getStartY();

        // Нүд хоорондын зайны харьцааг тооцоолох
        double scaleX = (double) targetGrid.getGapWidth() / sourceGrid.getGapWidth();
        double scaleY = (double) targetGrid.getGapHeight() / sourceGrid.getGapHeight();

        // Шинэ координатыг тооцоолох
        int newCol = ((col * sourceGrid.getGapWidth() + deltaX) / targetGrid.getGapWidth());
        int newRow = ((row * sourceGrid.getGapHeight() + deltaY) / targetGrid.getGapHeight());

        // Шинэ координат хүчинтэй эсэхийг шалгах
        if (newRow >= 0 && newRow < targetGrid.getRows() &&
                newCol >= 0 && newCol < targetGrid.getCols()) {
            return of(newRow, newCol);
        }

        return invalid();
    }

    /**
     * Хоёр координатын хоорондох зайг тооцоолох
     *
     * @param other Нөгөө координат
     * @return Евклидийн зай
     */

    public double distanceTo(GridCoordinate other) {
        if (other == null || !this.valid || !other.valid) {
            return -1; // Хэрэв координат хүчингүй бол
        }
        return Math.sqrt(Math.pow(this.row - other.row, 2) + Math.pow(this.col - other.col, 2));
    }


    /**
     * Координатыг дэлгэцийн цэг болгон хөрвүүлэх
     *
     * @param grid Тухайн grid
     * @return Point объект
     */
    public Point toScreenPoint(IGrid grid) {
        if (!isWithinBounds(grid)) {
            return new Point(-1, -1);
        }

        int screenX = grid.getStartX() + col * grid.getGapWidth();
        int screenY = grid.getStartY() + row * grid.getGapHeight();

        return new Point(screenX, screenY);
    }

    @Override
    public String toString() {
        return valid ?
                String.format("GridCoordinate(row=%d, col=%d)", row, col) :
                "GridCoordinate(invalid)";
    }
}