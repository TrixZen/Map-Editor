package view;

import AllInterface.IButtonView;
import model.ButtonModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonView implements IButtonView {

    @Override
    public List<JButton> initializeButton() {
        List<JButton> buttons = new ArrayList<>();
        // Dynamically create buttons for each enum value
        for (ButtonModel action : ButtonModel.values()) {
            // Зургийг ачаалах
            ImageIcon originalIcon = new ImageIcon(action.getLabel());
            Image scaledImage = originalIcon
                    .getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            JButton button = getButtonConfiguration(action, scaledImage);

            buttons.add(button);
        }
        return buttons;
    }

    private static JButton getButtonConfiguration(ButtonModel action, Image scaledImage) {
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        // Initialize buttons with icons
        JButton button = new JButton(scaledIcon);

        // Товчлуурын хэмжээ ба байрлыг тохируулах тохируулах
        button.setBounds(action.getX(), action.getY(), 36,36);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        return button;
    }
}

