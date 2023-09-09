/*
Interactive.java
Luhang Sun
CS231 Project 1
//an interactive program where the player can choose whether he/she wants to deal another card when it is player's turn
*/

public class Interactive{
    static Blackjack interJack;

    public static void main (String[] args){
        interJack = new Blackjack();
        
        interJack.deal();
        interJack.playerTurnInteractive();
        if (interJack.print){
            interJack.dealerTurn();
        }

        interJack.printHand(interJack); //print the states of cards the dealer and player has
        interJack.game(true);

    }
    
}
