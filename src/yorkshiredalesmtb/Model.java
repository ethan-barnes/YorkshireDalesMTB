/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;

/**
 * https://stackoverflow.com/questions/59263498/adding-swing-gui-to-existing-working-java-program
 * @author barne
 *
 * Model contains the information for the view and information from the view
 * as well as the logic.
 * The model is independent of the user interface.
 */
public class Model {

    public Item[] getItems(ShopMonitor sm) {
        return sm.getItems();
    }
}
