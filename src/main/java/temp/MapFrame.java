package temp;

import main.MapEditorModel;
import view.MapMenuBar;

import javax.swing.*;

public class MapFrame extends JFrame {
    private MapMenuBar mapMenuBar;
    //private MapPanel mapPanel;
    private MapEditorModel model;

    public MapFrame() {
        initializeFrame();
        initializeComponents();
        finalizeSetup();
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Simple 2D Tile Editor version 1.01");
    }

    private void initializeComponents() {
        mapMenuBar = new MapMenuBar();
        setJMenuBar(mapMenuBar);
        model = new MapEditorModel();
        this.add(new MapEditorPanel(model));
        this.pack();
    }

    private void finalizeSetup() {
        setLocationRelativeTo(null); // Center on screen
        setVisible(true); // Make the frame visible
    }

    public void setModel(MapEditorModel model) {
        this.model = model;
    }

    public MapMenuBar getMapMenuBar() {
        return mapMenuBar;
    }

    public MapEditorModel getModel() {
        return model;
    }
}
