package yorkshiredalesmtb;

public class YorkshireDalesMTB {

    /**
     * @param args the command line arguments
     */
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
        Thread threads[] = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new VisitorControl(new Visitor(), shop), "Visitor " + (i+1));
        }
        for (Thread thread : threads) {
            thread.start();
        }

    }

}
