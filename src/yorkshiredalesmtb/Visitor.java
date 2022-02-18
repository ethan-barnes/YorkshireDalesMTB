package yorkshiredalesmtb;

public class Visitor {

    private Status currentStatus;
    private Item[] items = new Item[4];

    public Visitor() {
        currentStatus = null;
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
    }

    public Status getStatus() {
        return currentStatus;
    }

}
