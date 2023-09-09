/*
Luhang Sun
CS231 Project 5
Customer.java
*/
import java.util.*;

public abstract class Customer{
    protected int n; //number of items
    protected int t; //number of time steps before leaving
    protected int strategy;
    
    public Customer(int num){
        this.n = num;
        this.t = 0;
    }

    public Customer(int num, int time_steps){
        this.n = num;
        this.t = time_steps;
    }

    public void incrementTime(){
        t++;
    }

    public int getTime(){
        return t;
    }

    //decrement the number of item by 1
    public void giveUpItem(){
        if (n == 0){
            System.out.println("error: no more item");
            return;
        }
        n--;
    }

    public int getNumItems(){
        return n;
    }

    public abstract int chooseLine (ArrayList <CheckoutAgent> checkouts);
}