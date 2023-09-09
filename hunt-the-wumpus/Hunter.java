/*
Luhang Sun
CS231 Project 9
Hunter.java 
*/
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

public class Hunter extends Agent{
    private Vertex vertex;
    private boolean armed, alive;

    public Hunter(int x, int y, Vertex v){
        super(x, y);
        this.vertex = v;
        this.vertex.setVisible(true);
        this.armed = false;
        this.alive = true;
    }

    public void updatePosition(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    public void setHunterVertex(Vertex newV) {
        this.vertex = newV;
    }

    public void setArmed(boolean a){
        this.armed = a;
    }

    public boolean isArmed (){
        return this.armed;
    }

    public Vertex getHunterPosition(){
        return this.vertex;
    }

    public boolean life () { return this.alive; }

    public void setLife(boolean s) { this.alive = s; }

    public void draw(Graphics g, int scale){
        int xpos = (int)vertex.getCol()*scale;
        int ypos = (int)vertex.getRow()*scale;
        int border = 2;
        int fifth = scale / 5;

        if(this.alive){
            BufferedImage smurf = null;
            try{
                smurf = ImageIO.read (new File("smurf.jpg"));
                g.drawImage(smurf, xpos+scale/10, ypos, null);
            }
            catch (IOException e){
                System.out.println("Unable to load image");
                g.setColor(Color.orange);
                g.fillOval(xpos, ypos, scale - 2*border, scale - 2 * border);
            }

            //when the hunter is armed
            if (this.armed){
                g.setColor(new Color (30,144,255));
                g.fill3DRect(xpos + fifth*2, ypos + fifth*2, scale/4, scale/4, true);
            }  
        }
        else{ //when the hunter is dead
            BufferedImage deadsmurf = null;
            try{
                deadsmurf = ImageIO.read (new File("deadsmurf2.png"));
            }
            catch(Exception e){
            }
            g.drawImage(deadsmurf, xpos+scale/10, ypos, null);
        }        
    }
}