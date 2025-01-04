package view;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapMenuBar extends JMenuBar {
    private final Map<String, JMenuItem> menuItems = new HashMap<>();

    public MapMenuBar() {
        addMenus();
    }

    public void addMenus() {
        // Меню болон түүний элементийн нэрсийг тодорхойлох
        Map<String, String[]> menus = new LinkedHashMap<>();
        menus.put("File", new String[]{"New Project", "Import Tile", "Import Tile Sheet", "Save Map", "Save Map As", "Load Map"});
        menus.put("Map Size", new String[]{"50 x 50", "100 x 100", "250 x 250"});
        menus.put("View", new String[]{"Grid On/Off"});
        menus.put("Tools", new String[]{"Splash On Entire Map", "Erase All Tiles On Map"});
        menus.put("Help", new String[]{"Shortcut List","About"});

        // Давталт ашиглан меню болон элементийн жагсаалтыг үүсгэх
        menus.forEach((menuName, items) -> {
            JMenu menu = new JMenu(menuName); // Меню үүсгэх
            for (String itemName : items) {
                JMenuItem menuItem = new JMenuItem(itemName);
                // Меню элементийг Map-д хадгалах
                menuItems.put(itemName, menuItem);

                // Жишээ: "Exit" товчийг сонгоход програмыг хаах
                if ("Exit".equals(itemName)) {
                    menuItem.addActionListener(e -> System.exit(0));
                }

                menu.add(menuItem); // Менюд элементийг нэмэх
            }
            this.add(menu); // Меню бар руу нэмэх
        });
    }
    // Method to attach an action listener to a menu item by its name
    public void addActionListenerToMenuItem(String itemName, java.awt.event.ActionListener actionListener) {
        JMenuItem menuItem = menuItems.get(itemName);
        if (menuItem != null) {
            menuItem.addActionListener(actionListener);
        }
    }
//    // Меню элементэд хандах арга
//    public JMenuItem getMenuItem(String itemName) {
//        return menuItems.get(itemName);
//    }
}
