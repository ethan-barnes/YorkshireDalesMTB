package yorkshiredalesmtb;

public class YorkshireDalesMTB {

    public static void main(String[] args) {
        // Contents of the shop
//        Item items[] = {
//            Item.BIKE,
//            Item.BIKE,
//            Item.BIKE,
//            Item.BIKE,
//            Item.GLOVES,
//            Item.GLOVES,
//            Item.JACKET,
//            Item.JACKET,
//            Item.HELMET,
//            Item.HELMET,
//            Item.HELMET
//        };
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }

    
}
