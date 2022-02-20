/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yorkshiredalesmtb;

/**
 *
 * @author barne
 */
public class ThreadRunner {

    public void runProgram(Model model, Controller controller, ShopMonitor shop) {
        Thread threads[] = new Thread[model.getVisitorNum()];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new VisitorControl(new Visitor(controller), shop, controller), "Visitor " + (i + 1));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
