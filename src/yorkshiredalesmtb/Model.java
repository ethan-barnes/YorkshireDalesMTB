/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;

/**
 * https://stackoverflow.com/questions/59263498/adding-swing-gui-to-existing-working-java-program
 *
 * @author barne
 *
 * Model contains the information for the view and information from the view as
 * well as the logic. The model is independent of the user interface.
 */
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
