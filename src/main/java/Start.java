
import main.MapEditor;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MapEditor::new);
    }
}