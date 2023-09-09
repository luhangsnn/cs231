/*
Luhang Sun
CS231 Project 4
Agent.java 
*/

import java.awt.Graphics;

public abstract class Agent {
    protected double x;
    protected double y;
    protected int category = 2;

    public Agent (double x0, double y0){
        this.x = x0;
        this.y = y0;
    }

    public double getX (){
        return this.x;
    }

    public double getY () {
        return this.y;
    }

    public void setX (double newX) {
        this.x = newX;
    }

    public void setY (double newY) {
        this.y = newY;
    }

    public String toString () {
        return "(" + this.x + "," + this.y + ")";
    }

    //abstract methods to be overwritten
    public abstract void updateState (Landscape scape, double r);

    public abstract void draw (Graphics g);

    // public static void main(String [] args){
    //     Agent a = new Agent (0,0);
    //     System.out.println(a);
    //     a.setX (30.5);
    //     a.setY(6.4);
    //     System.out.println(a);
    // }
}