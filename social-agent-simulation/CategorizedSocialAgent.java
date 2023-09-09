/*
Luhang Sun
CS231 Project 4
CategorizedSocialAgent.java
*/

import java.awt.*;
import java.util.*;

public class CategorizedSocialAgent extends SocialAgent{

    public CategorizedSocialAgent(double x0, double y0, int cat){
        super (x0, y0);
        this.category = cat;
    }

    public int getCategory(){
        return this.category;
    }

    public String toString (){
        return Integer.toString(this.category);
    }

    public void draw(Graphics g){
        if (category == 0) {
            if (this.moved) {
                g.setColor(Color.cyan);
            }
            else{
                g.setColor(Color.blue);
            }
        }
        else if (category == 1) {
            if (this.moved) {
                g.setColor(Color.pink);
            }
            else{
                g.setColor(Color.red);
            }
        }
        else if (category == 2) {
            if (this.moved) {
                g.setColor(Color.LIGHT_GRAY);
            }
            else{
                g.setColor(Color.gray);
            }
        }
        g.fillOval ((int)this.x, (int)this.y, 5, 5);
    }

    public void updateState(Landscape scape, double r){
        ArrayList <Agent> neighbors = scape.getNeighbors(this.x, this.y, r);
        int n = 0; //No. of agents from the same category
        int d = 0; //No. of agents from differnet category

        //get the number of agents from the same category
        for (Agent a: neighbors){
            if (a.category == this.getCategory()){
                n++;
            }
            else{
                d++;
            }
        }

        n--;//exlcuding the referencing agent itself

        //implement the rules
        if (n >= 2 && n > d){
            if (Math.random() <= 0.01){
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
}