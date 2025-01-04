package main;

import AllInterface.*;
import AllInterface.Action;
import controller.GridController;
import controller.button.*;
import controller.menuBar.*;
import view.MapMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class MapEditor extends JFrame {

    // Model and view
    private final MapEditorModel model;
    private final MapEditorView view;

    // Controllers
    private final IGridController gridController;
    private final List<Action> actions;
    // Menu bar
    private final MapMenuBar mapMenuBar;

    public MapEditor() {
        // Initialize model and view
        this.model = new MapEditorModel();
        this.view = new MapEditorView();
        this.actions = new ArrayList<>();

        // Initialize controllers
        this.gridController = new GridController(model);

        // Initialize menu bar
        this.mapMenuBar = new MapMenuBar();
        setJMenuBar(mapMenuBar);

        // Setup frame
        initializeFrame();
        initializeComponents();
        finalizeSetup();
        initializeMenuBarController();
        initializeListener();
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Simple 2D Tile Editor version 1.01");
    }
    private void initializeComponents() {
        add(new MapEditorPanel());
        pack();
    }
    private void initializeMenuBarController() {
        actions.add(new TileImportController());
        actions.add(new ImportTileSheetController());
        actions.add(new SaveMapController());
        actions.add(new SaveMapAsController());
        actions.add(new LoadMapController());
        actions.add(new SplashOnEntireMap());
        actions.add(new EraseAllTilesOnMap());
        actions.add(new GridOnOff());
        actions.add(new AboutController());
    }
    private void initializeListener() {
        String[] menuItems = {
                "Import Tile",
                "Import Tile Sheet",
                "Save Map",
                "Save Map As",
                "Load Map",
                "Splash On Entire Map",
                "Erase All Tiles On Map",
                "Grid On/Off",
                "About"
        };

        for (int i = 0; i < menuItems.length; i++) {
            int actionIndex = i; // Ламбда дотор ашиглахын тулд локал хувьсагч хэрэгтэй
            mapMenuBar.addActionListenerToMenuItem(menuItems[i],
                    e -> actions.get(actionIndex).action(this));
        }
    }

    private void finalizeSetup() {
        setLocationRelativeTo(null); // Center on screen
        setVisible(true); // Make the frame visible
    }

    public MapEditorModel getModel() {
        return model;
    }

    private class MapEditorPanel extends JPanel {

        private final List<Click> clicks;

        public MapEditorPanel() {
            clicks = new ArrayList<>();
            setupPanel();
            setupListeners();
            setupButtonListeners();
            model.setUpdateCallback(ignored -> repaint());
            initializeButtonController();
        }
        private void initializeButtonController() {
            clicks.addAll(List.of(
                    new Up(),
                    new Down(),
                    new Left(),
                    new Right(),
                    new ZoomIn(),
                    new ZoomOut(),
                    new Fill(),
                    new Clear(),
                    new SwitchUp(),
                    new SwitchDown()
            ));
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
            int index = 0;
            for (JButton button : buttons) {
                int finalIndex = index;
                button.addActionListener(e -> {
                    clicks.get(finalIndex).onClick(model);
                    repaint();
                });
                index++;
            }
            buttons.forEach(this::add);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            view.render(g, model);
        }
    }
}
