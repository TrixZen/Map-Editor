package controller.button;

import AllInterface.Click;
import main.MapEditorModel;

public class SwitchUp implements Click {
    @Override
    public void onClick(MapEditorModel model) {

        model.setTileToolBarIndex(-1);
    }
}
