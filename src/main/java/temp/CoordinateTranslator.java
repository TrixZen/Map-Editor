package temp;

import AllInterface.IGrid;

import java.awt.*;

/**
 * CoordinateTranslator класс нь GridCoordinate-уудын хооронд хөрвүүлэх болон бусад хэрэгцээтэй utility функцуудыг агуулна.
 */
public class CoordinateTranslator {

    /**
     * GridCoordinate-г нэг grid-ээс өөр grid рүү хөрвүүлэх
     *
     * @param coordinate Эх координат
     * @param sourceGrid Эх grid
     * @param targetGrid Зорилтот grid
     * @return Зорилтот grid-ийн координат
     */
    public static GridCoordinate translate(GridCoordinate coordinate, IGrid sourceGrid, IGrid targetGrid) {
        return coordinate.translateToGrid(sourceGrid, targetGrid);
    }

    /**
     * Хоёр координатын хоорондох зайг тооцоолох
     *
     * @param first  Эх координат
     * @param second Нөгөө координат
     * @return Евклидийн зай
     */
    public static double calculateDistance(GridCoordinate first, GridCoordinate second) {
        return first.distanceTo(second);
    }

    /**
     * GridCoordinate-г дэлгэцийн цэг болгон хөрвүүлэх
     *
     * @param coordinate Grid дээрх координат
     * @param grid       Тухайн grid
     * @return Дэлгэцийн цэг (Point)
     */
    public static Point toScreenPoint(GridCoordinate coordinate, IGrid grid) {
        return coordinate.toScreenPoint(grid);
    }

    /**
     * GridCoordinate-г хүчинтэй эсэхийг шалгах
     *
     * @param coordinate Шалгах координат
     * @param grid       Тухайн grid
     * @return Хүчингүй бол false, эсвэл true
     */
    public static boolean isValidCoordinate(GridCoordinate coordinate, IGrid grid) {
        return coordinate.isWithinBounds(grid);
    }

    /**
     * GridCoordinate-г хүснэгтэд хэвлэхэд зориулж форматлах
     *
     * @param coordinate GridCoordinate
     * @return Хэвлэхэд тохиромжтой текст
     */
    public static String formatForDisplay(GridCoordinate coordinate) {
        return coordinate.toString();
    }
}