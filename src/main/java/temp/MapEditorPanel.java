package temp;

import AllInterface.IGridController;
import controller.GridController;
import main.MapEditorModel;
import main.MapEditorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

//Main
public class MapEditorPanel extends JPanel {
    // MapEditorModel is main of all model
    private final MapEditorModel model;
    // MapEditorView is main of all view
    private final MapEditorView view;
    // Interface GridController is that setupListeners in MapEditorPanel x,y coordinate convert new Point
    private final IGridController gridController;
    //
    private final ButtonAction action;

    public MapEditorPanel(MapEditorModel model) {
        this.model = model;
        this.view = new MapEditorView();
        this.gridController = new GridController(model);
        this.action = new ButtonController(model);

        setupPanel();
        setupListeners();
        setupButtonListeners();
        model.setUpdateCallback(ignored -> repaint());
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(1056, 696));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
    }

    private void setupListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gridController.handleMouseAction(e.getPoint());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                gridController.handleMouseDrag(e.getPoint());
            }
        });
    }

    private void setupButtonListeners() {
        List<JButton> buttons = view.getButtons();

        buttons.get(0).addActionListener(e -> {
            action.up(); repaint();
        });
        buttons.get(1).addActionListener(e -> {
            action.down(); repaint();
        });
        buttons.get(2).addActionListener(e -> {
            action.left(); repaint();
        });
        buttons.get(3).addActionListener(e -> {
            action.right(); repaint();
        });
        buttons.get(4).addActionListener(e -> {
            action.zoomIn(); repaint();
        });
        buttons.get(5).addActionListener(e -> {
            action.zoomOut(); repaint();
        });
        buttons.get(6).addActionListener(e -> {
            action.fill(); repaint();
        });
        buttons.get(7).addActionListener(e -> {
            action.clear(); repaint();
        });
        buttons.get(8).addActionListener(e -> {
            action.switchup(); repaint();
        });
        buttons.get(9).addActionListener(e -> {
            action.switchdown(); repaint();
        });

        buttons.forEach(this::add);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        view.render(g, model);
    }
}
