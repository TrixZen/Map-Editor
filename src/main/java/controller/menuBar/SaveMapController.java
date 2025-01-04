package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;
import util.SaveAsFileUtility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveMapController implements Action {

    @Override
    public void action(MapEditor mapEditor) {
        String fileName = mapEditor.getModel().getSavePath().getFile();
        if (fileName == null) {
            SaveAsFileUtility.saveAs(mapEditor);
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (List<Integer> row : mapEditor.getModel().getTileData().getData()) {
                for (Integer element : row) {
                    if (element == null)
                        element = -1;
                    writer.write(element + " "); // Элементийг бичих
                }
                writer.newLine(); // Дараагийн мөр рүү шилжих
            }
            System.out.println("Файл амжилттай хадгалагдлаа: " + fileName);
        } catch (IOException e) {
            System.err.println("Файлд хадгалах явцад алдаа гарлаа: " + e.getMessage());
        }
    }
}
