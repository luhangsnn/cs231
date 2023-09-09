/*
Luhang Sun
CS231 Project 1
*/
import java.util.ArrayList;
import java.util.Random;

public class Deck{
    //building the field deckList
    public ArrayList <Card> deckList;

    boolean single;
    
    public Deck(){
        this.deckList = new ArrayList <Card>();
        this.single = true;
        this.build();
    }

    //builds the deck of cards in accordance to the instruction
    public void build(){
        for (int i=0; i<4; i++){
            this.deckList.add(new Card (11));
            for (int j=2; j<=9; j++){
                this.deckList.add(new Card (j));
            }
        }
        for (int k=0; k<16; k++){
            this.deckList.add(new Card (10));
        }
    }

    //returns the umber of cards in the deck
    public int size(){
        return this.deckList.size();
    }

    // returns the top card and removes it from the deck
    public Card deal(){
        Card remove_card = this.deckList.remove(0);
        return remove_card;
    }

    // return and remove the card at position i
    public Card pick (int i){
        if (i>=0 && i<this.deckList.size()){
            Card pick_card = this.deckList.remove(i);
            return pick_card;
        }
        else{
            System.out.println("index outa range. Picking the first card.");
            return this.deckList.remove(0);
        } 
    }

    //shuffle the deck
    public void shuffle(){
        //create a newdeck arraylist to hold the shuffled cards
        ArrayList <Card> newdeck = new ArrayList <Card>();

        Random number = new Random(System.currentTimeMillis()); //create a random object
        
        for (int i=0; i<this.deckList.size(); i++){
            int N = number.nextInt(deckList.size());
            newdeck.add(deckList.remove(N));
        }
        this.deckList = newdeck;   
    }

    //return the contents of the deck
    public  String toString(){
        String content = "";
        for  (int i=0; i<this.deckList.size(); i++){
            content = content + "Card: " + this.deckList.get(i).getValue() + ", ";
        }
        return content;
    } 
}