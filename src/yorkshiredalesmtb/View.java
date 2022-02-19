/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;

import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*;

/**
 * https://stackoverflow.com/questions/59263498/adding-swing-gui-to-existing-working-java-program
 *
 * @author barne
 *
 * View only contains the user interface part
 */
public class View {

    private Model model;
    private JList<String> statusList;
    private JList<String> shopList;
    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JScrollBar scrollBar;
    private DefaultListModel<String> shopListVals;
    public DefaultListModel<String> statusListVals;

    public void createAndShowGUI(Model model) {
        this.model = model;
        panel = new JPanel(new GridLayout(0, 2));

        createLocations();
        
        frame = new JFrame("Yorkshire Dales MTB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    private void createStatusList() {
        statusListVals = new DefaultListModel<>();
        statusList = new JList<>(statusListVals);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(statusList);
        statusList.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane);
        //panel.add(statusList);
    }

    private void createShop() {
        shopListVals = new DefaultListModel<>();
        shopList = new JList<>(shopListVals);
        panel.add(shopList);
    }

    private void createLocations() {
        createStatusList();
        createShop();
    }

    public synchronized void updateShopList(ShopMonitor sm) {
        Item[] items = model.getItems(sm);
        shopListVals.clear();
        shopListVals.ensureCapacity(items.length);
        for (Item item : items) {
            if (item != null) {
                shopListVals.addElement(item.name());
            }
        }
    }

    public void changeStatus(Status status, String visitorName, Item[] items) {
        switch (status) {
            case CYCLING ->
                statusListVals.addElement(visitorName + " is cycling");
            case RETURNING ->
                statusListVals.addElement(visitorName + " is returning " + Arrays.toString(items));
            case REQUESTING ->
                statusListVals.addElement(visitorName + " is requesting " + Arrays.toString(items));
            case WALKING ->
                statusListVals.addElement(visitorName + " is walking");
        }
        scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

}
