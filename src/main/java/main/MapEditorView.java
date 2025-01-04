package main;

import AllInterface.*;
import model.ImageModel;
import model.TileData;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapEditorView {
    private final IGridView gridView;
    private final ITileView tileView;
    private final IInformationView informationView;
    private final IButtonView buttonView;

    public MapEditorView() {
        this.gridView = new GridView();
        this.tileView = new ImageGridDrawer();
        this.informationView = new InformationView();
        this.buttonView = new ButtonView();
    }

    public void render(Graphics g, MapEditorModel model) {


        // Render tiles
        ICoordinate coord = model.getCoordinates().get(1);
        ImageModel imageModel = model.getImageModel();
        TileData<Integer> tileData = model.getTileData();
        TileData<Integer> tileToolbar = model.getTileToolBar().getTileToolBar(model.getTileToolBarIndex());

        tileView.render(g, imageModel, tileData, model.getActiveGrid().get(0), coord.row(), coord.col()); // draw Map
        tileView.render(g, imageModel, tileData, model.getActiveGrid().get(2), 0, 0); // background Map
        tileView.render(g, imageModel, tileToolbar, model.getGrids().get(2),0, 0);
        tileView.render(g, imageModel, model.getSelectedTile(), model.getGrids().get(5), 0, 0);

        // Render information
        model.getInformation().forEach(informationModel -> informationView.render(g, informationModel));
        // Render grids
        for (IGrid grid : model.getGrids()) {
            if (!model.getDrawMapGrid() && grid.equals(model.getGrids().get(1))) {
                continue;
            }
            gridView.render(g, grid);
        }
    }
    public List<JButton> getButtons() {
        return buttonView.initializeButton();
    }
}
