/*
Luhang Sun
CS231 Project 5
RandomCustomer.java
*/
import java.util.*;

public class RandomCustomer extends Customer {
    //spends one time-step choosing a random line

    private Random r = new Random();

    public RandomCustomer (int num_items){
        super(num_items, 1);
        strategy = 0;
    }

    public int chooseLine (ArrayList <CheckoutAgent> checkouts){
        return r.nextInt(checkouts.size());
    }
}