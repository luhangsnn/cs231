/*
Luhang Sun
CS231 Project 5
Pick2Customer.java
*/
import java.util.*;

public class Pick2Customer extends Customer{
    private Random ran = new Random();

    public Pick2Customer (int num_items){
        super (num_items, 2);
        strategy = 2;
    }

    public int chooseLine (ArrayList <CheckoutAgent> checkouts){
        int line1 = ran.nextInt(checkouts.size());
        int line2 = ran.nextInt(checkouts.size());
        
        while (line1 == line2) {
            line2 = ran.nextInt(checkouts.size());
        }

        if (checkouts.get(line1).getNumInQueue() <= checkouts.get(line2).getNumInQueue()){
            return line1;
        }
        else{
            return line2;
        }
    }
}