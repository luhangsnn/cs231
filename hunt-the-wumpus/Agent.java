/*
Luhang Sun
CS231 Project 9
Agent.java 
*/

import java.awt.Graphics;

public class Agent {
    protected int x;
    protected int y;

    public Agent (int x0, int y0){
        this.x = x0;
        this.y = y0;
    }

    public int getCol (){
        return this.x;
    }

    public int getRow () {
        return this.y;
    }

    public void setX (int newX) {
        this.x = newX;
    }

    public void setY (int newY) {
        this.y = newY;
    }

    public String toString () {
        return "(" + this.x + "," + this.y + ")";
    }

    public void draw(Graphics g, int scale) {
        return;
    }


    // public static void main(String [] args){
    //     Agent a = new Agent (0,0);
    //     System.out.println(a);
    //     a.setX (30.5);
    //     a.setY(6.4);
    //     System.out.println(a);
    // }
}