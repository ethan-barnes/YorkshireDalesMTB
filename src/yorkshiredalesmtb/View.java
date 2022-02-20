/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

/**
 * https://stackoverflow.com/questions/59263498/adding-swing-gui-to-existing-working-java-program
 *
 * @author barne
 *
 * View only contains the user interface part
 */
public class View {

    private Controller controller;
    private Model model;
    private JList<String> statusList;
    private JList<String> shopList;
    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JScrollBar scrollBar;
    private JLabel[] visitors;
    private DefaultListModel<String> shopListVals;
    public DefaultListModel<String> statusListVals;
    private final ThreadRunner tr = new ThreadRunner();

    public void createAndShowGUI(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;

        panel = new JPanel(new GridLayout(8, 2));
        createInputElements();

        frame = new JFrame("Parameter entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setVisible(true);

        //createMainWindow();
    }

    private void createMainWindow() {
        if (model.getVisitorNum() > 10) {
            model.setVisitorNum(10);
        } else {
            model.setVisitorNum(Math.abs(model.getVisitorNum()));
        }
        panel = new JPanel(new GridLayout(0, 2 + model.getVisitorNum()));

        createElements();

        frame = new JFrame("Yorkshire Dales MTB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1600, 800);
        frame.setVisible(true);
    }

    private void createInputElements() {
        JLabel bikeLabel = new JLabel("   Bikes");
        JTextField bikeField = new JTextField("4");
        JLabel glovesLabel = new JLabel("   Gloves");
        JTextField glovesField = new JTextField("2");
        JLabel helmetLabel = new JLabel("   Helmets");
        JTextField helmetField = new JTextField("2");
        JLabel jacketLabel = new JLabel("   Jackets");
        JTextField jacketField = new JTextField("3");
        JLabel visitorLabel = new JLabel("   Visitors (max. 10)");
        JTextField visitorField = new JTextField("6");

        panel.add(bikeLabel);
        panel.add(bikeField);
        panel.add(glovesLabel);
        panel.add(glovesField);
        panel.add(helmetLabel);
        panel.add(helmetField);
        panel.add(jacketLabel);
        panel.add(jacketField);
        panel.add(visitorLabel);
        panel.add(visitorField);

        JButton submit = new JButton("Submit");
        submit.addActionListener((ActionEvent e) -> {
            try {
                model.setVisitorNum(Integer.parseInt(visitorField.getText()));
                model.setItems(
                        Integer.parseInt(bikeField.getText()),
                        Integer.parseInt(glovesField.getText()),
                        Integer.parseInt(helmetField.getText()),
                        Integer.parseInt(jacketField.getText()));
                frame.dispose();
                createMainWindow();
                tr.runProgram(model, controller, new ShopMonitor(model.getItems()));
            } catch (NumberFormatException ex) {
                System.out.println("Error with inputted values. " + ex);
            }
        });
        panel.add(submit);
    }

    private void createStatusList() {
        statusListVals = new DefaultListModel<>();
        statusList = new JList<>(statusListVals);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(statusList);
        statusList.setLayoutOrientation(JList.VERTICAL);
        panel.add(scrollPane);
    }

    private void createShop() {
        shopListVals = new DefaultListModel<>();
        shopList = new JList<>(shopListVals);
        panel.add(shopList);
    }

    private void createVisitors() {
        JLabel visitor0 = new JLabel();
        JLabel visitor1 = new JLabel();
        JLabel visitor2 = new JLabel();
        JLabel visitor3 = new JLabel();
        JLabel visitor4 = new JLabel();
        JLabel visitor5 = new JLabel();
        JLabel visitor6 = new JLabel();
        JLabel visitor7 = new JLabel();
        JLabel visitor8 = new JLabel();
        JLabel visitor9 = new JLabel();

        JLabel visitorsStandBy[] = {
            visitor0,
            visitor1,
            visitor2,
            visitor3,
            visitor4,
            visitor5,
            visitor6,
            visitor7,
            visitor8,
            visitor9,};

        visitors = new JLabel[model.getVisitorNum()];
        // Copies number of visitors requested from visitorsStandBy into this.visitors.
        System.arraycopy(visitorsStandBy, 0, visitors, 0, model.getVisitorNum());

        for (int i = 0; i < visitors.length; i++) {
            visitors[i].setText("Visitor " + (i + 1));
            panel.add(visitors[i]);
        }
    }

    private void createElements() {
        createStatusList();
        createShop();
        createVisitors();
    }

    public synchronized void updateShopList() {
        Item[] items = model.getItems();
        shopListVals.clear();
        shopListVals.ensureCapacity(items.length);
        for (Item item : items) {
            if (item != null) {
                shopListVals.addElement(item.name());
            }
        }
    }

    public void changeStatus(Status status, String visitorName, Item[] items) {
        int vNum = Integer.parseInt(visitorName.substring(8)) - 1;
        switch (status) {
            case CYCLING -> {
                statusListVals.addElement(visitorName + " is cycling");
                visitors[vNum].setText(String.format("<html>%s <br/> is cycling.</html>", visitorName));
            }
            case RETURNING -> {
                statusListVals.addElement(visitorName + " is returning " + Arrays.toString(items));
                visitors[vNum].setText(String.format("<html>%s <br/> is returning %s.</html>", visitorName, Arrays.toString(items)));
            }
            case REQUESTING -> {
                statusListVals.addElement(visitorName + " is requesting " + Arrays.toString(items));
                visitors[vNum].setText(String.format("<html>%s <br/> is requesting %s.</html>", visitorName, Arrays.toString(items)));
            }
            case WALKING -> {
                statusListVals.addElement(visitorName + " is walking");
                visitors[vNum].setText(String.format("<html>%s <br/> is walking.</html>", visitorName));
            }
        }
        scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

}
