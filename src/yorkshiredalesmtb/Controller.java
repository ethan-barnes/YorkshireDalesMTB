package yorkshiredalesmtb;

public class Controller {
    
    private final Model model;
    private final View view;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;        
    }
    
    public void begin() {
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
