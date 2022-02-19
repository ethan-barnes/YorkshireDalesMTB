/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;
import java.beans.*;
/**
 * https://stackoverflow.com/questions/59263498/adding-swing-gui-to-existing-working-java-program
 * @author barne
 *
 * The controller controls the view and model.
 * Based on the user action, the Controller calls methods in the View and Model
 * to accomplish the requested action.
 */
public class Controller {
    
    private final Model model;
    private final View view;
    private final ShopMonitor shopMonitor;
    
    public Controller(Model model, View view, ShopMonitor shopMonitor) {
        this.model = model;
        this.view = view;
        this.shopMonitor = shopMonitor;
        view.createAndShowGUI(model);
    }
    
    // Is called when visitor status changes.
    public void changeStatus(Visitor visitor, String visitorName) {
        Status status = visitor.getStatus();
        view.changeStatus(status, visitorName, null);        
    }
    
    public void changeStatus(Visitor visitor, String visitorName, Item[] items) {
        Status status = visitor.getStatus();
        view.changeStatus(status, visitorName, items);        
    }
    
    public void changeShopList() {
        view.updateShopList(shopMonitor);
    }
    
    
}
