package yorkshiredalesmtb;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class VisitorControl implements Runnable {

    Random rand = new Random();
    Visitor visitor;
    ShopMonitor shop;
    Controller controller;

    public VisitorControl(Visitor v, ShopMonitor sm, Controller controller) {
        visitor = v;
        shop = sm;
        this.controller = controller;
    }

    @Override
    public void run() {
        if (rand.nextBoolean()) {
            requestItems();
        } else {
            walk();
        }
    }

    private void requestItems() {
        boolean rents = false;
        Item[] items = new Item[4];
        for (int i = 0; i < 4; i++) {
            if (rand.nextBoolean()) {
                switch (i) {
                    case 0 -> {
                        items[i] = Item.BIKE;
                        rents = true;
                    }
                    case 1 -> {
                        items[i] = Item.GLOVES;
                        rents = true;
                    }
                    case 2 -> {
                        items[i] = Item.HELMET;
                        rents = true;
                    }
                    case 3 -> {
                        items[i] = Item.JACKET;
                        rents = true;
                    }
                }
            }
        }

        if (rents) { // Only request from shop if visitor wants item/s.
            visitor.setStatus(Status.REQUESTING, items);
            System.out.println(Thread.currentThread().getName() + " requests to borrow " + Arrays.toString(items));
            if (shop.takeItems(items)) { // If items requested are available.
                visitor.setItems(items);
                System.out.println(Thread.currentThread().getName() + " successfully borrows " + Arrays.toString(items));
                controller.changeShopList();
                cycle();
            } else {
                run();
            }
        } else {
            run();
        }
    }

    private void cycle() {
        visitor.setStatus(Status.CYCLING);
        int sleepTime = (int) ((Math.random() * (5 - 1)) + 1);
        System.out.println(Thread.currentThread().getName() + " cycles for " + sleepTime + " seconds.");
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        returnItems();
    }

    private void returnItems() {
        System.out.println(Thread.currentThread().getName() + " returns items " + Arrays.toString(visitor.getItems()));
        visitor.setStatus(Status.RETURNING, visitor.getItems());
        visitor.returnItems(shop);
        controller.changeShopList();
        run();
    }

    private void walk() {
        int sleepTime = (int) ((Math.random() * (5 - 1)) + 1);
        System.out.println(Thread.currentThread().getName() + " walks around for " + sleepTime + " seconds.");
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        visitor.setStatus(Status.WALKING);
        run();
    }
}
