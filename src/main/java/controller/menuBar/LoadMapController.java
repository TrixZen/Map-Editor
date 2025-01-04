package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadMapController implements Action {

    @Override
    public void action(MapEditor mapEditor) {
        // JFileChooser ашиглан файл унших байршил болон файлын нэрийг сонгох
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Унших файлыг сонгоно уу");

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToRead = fileChooser.getSelectedFile();
            mapEditor.getModel().getSavePath().setFile(fileToRead.getAbsolutePath());
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
                String line;
                List<List<Integer>> data = new ArrayList<>(); // Түр зуурын хэмжээ

                while ((line = reader.readLine()) != null) {
                    String[] elements = line.trim().split(" ");
                    List<Integer> rowMap = new ArrayList<>();
                    for (String element : elements) {
                        int number = Integer.parseInt(element);
                        rowMap.add( number != -1 ? number : null);
                    }
                    data.add(rowMap);
                }

                System.out.println("Файл амжилттай уншигдлаа: " + fileToRead.getAbsolutePath());
                mapEditor.getModel().getTileData().setData(data);

            } catch (IOException | NumberFormatException e) {
                System.out.println("Файл уншихад алдаа гарлаа: " + e.getMessage());
            }
        } else {
            System.out.println("Хэрэглэгч унших үйлдлийг цуцаллаа.");
        }
        mapEditor.getModel().repaint();
    }
}
