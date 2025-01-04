package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;
import model.ImageModel;
import model.TileToolBar;

import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class TileImportController implements Action {

    private int tile = 0;
    @Override
    public void action(MapEditor mapEditor) {
        ImageModel imageModel = mapEditor.getModel().getImageModel();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(mapEditor);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            File[] imageFiles = selectedFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") ||
                    name.toLowerCase().endsWith(".png") ||
                    name.toLowerCase().endsWith(".gif"));
            if (imageFiles != null) {
                imageModel.clearImages();  // Clear previous images
                for (File imageFile : imageFiles) {
                    try {
                        BufferedImage img = ImageIO.read(imageFile);
                        //Image resizedImage = img.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(img);
                        imageModel.addImage(icon);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mapEditor, "Error loading image: " + imageFile.getName(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        setupTile(mapEditor.getModel().getImageModel().getImageList().size(), mapEditor);
        mapEditor.getModel().repaint();
    }

    private void setupTile(int size, MapEditor mapEditor) {
        for (int i = 0; i < mapEditor.getModel().getTileToolBar().getInitCapacity(); i++) {
            boolean check = addTile(i, size, mapEditor);
            if (!check) break;
        }

    }

    private boolean addTile(int index, int size, MapEditor mapEditor) {
        TileToolBar<Integer> toolBar = mapEditor.getModel().getTileToolBar();
        for (int row = 0; row < toolBar.getRows(); row++) {
            for (int col = 0; col < toolBar.getCols(); col++) {
                if (tile == size) {
                    return false;
                }
                toolBar.addTileData(index, tile, row, col);
                tile++;
            }
        }
        return true;
    }
}

