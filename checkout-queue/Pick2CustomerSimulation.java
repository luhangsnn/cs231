/*
Luhang Sun
CS231 Project 5
Pick2CustomerSimulation.java
*/

import java.util.*;

public class Pick2CustomerSimulation {
    //simulate customers that pick 2 lines randomly and pick the shorter one

    public static void main (String [] args) throws InterruptedException {
        if (args.length < 1){
            System.out.println("please supply the maximum number of items for customers.");
            return;
        }

        ArrayList <CheckoutAgent> checkouts = new ArrayList <CheckoutAgent> (5);
        Landscape scape = new Landscape (500, 500, checkouts);
        Random ran = new Random();
        int num_items = Integer.parseInt(args[0]);

        for(int i = 0; i < 5; i ++) {
            CheckoutAgent ca = new CheckoutAgent( i*100+50, 480 );
            checkouts.add(ca);
        }

        LandscapeDisplay display = new LandscapeDisplay(scape);

        //adding 100 pick 2 customers
        for (int j = 0; j < 100; j++) {
            for (int k=0; k<100; k++){
                Customer cust = new Pick2Customer(1+ran.nextInt(num_items));
                int choice = cust.chooseLine(checkouts);
                checkouts.get(choice).addCustomerToQueue(cust);
                scape.updateCheckouts();
                display.repaint();
                Thread.sleep(10);
            }
            scape.printFinishedCustomerStatistics();
        }
        System.out.println("done!!");
    }
}