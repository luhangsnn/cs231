/*
bigDeck.java
Luhang Sun
CS231 Project 1
*/

//a deck arraylist that holds 6 decks of cards

import java.util.ArrayList;

public class BigDeck extends Deck{
    
    public ArrayList <Card> bigList;

    public BigDeck (){
        this.bigList = new ArrayList<Card>();
        this.single = false;
        this.build();
    }

    public void build(){
        //call the build function in Deck() for 6 times
        for (int i=0; i<6; i++){
            super.build();
    }
}
}
