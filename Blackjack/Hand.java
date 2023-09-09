/*
Hand.java
Luhang Sun
CS231 Project 1
*/

import java.util.ArrayList;

public class Hand{

    public ArrayList <Card> handList;

    //initializes the arraylist
    public Hand(){
        this.handList = new ArrayList<Card>();
        this.handList.clear();
    }

    //methods that reset the hand to empty
    public void reset(){
        handList.clear();
    }
    
    //add card to hand
    public void add (Card card){
        handList.add(card);
    }

    //return the number of cards in hand
    public int size(){
        return handList.size();
    }

    //return the card with index i
    public Card getCard (int i){
        if (i >= 0 && i < handList.size()){
            return handList.get(i);
        }
        else{
            System.out.println("index out of range, getting the first card");
            return handList.get(0);
        }
    }

    public int getTotalValue(){
        int sum = 0;
        for (int v = 0; v < handList.size(); v++){
            sum = sum + handList.get(v).getValue();
        }
        return sum;
    }

    // returns a String that has the contents of handList
    public String toString(){
        String content = "";
        for (int i =0; i < handList.size(); i++){
            content = content + handList.get(i).getValue() + " ";
        }
        return content;
    }
}