package yorkshiredalesmtb;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class VisitorControl implements Runnable {

    Random rand = new Random();
    Visitor visitor;
    ShopMonitor shop;

    public VisitorControl(Visitor v, ShopMonitor sm) {
        visitor = v;
        shop = sm;
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
        for (int i = 1; i < 4; i++) {
            if (rand.nextBoolean()) {
                switch (i) {
                    case 0:
                        items[i] = Item.BIKE;
                        rents = true;
                        break;
                    case 1:
                        items[i] = Item.GLOVES;
                        rents = true;
                        break;
                    case 2:
                        items[i] = Item.HELMET;
                        rents = true;
                        break;
                    case 3:
                        items[i] = Item.JACKET;
                        rents = true;
                        break;
                }
            }
        }
        
        if (rents) {
            shop.getItems(items);
            visitor.setItems(items);
            visitor.setStatus(Status.REQUESTING);
            System.out.println(Thread.currentThread().getName() + " requests to borrow " + Arrays.toString(items));
            cycle();
        } else {
            requestItems();
        }
    }

    private void cycle() {
        int sleepTime = (int) ((Math.random() * (5 - 1)) + 1);
        System.out.println(Thread.currentThread().getName() + " cycles for " + sleepTime + " seconds.");
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        visitor.setStatus(Status.CYCLING);
        returnItems();
    }

    private void returnItems() {
        System.out.println(Thread.currentThread().getName() + " returns items " + Arrays.toString(visitor.getItems()));
        visitor.returnItems(shop);
        visitor.setStatus(Status.RETURNING);
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
    }
}
