/*
Luhang Sun
CS231 Project 9
Wumpus.java
*/
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class Wumpus extends Agent{
    private Vertex v; //home locaiton of the Wumpus
    private boolean alive;


    public Wumpus (int x, int y, Vertex v) {
        super(x, y);
        this.v = v;
        this.alive = true;
    }

    public Vertex getWumpusPosition(){
        return this.v;
    }

    public boolean life (){
        return this.alive;
    }

    public void setLife(boolean s){
        this.alive = s;
    }

    public void draw (Graphics g, int scale){
        if (!v.isVisible()) return;

        int xpos = (int)v.getCol()*scale;
        int ypos = (int)v.getRow()*scale;
        int border = 2;

        if (this.alive){
            g.setColor(new Color(240,128,128));
        }
        else{
            g.setColor(new Color (112,128,144));
        }
        g.fillRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        
        BufferedImage gargy  = null;
        try{
            gargy = ImageIO.read (new File("gargy.png"));
            g.drawImage(gargy, xpos+scale/10, ypos+scale/10, null);
        }
        catch(Exception e){
            System.out.println("Unable to load image.");
            g.setColor(new Color (112,128,144));
            g.fillOval(xpos, ypos, scale - 2*border, scale - 2 * border);
        }
    }
}