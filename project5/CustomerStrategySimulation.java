/*
Luhang Sun
CS231 Project 5
CustomerStrategySimulation.java
*/
import java.util.*;

public class CustomerStrategySimulation {
    //A simulation where each customer can use a random strategy and compute the statistics of time taken for each type of customer

    public static void main (String [] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Random ran = new Random();

        //getting use input of no.of customers and checkout lines
        System.out.println("Beginning Simulation where Customers randomly pick their strategies. \nEnter the number of total Customers in simulation: ");
        int num_cust = Integer.parseInt(input.nextLine());

        System.out.println("Enter the maximum number of items each Customers can hold:  ");
        int max_items = Integer.parseInt(input.nextLine());
        input.close();

        ArrayList <CheckoutAgent> checkouts = new ArrayList <CheckoutAgent> (5);
        Landscape scape = new Landscape (500, 550, checkouts);

        for(int i = 0; i < 5; i++) {
            CheckoutAgent ca = new CheckoutAgent( i*100+50, 450 );
            checkouts.add(ca);
        }

        LandscapeDisplay display = new LandscapeDisplay(scape);

        Customer cust;
        for (int k = 0; k<num_cust; k++){
            int strategy = ran.nextInt(4);
            if (strategy == 0)
                cust = new RandomCustomer(1+ran.nextInt(max_items));   
            
            else if (strategy == 1)
                cust = new PickyCustomer(1+ran.nextInt(max_items), checkouts.size());
            
            else if (strategy == 2)
                cust = new Pick2Customer(1+ran.nextInt(max_items));
        
            else
                cust = new CountItemCustomer(1+ran.nextInt(max_items), scape.totalCust());

            int choice = cust.chooseLine(checkouts);
            checkouts.get(choice).addCustomerToQueue(cust);
            scape.updateCheckouts2();
            display.repaint();
            Thread.sleep(1);

            if (k % (num_cust/10) == 0 && k != 0){
                scape.printStrategyStats();
            }    
        }
        System.out.println("done!!");
    }
}