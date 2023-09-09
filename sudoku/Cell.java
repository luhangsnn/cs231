/*
Luhang Sun
CS231 Project 3
Cell.java
*/

import java.awt.Graphics;
import java.awt.Color;

public class Cell{
    private int row;
    private int col;
    private int value;
    private boolean locked;

    //constructor
    public Cell(){
        row = 0;
        col = 0;
        value = 0;
        locked = false;
    }

    public Cell(int r, int c, int v){
        this.row = r;
        this.col = c;
        this.value = v;
    }

    public Cell(int r, int c, int v, boolean lock){
        this.row = r;
        this.col = c;
        this.value = v;
        this.locked = lock;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int newval){
        this.value = newval;
    }

    public boolean isLocked(){
        return this.locked;
    }

    public void setLocked(boolean lock){
        this.locked = lock;
    }

    public String toString(){
        String a = "Row: " + Integer.toString(this.row) + " ";
        String b = "Column: " + Integer.toString(this.col) + " ";
        String c = "Value: " + Integer.toString(this.value) + " ";
        String d = "locked: " + Boolean.toString(this.locked);
        return a+b+c+d;
    }

    public Cell clone(){
        return new Cell(this.row, this.col, this.value, this.locked);
    }

    public void draw(Graphics g, int x0, int y0, int scale){
        if (this.value == 1){
            g.setColor(Color.black);
        }
        else if (this.value == 2){
            g.setColor(Color.orange);
        }
        else if (this.value == 3){
            g.setColor(Color.green);
        }
        else if (this.value == 4){
            g.setColor(Color.pink);
        }
        else if (this.value == 5){
            g.setColor(Color.red);
        }
        else if (this.value == 6){
            g.setColor(Color.gray);
        }
        else if (this.value == 7){
            g.setColor(Color.magenta);
        }
        else if (this.value == 8){
            g.setColor(Color.cyan);
        }
        else if (this.value == 9){
            g.setColor(Color.blue);
        }

        g.drawString(Integer.toString(this.value), x0, y0);
        g.setColor(Color.black);
        // char c[] = new char[1];
        // c[0] = (char)('0' + this.value);
        // g.drawChars(c,scale, 1, x0, y0);
    }
}