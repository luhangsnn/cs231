
/*
Luhang Sun
CS 231 Project 2
Cell.java
*/

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Cell {
    protected boolean life;

    // constructors
    public Cell() {
        this.life = false;
    }

    public Cell(boolean alive) {
        this.life = alive;
    }

    // return the state of the Cell
    public boolean getAlive() {
        return this.life;
    }

    // reset the cell to dead
    public void reset() {
        this.life = false;
    }

    // set the cell's alive state
    public void setAlive(boolean alive) {
        this.life = alive;
    }

    // return the alive state of the Cell as a string: "0" for alive and "" for dead
    public String toString() {
        if (this.life) {
            return "0";
        } else {
            return "1";
        }
    }

    public void draw(Graphics g, int x, int y, int scale) {
        if (this.life) {
            g.setColor(Color.orange);
            g.fillRect(x, y, scale, scale);
        } else {
            g.setColor(Color.black);
            g.fillRoundRect(x, y, scale, scale, 2, 2);
        }
    }

    // update the state of a Cell based on the states of its neighbors
    public void updateState(ArrayList<Cell> neighbors) {
        int A = 0; // number of alive neighbors
        int B = 0; // number of dead neighbors

        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getAlive()) {
                A++;
            }
        }

        if (this.getAlive()) {
            if (A == 3 || A == 2) {
                this.setAlive(true);
            } else {
                this.setAlive(false);
            }
        } else if (this.getAlive() == false) {
            if (A == 3) {
                this.setAlive(true);
            }
        }
    }

    // main method to test the above
    public static void main(String[] args) {
        Cell testcell = new Cell();
        System.out.println("the initial status of the cell is " + testcell.getAlive());
        testcell.setAlive(true);
        System.out.println("now the status of the cell is " + testcell.getAlive());
        testcell.reset();
        System.out.println(testcell.toString());
    }
}
