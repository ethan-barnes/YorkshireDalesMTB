package yorkshiredalesmtb;

import java.util.ArrayList;

public class ThreadController {

    private int numOfVisitors;
    private Model model;
    private Controller controller;
    ShopMonitor shop;
    private ArrayList<Thread> threads;

    public void runProgram(Model model, Controller controller, ShopMonitor shop) {
        this.model = model;
        this.controller = controller;
        this.shop = shop;

        numOfVisitors = model.getVisitorNum();
        threads = new ArrayList<>();
        for (int i = 0; i < numOfVisitors; i++) {
            threads.add(new Thread(new VisitorControl(new Visitor(controller, true), shop, controller), "Visitor " + (i + 1)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void addVisitor() {
        numOfVisitors++;
        Thread newVisitor = new Thread(new VisitorControl(new Visitor(controller, true), shop, controller), "Visitor " + numOfVisitors);
        threads.add(newVisitor);
        newVisitor.start();
    }

    public void setSpeed(boolean speed) {
        for (int i = 0; i < threads.size(); i++) {
            threads.set(i, new Thread(new VisitorControl(new Visitor(controller, speed), shop, controller), "Visitor " + (i + 1)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
