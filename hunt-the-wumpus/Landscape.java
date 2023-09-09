/*
Luhang Sun
CS231 Project 9
Landscape.java
*/

import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;

public class Landscape {
    protected int w, h; //width and height
    private LinkedList <Agent> foreground, background;

    public Landscape (int w, int h){
        this.w = w;
        this.h = h;
        this.foreground = new LinkedList <Agent> ();
        this.background = new LinkedList <Agent> ();
    }

    public int getHeight (){
        return this.h;
    }

    public int getWidth (){
        return this.w;
    }

    public void addForegroundAgent (Agent a){
        foreground.addFirst(a);
    }

    public void addBackgroundAgent (Agent a){
        background.addFirst(a);
    }

    public String toString (){
        String s = "no. of foreground agents: " + foreground.size() 
        + "\no. of background agent: " + background.size();
        return s;
    }

    public void draw( Graphics g, int scale ){
        for (Agent a: foreground){
            a.draw(g, scale);
        }
        for (Agent a: background){
            a.draw(g, scale);
        }
    }
}