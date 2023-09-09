/*
Luhang Sun
CS231 Project 5
CountItemCustomer.java
*/

// A class of Customers pick lines according to the number of total items in each line
//i.e. picking the line that has the smallest number of total items

import java.util.*;

public class CountItemCustomer extends Customer {
    public CountItemCustomer (int num_items, int num_cust){
        super (num_items, num_cust);
        strategy = 3;
    }

    public int chooseLine(ArrayList <CheckoutAgent> checkouts) {
        int index = 0;
        for (int i=0; i<checkouts.size(); i++){
            if (checkouts.get(i).getTotalItem() < checkouts.get(index).getTotalItem()){
                index = i;
            }
        }
        return index;
    }
}