/*
Luhang Sun
CS231 Project 4
SocialAgent.java
*/

import java.awt.*;
import java.util.*;

public class SocialAgent extends Agent {
    protected boolean moved; //indicates whether the agent moved during the last update

    public SocialAgent (double x0, double y0){
        super (x0, y0);
    }

    //draw active and inactive agents with different colors
    public void draw (Graphics g){
        if (moved){
            g.setColor (Color.cyan);
        }
        else{
            g.setColor(Color.blue);
        }
        g.fillOval((int)this.x, (int)this.y, 5, 5);
    }
    
    //override the abstract updateState method
    public void updateState(Landscape scape, double r){
        ArrayList <Agent> neighbors = scape.getNeighbors(this.x, this.y, r);
        if (neighbors.size() > 4){
            double m = Math.random();
            if (m <= 0.01){
                this.moved = true;
                this.setX(this.x + (Math.random() - Math.random())*10);
                this.setY(this.y + (Math.random() - Math.random())*10);
            }
            else{
                this.moved = false;
            }
        }
        else{
            this.moved = true;
            this.setX(this.x + (Math.random() - Math.random())*10);
            this.setY(this.y + (Math.random() - Math.random())*10);
        }
    }

    public static void main(String [] args){
        SocialAgent a = new SocialAgent (0,0);
        System.out.println(a);
        a.setX (30.5);
        a.setY(6.4);
        System.out.println(a);
    }
}