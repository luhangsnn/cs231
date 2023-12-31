/*
    Stephanie Taylor (Spring 2019)
    TestPickyCustomer.java
    Purpose: to test the line-choosing strategy of the PickyCustomer by visualizing
    the lines as customers join them. No customers leave.
*/
import java.util.Random;
import java.util.ArrayList;

public class TestPickyCustomer {
    // test function that creates a new LandscapeDisplay and populates it with 5 checkouts and 99 customers.
    public static void main(String[] args) throws InterruptedException {
        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i=0;i<5;i++) {
            CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
            checkouts.add( checkout );
        }
        Landscape scape = new Landscape(500,500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        
        for (int j = 0; j < 100; j++) {
            
            Customer cust = new PickyCustomer(1+gen.nextInt(10), checkouts.size());
            int choice = cust.chooseLine( checkouts );
            checkouts.get(choice).addCustomerToQueue( cust );
            display.repaint();
            Thread.sleep( 250 );
        }

    }

}