/*
Luhang Sun
CS231 Project 5
Landscape.java
*/

import java.util.*;
import java.awt.*;

public class Landscape {
    private int w;
    private int h;
    private ArrayList <CheckoutAgent> checkoutList;
    private LinkedList <Customer> finishedList;
    private ArrayList <LinkedList <Customer>> finished_categorized ; //to be updated to hold only type of customers

    public Landscape (int w, int h, ArrayList <CheckoutAgent> checkouts){
        this.w = w;
        this.h = h;
        this.checkoutList = checkouts;
        this.finishedList = new LinkedList<Customer>();

        //createing an ArrayList of LinkedLists, each of which holds a different type of finished customers
        this.finished_categorized = new ArrayList <LinkedList<Customer>> (4);
        for (int i=0; i<4; i++){
            finished_categorized.add(new LinkedList<Customer>());
        }
    }

    public int getHeight(){
        return this.h;
    }

    public int getWidth(){
        return this.w;
    }

    public String toString(){
        String s = "Checkouts: " + checkoutList.size() + 
        "\nFinished: " + finishedList.size();
        return s;
    }

    public void addFinishedCustomers (Customer c){
        finishedList.addLast(c);
    }

    public int totalCust (){
        int totalCustomer = 0;
        for (CheckoutAgent ca: checkoutList){
            totalCustomer += ca.getNumInQueue();
        }
        return totalCustomer;
    }

    //call the draw method of every checoutAgents in the ArrayList
    public void draw (Graphics g){
        for (CheckoutAgent ca: checkoutList){
            ca.draw(g);
        }
    }

    public void updateCheckouts() {
        for (CheckoutAgent ca: checkoutList){
            ca.updateState(this);
        }
    }

    //compute the average and standard dev. of the time taken to leave the checkouts for all customers in the finished customer list
    public void printFinishedCustomerStatistics () {
        double sum = 0.0;
        double v = 0.0;

        for (Customer c: this.finishedList){
            sum += c.t;
        }
        double avg = sum / this.finishedList.size();

        //compute the standard dev.
        for (Customer c: this.finishedList){
            v += Math.pow(c.t - avg, 2);
        }
        double sd = Math.sqrt (v/this.finishedList.size());

        System.out.println("Avg time taken: " + avg + "\nStandard dev: " + sd + "\n");
    }


    public void addToCategorizedList (Customer c){
            finished_categorized.get(c.strategy).addLast(c);
    }

    //compute the stats of each type of customers
    public void printStrategyStats (){
        for (LinkedList <Customer> list: this.finished_categorized){
            double sum = 0.0; double v = 0.0;
            double average = 0.0; double sd = 0.0;

            for (Customer c: list){
                sum += c.t;
            }
            average = sum / list.size();

            for (Customer c: list){
                v += Math.pow(c.t - average, 2);
            }
            sd = Math.sqrt (v/list.size());

            System.out.println(list.getFirst().getClass() + "\nNum of Customers: " + list.size());
            System.out.println("Avg time taken: " + average + 
            "\nStandard dev: " + sd + "\n----------------------");
        }
    }

    //call the updateStateCategorized method of each checkout agents
    public void updateCheckouts2() {
        for (CheckoutAgent ca: checkoutList){
            ca.updateStateCategorized(this);
        }
    }
}