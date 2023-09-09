/*
Luhang Sun
CS231 Project 1
*/

public class Card {
    public int value;
    
    // constructor with the value of the card
    public Card (int v){
        if (v >= 2 && v <= 11){
            this.value = v;
        }
        else{
            System.out.println("value out of range");
        }

    }

    //return the numeric value of the card
    public int getValue(){
        return this.value;
    }

    //main method
    public static void main(String[] args){
        Card first = new Card(10);
        int firstValue = first.getValue();
        System.out.println("the value of the first card is "+firstValue);
    }
}