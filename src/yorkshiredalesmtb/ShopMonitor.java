package yorkshiredalesmtb;

public class ShopMonitor {

    private Item[] items;

    public ShopMonitor(Item[] itemArray) {
        items = itemArray;
    }

    public synchronized void returnItems(Item[] items) {
        for (Item item : items) {
            if (item != null) {
                int itemPos = getItemPos(null);
                if (itemPos >= 0) {
                    this.items[itemPos] = item;
                }
            }
        }
    }

    public synchronized void getItems(Item[] items) {
        for (Item item : items) {
            if (item != null) {
                int itemPos = getItemPos(item);
                if (itemPos >= 0) {
                    this.items[itemPos] = null;
                }
            }
        }
    }

    public synchronized boolean getItem(Item item) {
        int itemPos = getItemPos(item);
        if (itemPos >= 0) { // Check that item is available.
            items[itemPos] = null;
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

}
