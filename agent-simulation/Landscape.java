
/*
Luhang Sun
CS 231 Project 2
Landscape.java
*/

import java.awt.Graphics;
import java.util.ArrayList;

public class Landscape {
    private Cell land[][];
    private int r;
    private int c;

    // constructor: set the number of rows and columns
    public Landscape(int rows, int cols) {
        this.r = rows;
        this.c = cols;
        this.land = new Cell[r][c];

        // allocate a cell for each location in the grid
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) {
                land[i][j] = new Cell();
            }
        }
    }

    // reset the cell in each location
    public void reset() {
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) {
                land[i][j].reset();
            }
        }
    }

    // return the number of row in the array land
    public int getRows() {
        return land.length;
    }

    // return the number of cols in the array land
    public int getCols() {
        return land[0].length;
    }

    // return the reference to the Cell in each position
    public Cell getCell(int row, int col) {
        return land[row][col];
    }

    // return the state of each Cell at each location
    public String toString() {
        String representation = "";
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) {
                representation = representation + land[i][j].toString() + "-";
            }
            representation = representation.substring(0, representation.length() - 1) + "\n";
        }
        return representation;
    }

    // return a list of the neighbors of the Cell at loction (row, col)
    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> neighbor = new ArrayList<Cell>();
        int up = row - 1;
        int down = row + 1;
        int left = col - 1;
        int right = col + 1;

        // first check the boundary
        if (up >= 0) {
            neighbor.add(this.getCell(up, col)); // upper neighbor
            if (left >= 0) {
                neighbor.add(this.getCell(up, left)); // upper-left neighbor
            }

            if (right < land[row].length) {
                neighbor.add(this.getCell(up, right)); // upper-right neighbor
            }
        }

        if (down < land.length) {
            neighbor.add(this.getCell(down, col)); // lower neighbor
            if (left >= 0) {
                neighbor.add(this.getCell(down, left)); // lower-left neighbor
            }

            if (right < land[row].length) {
                neighbor.add(this.getCell(down, right)); // lower-right neighbor
            }
        }

        if (left >= 0) {
            neighbor.add(this.getCell(row, left)); // left neighbor
        }
        if (right < land[row].length) {
            neighbor.add(this.getCell(row, right)); // right neighbor
        }
        return neighbor;
    }

    // draw all the Cells
    public void draw(Graphics g, int gridScale) {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                this.land[i][j].draw(g, i * gridScale, j * gridScale, gridScale);
            }
        }
    }

    // advance all the Cells towards next generation
    public void advance() {
        Cell newland[][] = new Cell[this.r][this.c]; // creating a temporary Cell grid

        // copy Cell states of the original grid to the new grid
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) {
                newland[i][j] = new Cell();
                newland[i][j].setAlive(land[i][j].getAlive());
            }
        }

        // update each cell in the new grid with the neighbor rule
        for (int k = 0; k < this.r; k++) {
            for (int m = 0; m < this.c; m++) {
                newland[k][m].updateState(getNeighbors(k, m));
            }
        }
        this.land = newland;
    }

    public static void main(String[] args) {
        Landscape landscape = new Landscape(5, 6);

        // activate the Cells in the first row
        for (int i = 0; i < landscape.getCols(); i++) {
            landscape.getCell(0, i).setAlive(true);
        }
        System.out.println(landscape.getCell(0, 5).toString());
        System.out.println(landscape.getRows() + " " + landscape.getCols());
        System.out.println(landscape.toString());

        // print out the states of the neighbors of (1,3)
        ArrayList<Cell> neiCells = landscape.getNeighbors(1, 3);
        for (int j = 0; j < neiCells.size(); j++) {
            System.out.print(neiCells.get(j).toString() + " ");
        }

        // reset the first row
        System.out.println("");
        landscape.reset();
        System.out.println(landscape.toString());
    }
}