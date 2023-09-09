/*
Luhang Sun
CS231 Project 4
Landscape.java
*/

import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;

public class Landscape {
    protected int w; //width
    protected int h; //height
    private LinkedList <Agent> list;

    public Landscape (int w, int h){
        this.w = w;
        this.h = h;
        this.list = new LinkedList <Agent> ();
    }

    public int getHeight (){
        return this.h;
    }

    public int getWidth (){
        return this.w;
    }

    public void addAgent (Agent a){
        list.addFirst(a);
    }

    public String toString (){
        String s = "no. of agents: " + list.size();
        return s;
    }

    public ArrayList <Agent> getNeighbors (double x0, double y0, double radius){
        ArrayList <Agent> neighbors = new ArrayList <Agent> ();

        for (Agent a: list){
            if (Math.sqrt((a.getX()-x0)*(a.getX()-x0) + (a.getY()-y0)*(a.getY()-y0)) < radius){
                neighbors.add(a);
            }
        }
        return neighbors;
    }

    public void draw( Graphics g ){
        for (Agent a: list){
            a.draw(g);
        }
    }

    //update each agent in the list
    public void updateAgents(double r){
        ArrayList <Agent> arr = list.toShuffledList();
        for (Agent a: arr){
            a.updateState(this, r);
        }
    }

    public static void main(String [] args){
        Landscape scape = new Landscape(100, 100);
        SocialAgent a = new SocialAgent (5,5);
        scape.addAgent(a);
        scape.addAgent(new SocialAgent(15,15));
        scape.addAgent(new SocialAgent(10,10));
        scape.addAgent(new SocialAgent(20,20));

        a.updateState(scape, 15);
        System.out.println("a is" + a);
        for (Agent n: scape.getNeighbors(a.getX(), a.getY(), 15)){
            System.out.println(n);
        }
    }
}