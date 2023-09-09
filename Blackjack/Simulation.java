/*
Simulation.java
Luhang Sun
CS231 Project 1
*/

public class Simulation{

    //create the field of a blackjack class
    static Blackjack sim;
    
    public static void main(String[] args){
        sim = new Blackjack();
        int p = 0; //times that player wins
        int d = 0; //times that dealer wins
        int push = 0; //times of "push"

        //play the blackjack game 1000 times
        for (int i=0; i<1000; i++){
            sim.deal();
            sim.playerTurn();
            sim.dealerTurn();

            //keep track of the times of different results of the games
            if (sim.game(false) == 1){
                p++;
            }
            else if (sim.game(false) == 0){
                push++;
            }
            else if (sim.game(false) == -1){
                d++;
            }

            //reset the hand of the player and the dealer
            sim.player.reset();
            sim.dealer.reset();
        }

        //print out the result as raw numbers and percentage
        System.out.println("player won:" + p + "  percentage:" + (100*p/1000.0) + "%");
        System.out.println("dealer won:" + d +"  percentage:" + (100*d/1000.0) + "%");
        System.out.println("pushed:" + push + "  percentage:" + (100*push/1000.0) + "%");
    }
}