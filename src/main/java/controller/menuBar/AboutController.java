package controller.menuBar;

import AllInterface.Action;
import main.MapEditor;

import javax.swing.*;
import java.awt.*;

public class AboutController implements Action {
    @Override
    public void action(MapEditor mapEditor) {
        // JFrame үүсгэж, тохиргоог хийж байна
        JFrame frame = new JFrame("About");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // 300x300 хэмжээтэй JPanel үүсгэж байна
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.black); // Хар өнгийн арка

        JLabel label = getLabel();

        // JPanel руу JLabel нэмэх
        panel.add(label);
        // JFrame дээр JPanel-ийг нэмэх
        frame.add(panel);
        frame.pack();

        // JFrame-г харагдуулах
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JLabel getLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 20)); // Font тохируулах

        // HTML формат ашиглан текст оруулах, текстийн өнгийг цагаан болгох
        String text = """
                <html>
                    <div style='color:white;'>
                        Simple 2D Map Editor 1.01 version<br>
                        2024 оны 12 сарын 7 өдрөөс эхэлж<br>
                        хөгжүүлээд 2025 оны 1 сарын 4 дуусав.<br>
                        Developer: TrixZen
                    </div>
                </html>
                """;
        label.setText(text);

        // Текстийн багтаамжийг зөв зохион байгуулж, багтаах
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(280, 280)); // JLabel-ийн хэмжээ тохируулах

        return label;
    }
}
