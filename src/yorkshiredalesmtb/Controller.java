/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;
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
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;        
        view.createAndShowGUI(model, this);
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
        view.updateShopList();
    }
    
    
}
