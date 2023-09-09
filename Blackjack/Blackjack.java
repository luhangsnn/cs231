/*
Blackjack.java
Luhang Sun
CS231 Project 1
*/

//current using 6 decks of card to play the game
//the original codes where only one deck of cards is used is commented out in //

import java.util.Scanner;

public class Blackjack{

    Hand player;
    Hand dealer;
    //Deck deck;
    BigDeck deck;

    boolean print = true; //a variable that helps to decide if the dealer needs to draw card (because the player might be bust, therefore the dealer needn't to draw)
    int deckSize; //size of the orginal deck


    //set up and reset the game
    public Blackjack(){
        player = new Hand();
        dealer = new Hand();
        //deck = new Deck();
        deck = new BigDeck();
        deckSize = deck.size();
        this.reset(true);
    }

    //reset the game
    public void reset( boolean newDeck){
        deck.shuffle();
    }

    //deal out two cards for both players
    public void deal(){
        for (int i=0; i<2; i++){
            player.add(deck.deal());
            dealer.add(deck.deal());
        }
    }

    //return the total values in player and dealer hands
    public String toString(){
        String state = "";
        int p_value = player.getTotalValue();
        if (print){
            int d_value = dealer.getTotalValue();
            state = "player cards worth: " + p_value + "  dealer cards worth: " + d_value;
        }
        else{
            state = "player cards worth: " + p_value;
        }
        return state;
    }

    public boolean playerTurn(){
        //have the player draw card  until player's hand <= 16
        while (player.getTotalValue() <= 16){
            player.add(deck.deal());
        }

        if (player.getTotalValue()>21){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean dealerTurn(){
        //have the dealer draw card until dealer's hand <= 17
        while (dealer.getTotalValue() <= 17){
            dealer.add(deck.deal());
        }

        if (dealer.getTotalValue()>21){
            return false;
        }
        else{
            return true;
        }
    }

    //helps use the terminal the control player actions from input
    public void playerTurnInteractive(){
        Scanner actionInput = new Scanner(System.in);

        //classify player's choices and implements dealing actions
        while (true) {
            //getting the action input from player
            System.out.println("Your cards now worth: " + player.getTotalValue());

            //if the player's card is already worth more than 21
            if (player.getTotalValue()>=21){
                this.print = false;
                break;
            }
            else{
                System.out.println("Draw another card? (yes/no)");
                String action = actionInput.nextLine(); //read from command line

                if (action.equals("yes")){
                    Card newcard = deck.deal();
                    System.out.println("the card you drew worth: " + newcard.getValue());
                    player.add(newcard);

                }
                else if (action.equals("no")){
                    actionInput.close();
                    break;
                }
                else {
                    System.out.println("invalid input, stopped dealing card");
                    break;
                }
            }
        }
    }

    //set the rule of the game 
    public int game (boolean verbose){
        
        //reset the card deck if too few cards left in the deck
        //first check if there are more than one stack of cards used in the game
        if (deck.single){
            if (this.deck.size() <= 26){
                this.deck.build();
                this.reset(true);
            }
        }
        else{
            //when using 6 decks of cards
            if (this.deck.size() <= 52){
                this.deck.build();
                this.reset(true);
            }
        }

        //start comparing the cards of the player and the dealer
        if (player.getTotalValue()>21){
            //print out the result (code syntax stays the same for the conditional statement below)
            if (verbose){
                System.out.println("dealer wins");
            }
            return -1;
        }
        else {
            if (dealer.getTotalValue()>21 || player.getTotalValue() > dealer.getTotalValue()){
                if (verbose){
                    System.out.println("player wins");}
                return 1;
            }
            else if (player.getTotalValue()==21 && (dealer.getTotalValue()!=21)){
                if (verbose){
                    System.out.println("player wins");}
                return 1;
            }
            else if (player.getTotalValue()==dealer.getTotalValue()){
                if (verbose){
                    System.out.println("push!");}
                return 0;
            }
            else{
                if (verbose){
                    System.out.println("dealer wins");}
                return -1;
            }
        }
    }

    //print the state of the cards in player and dealer's hand
    public void printHand(Blackjack jack){
        System.out.println("player cards: " + jack.player.toString());
        if (print){
            System.out.println("dealer cards: " + jack.dealer.toString());
        }
        System.out.println(jack.toString());        
    }

    public static void main(String[] args){
        //test the game
        Blackjack blackjack = new Blackjack();
        
        // plays the game for three times, each time deal and let the player and dealer draw card
        for (int i=0; i<3; i++){
            blackjack.deal();
            blackjack.playerTurn();
            blackjack.dealerTurn();
            blackjack.printHand(blackjack); //print the states of cards the dealer and player has
            blackjack.game(true);
            System.out.println("");

            //reset the hand of the player and the dealer
            blackjack.player.reset();
            blackjack.dealer.reset();
        }
            
        }
}