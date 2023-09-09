/*
Luhang Sun
CS 231 Project 9
Vertex.java
*/
import java.util.*;
import java.awt.*;


public class Vertex extends Agent implements Comparable <Vertex> {
    private Vertex room [];
    private int cost;
    private boolean marked, visible;

    public Vertex(int x, int y) {
        super (x, y);
        this.cost = 0;
        this.marked = false; 
        this.visible = false;
        this.room = new Vertex [4];
    }

    public static Direction opposite (Direction d) {
        if (d == Direction.SOUTH){
            return Direction.NORTH;
        }
        else if (d == Direction.NORTH){
            return Direction.SOUTH;
        }
        else if (d == Direction.EAST) {
            return Direction.WEST;
        }
        else if (d == Direction.WEST) {
            return Direction.EAST;
        }
        return null;
    }

    // modify the object's adjacency list so that it connects with other Vertex
    public void connect (Vertex other, Direction dir) {
        this.room [dir.ordinal()] = other;
    }

    // returns the vertex in the specified direction 
    public Vertex getNeighbor (Direction dir) {
        return this.room [dir.ordinal()];
    }

    // returns an arraylist of all of the object's neighbors
    public ArrayList <Vertex> neighbors(){
        ArrayList<Vertex> neighbors = new ArrayList <Vertex>();
        for (int i=0; i<this.room.length; i++) {
            if (this.room[i] != null) {
                neighbors.add(this.room[i]);
            }
        }
        return neighbors;
    }

    public void setCost(int c){ this.cost = c; }

    public int getCost(){ return this.cost; }

    public boolean isVisible() { return this.visible; }

    public void setVisible(boolean v){ this.visible = v; }
    
    // mark a vertex as visited
    public void mark() { this.marked = true; }

    public boolean isMarked() { return this.marked;}

    public String toString(){
        String s = "Num of neighbors: " + this.neighbors().size();
        s += ", Cost: " + this.cost + ", Marked: " + this.marked + "|";
        return s;
    }

    public int compareTo(Vertex v) {
        return this.getCost() - v.getCost();
    }

    public void draw(Graphics g, int scale) {
        if (!this.visible) return;

        int xpos = (int)this.getCol()*scale;
        int ypos = (int)this.getRow()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
        
        // draw rectangle for the walls of the room
        if (this.cost <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);
        
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.getNeighbor(Direction.NORTH) != null)
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.getNeighbor(Direction.SOUTH) != null)
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
                       eighth, eighth + sixteenth);
        if (this.getNeighbor(Direction.WEST) != null)
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.getNeighbor(Direction.EAST)!= null)
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
                       eighth + sixteenth, eighth);
        // g.drawString(Integer.toString(this.cost), xpos+scale/2, ypos+scale/2);
    }
}