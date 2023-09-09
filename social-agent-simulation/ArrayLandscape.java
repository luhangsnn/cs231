/*
Luhang Sun
CS231 Project 4
ArrayLandscape.java
*/
//using the GenericArray instead of LinkedList to hold the agents

import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;

public class ArrayLandscape extends Landscape{
    private GenericArray <Agent> list; 

    public ArrayLandscape (int w, int h){
        super (w, h);
        this.list = new GenericArray <Agent> (50);
    }

    //overwrite parent's methods
    public void addAgent (Agent a){
        list.addFirst(a);
    }

    public ArrayList <Agent> getNeighbors (double x0, double y0, double radius){
        ArrayList <Agent> neighbors = new ArrayList <Agent> ();

        for (int i=0; i<list.size(); i++){
            Agent a = list.get(i);
            if (Math.sqrt((a.getX()-x0)*(a.getX()-x0) + (a.getY()-y0)*(a.getY()-y0)) < radius){
                neighbors.add(a);
            }
        }
        return neighbors;
    }

    //draw every agent in the landscape
    public void draw( Graphics g ){
        for (int i=0; i<list.size(); i++){
            list.get(i).draw(g);
        }
    }

    public String toString (){
        String s = "no. of agents: " + list.size();
        return s;
    }

    public static void main(String [] args){
        ArrayLandscape s = new ArrayLandscape(300, 300);
        SocialAgent a = new SocialAgent (5,5);
        s.addAgent(a);
        s.addAgent(new SocialAgent(15,15));
        s.addAgent(new SocialAgent(10,10));
        s.addAgent(new SocialAgent(20,20));

        for (Agent n: s.getNeighbors(a.getX(), a.getY(), 15)){
            System.out.println(n);
        }

        System.out.println(s);
    }
}