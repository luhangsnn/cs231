/*
Luhang Sun
CS231 Project 5
CountItemCustomerSimulation.java
*/

import java.util.*;

public class CountItemCustomerSimulation {
    //simulate customers that pick the shortest line

    public static void main (String [] args) throws InterruptedException {
        if (args.length < 1){
            System.out.println("please supply the maximum number of items for customers.");
            return;
        }

        ArrayList <CheckoutAgent> checkouts = new ArrayList <CheckoutAgent> (5);
        Landscape scape = new Landscape (500, 500, checkouts);
        Random ran = new Random();
        int max_items = Integer.parseInt(args[0]);

        for(int i = 0;i < 5;i ++) {
            CheckoutAgent ca = new CheckoutAgent( i*100+50, 480 );
            checkouts.add(ca);
        }

        LandscapeDisplay display = new LandscapeDisplay(scape);

        for (int k =0; k<10000; k++){
            Customer cust = new CountItemCustomer(1+ran.nextInt(max_items), scape.totalCust());

            int choice = cust.chooseLine(checkouts);
            checkouts.get(choice).addCustomerToQueue(cust);
            scape.updateCheckouts();
            display.repaint();
            Thread.sleep(10);
            if (k % 100 == 0) {
                scape.printFinishedCustomerStatistics();
            }   
        }
        System.out.println("done!!");
    }
}