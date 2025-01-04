package util;

import main.MapEditor;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveAsFileUtility {
    public static void saveAs(MapEditor mapEditor) {
        // JFileChooser ашиглан файл хадгалах байршил болон нэрийг сонгох
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Хадгалах байршил болон файлын нэрийг сонгоно уу");

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                for (List<Integer> row : mapEditor.getModel().getTileData().getData()) {
                    for (Integer element : row) {
                        if (element == null)
                            element = -1;
                        writer.write(element + " "); // Элементийг бичих
                    }
                    writer.newLine(); // Дараагийн мөр рүү шилжих
                }
                System.out.println("Файл амжилттай хадгалагдлаа: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Файлд бичихэд алдаа гарлаа: " + e.getMessage());
            }
        } else {
            System.out.println("Хэрэглэгч хадгалах үйлдлийг цуцаллаа.");
        }
    }
}
