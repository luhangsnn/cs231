/*
Luhang Sun
CS231 Project 5
PickyCustomer.java
*/
import java.util.*;

public class PickyCustomer extends Customer {
    public PickyCustomer (int num_items, int num_lines){
        super(num_items, num_lines);
        strategy = 1;
    }

    public int chooseLine(ArrayList <CheckoutAgent> checkouts){
        int index = 0;
        for (int i=0; i<checkouts.size(); i++) {
            if (checkouts.get(i).getNumInQueue() < checkouts.get(index).getNumInQueue()){
                index = i;
            }
        }
        return index;
    }
}