package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;

public class GridOnOff implements Action {
    private boolean draw = false;
    @Override
    public void action(MapEditor mapEditor) {
        mapEditor.getModel().setDrawMapGrid(draw);
        draw = !draw;
        mapEditor.getModel().repaint();
    }
}
