package yorkshiredalesmtb;

public class Model {

    private int visitorNum;
    private Item[] items;

    public Item[] getItems() {
        return items;
    }
    
    public void setItems(int bike, int gloves, int helmet, int jacket) {
        int size = bike + gloves + helmet + jacket;
        items = new Item[size];
        for(int i = 0; i < bike; i++) {
            items[i] = Item.BIKE;
        }
        for(int i = bike; i < bike + gloves; i++) {
            items[i] = Item.GLOVES;
        }
        for(int i = bike + gloves; i < bike + gloves + helmet; i++) {
            items[i] = Item.HELMET;
        }
        for(int i = bike + gloves + helmet; i < bike + gloves + helmet + jacket; i++) {
            items[i] = Item.JACKET;
        }
    }

    public int getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(int vNum) {
        visitorNum = vNum;
    }
}
