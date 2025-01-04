package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;
import util.SaveAsFileUtility;

public class SaveMapAsController implements Action {

    @Override
    public void action(MapEditor mapEditor) {
        SaveAsFileUtility.saveAs(mapEditor);
    }
}
