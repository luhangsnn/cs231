/**
 * Shuffle.java
 * Luhang Sun
 * 2/11/2019
*/

import java.util.ArrayList;
import java.util.Random;

public class Shuffle{
    public static void main (String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        Random number = new Random();
        
        // add 10 random intagers to myList
        for (int i=1; i <= 10; i++){
            //int rangeNum = number.nextInt(100);
            myList.add(i);
            //System.out.println(rangeNum);
        } 

        //loop through the myList and print out each number in turn
        /*
        for (int j=0; j < myList.size(); j++){
            //System.out.println(myList.get(j));
        }
        */
        
        int listSize = myList.size();
        for (int k=0; k < listSize; k++){
            int removeNum = myList.remove(number.nextInt(myList.size()));
            System.out.print("removed: " + removeNum);
            System.out.println("  remaining: " + myList);
        }

    }
}
