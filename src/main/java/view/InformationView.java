package view;

import AllInterface.IInformationModel;
import AllInterface.IInformationView;

import java.awt.*;

public class InformationView implements IInformationView {

    @Override
    public void render(Graphics g, IInformationModel model) {
        g.setColor(Color.lightGray);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(model.getMessage(), model.getX(), model.getY());
    }
}
