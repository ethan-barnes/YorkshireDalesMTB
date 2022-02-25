package yorkshiredalesmtb;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.*;

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
    private final ThreadController threadController = new ThreadController();

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
    }

    private void createMainWindow() {
        model.setVisitorNum(Math.abs(model.getVisitorNum()));
        panel = new JPanel(new GridLayout(/*3 + model.getVisitorNum()*/0, 3));

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
        JLabel visitorLabel = new JLabel("   Visitors");
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
                threadController.runProgram(model, controller, new ShopMonitor(model.getItems()));
            } catch (NumberFormatException ex) {
                System.out.println("Error with inputted values. " + ex);
            }
        });
        panel.add(submit);
    }

    private void createButtons() {
        JButton addVisitorBtn = new JButton("Add visitor");
        addVisitorBtn.addActionListener((ActionEvent e) -> {
            model.setVisitorNum(model.getVisitorNum() + 1);
            createVisitors();
            threadController.addVisitor();
        });

        JButton slowBtn = new JButton("Slower");
        slowBtn.addActionListener((ActionEvent e) -> {
            threadController.setSpeed(false);
        });
        
        JButton fastBtn = new JButton("Faster");
        fastBtn.addActionListener((ActionEvent e) -> {
            threadController.setSpeed(true);
        });
        panel.add(addVisitorBtn);
        panel.add(slowBtn);
        panel.add(fastBtn);
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
        panel.add(new JLabel()); // blank space for formatting
        panel.add(shopList);
    }

    private void createVisitors() {
        for (int i = 0; i < model.getVisitorNum(); i++) {
            try {
                panel.remove(visitors[i]);
            } catch (Exception e) {
                // this happens first time anyway
            }
        }
        visitors = new JLabel[model.getVisitorNum()];
        for (int i = 0; i < model.getVisitorNum(); i++) {
            visitors[i] = new JLabel("Visitor " + (i + 1));
            panel.add(visitors[i]);
        }
    }

    private void createElements() {
        createButtons();
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
