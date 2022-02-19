package yorkshiredalesmtb;

public class YorkshireDalesMTB {

    public static void main(String[] args) {
        Item items[] = {
            Item.BIKE,
            Item.BIKE,
            Item.BIKE,
            Item.BIKE,
            Item.GLOVES,
            Item.GLOVES,
            Item.JACKET,
            Item.JACKET,
            Item.HELMET,
            Item.HELMET,
            Item.HELMET
        };
        ShopMonitor shop = new ShopMonitor(items);
        Thread threads[] = new Thread[5]; // Number of visitors
        
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view, shop);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new VisitorControl(new Visitor(controller), shop, controller), "Visitor " + (i + 1));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
