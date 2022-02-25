package yorkshiredalesmtb;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShopMonitor {
    
    private Item[] items;
    
    public ShopMonitor(Item[] itemArray) {
        items = itemArray;
    }
    
    public Item[] getItems() {
        return items;
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
    
    public synchronized boolean takeItems(Item[] items, Boolean speed) {
        Item[] cachedItems = this.items;
        for (Item item : items) {
            try {
                if (speed) {
                    TimeUnit.MILLISECONDS.sleep(100);
                } else {
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ShopMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (item != null) {
                int itemPos = getItemPos(item);
                if (itemPos < 0) {
                    System.out.println(Thread.currentThread().getName() + " " + item + " is not available.");
                    this.items = cachedItems; // Restoring item array.
                    return false;
                } else {
                    this.items[itemPos] = null;
                }
            }
        }
        return true;
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
