package yorkshiredalesmtb;

public class YorkshireDalesMTB {
        
    public static boolean speed = true;
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.begin();
    }

    
}
