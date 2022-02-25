package yorkshiredalesmtb;

public class Visitor {
    
    private Status currentStatus;
    private Item[] items = new Item[4];
    private final Controller controller;
    private boolean speed = true;

    public Visitor(Controller controller, boolean speed) {
        currentStatus = null;
        this.controller = controller;
        this.speed = speed;
    }
    
    public boolean getSpeed() {
        return speed;
    }
    
    public void setSpeed(Boolean s) {
        speed = s;
    }

    public Item[] getItems() {
        return items;
    }

    public boolean addItem(Item item) {
        var nullPos = getItemPos(null);
        if (nullPos >= 0) {
            items[nullPos] = item;
            return true;
        }
        return false;
    }

    private int getItemPos(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public void returnItems(ShopMonitor shop) {
        shop.returnItems(items);
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        }
    }

    public void setItems(Item[] i) {
        items = i;
    }

    public void setStatus(Status s) {
        currentStatus = s;
        controller.changeStatus(this, Thread.currentThread().getName());
    }
    
    public void setStatus(Status s, Item[] items) {
        currentStatus = s;
        controller.changeStatus(this, Thread.currentThread().getName(), items);
    }

    public Status getStatus() {
        return currentStatus;
    }

}
