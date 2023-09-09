/*
Luhang Sun
CS231 Project 5
RandomCustomerSimulation.java
*/

import java.util.*;

public class RandomCustomerSimulation {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1){
            System.out.println("please supply the maximum number of items for customers.");
            return;
        }

        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i = 0;i < 5;i ++) {
            CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
            checkouts.add( checkout );
        }
        Landscape scape = new Landscape(500,500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        int num_items = Integer.parseInt(args[0]);
        
        for (int j = 0; j < 100; j++) {
            for (int k=0; k<100; k++){
                Customer cust = new RandomCustomer(1+gen.nextInt(num_items));
                int choice = cust.chooseLine( checkouts );
                checkouts.get(choice).addCustomerToQueue( cust );
                scape.updateCheckouts();
                display.repaint();
                Thread.sleep(10);
            }
            scape.printFinishedCustomerStatistics();
        }
        System.out.println("done!!");
    }
}