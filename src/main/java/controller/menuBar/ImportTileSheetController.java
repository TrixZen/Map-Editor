package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImportTileSheetController implements Action {
    @Override
    public void action(MapEditor mapEditor) {
        // Create a file chooser for importing the image
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif"));

        int returnValue = fileChooser.showOpenDialog(mapEditor);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                ImageIcon icon = new ImageIcon(img);
                mapEditor.getModel().getImageModel().addImage(icon);
                mapEditor.getModel().repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mapEditor, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
