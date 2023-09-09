/*
Luhang Sun
CS231 Project 3
Board.java
*/

import java.io.*;
import java.awt.Graphics;

public class Board{
    private Cell grid [][];
    public static int Size = 9;

    //constructor that creates a 2D array of Cells
    public Board(){
        grid = new Cell [Board.Size][Board.Size];
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<Board.Size; j++){
                grid [i][j] = new Cell();
            }
        }
    }

    //read file from command line
    public boolean read (String filename){
        try {
            FileReader file = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            int num = 0; //index that keeps track of the lines
            
            while (line != null){
                String [] arr = line.split("[ ]+");

                System.out.println(arr.length);
    
                //setting the values in the doc to Board
                for (int i=0; i<arr.length; i++){
                    this.set(num, i, Integer.parseInt(arr[i]));
                }
                line = buffer.readLine();
                num ++;
            }
            buffer.close();
            return true;
        }
        catch(FileNotFoundException ex){
            System.out.println("Board.read()::unable to open file " + filename);
        }
        catch(IOException ex){
            System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
    }

    public int getRows(){
        return grid.length;
    }

    public int getCols(){
        return grid[0].length;
    }

    public Cell get(int r, int c){
        return grid[r][c];
    }

    public boolean isLocked(int r, int c){
        return this.get(r,c).isLocked();
    }

    public int value (int r, int c){
        return grid[r][c].getValue();
    }

    public void set (int r, int c, int value){
        grid[r][c].setValue(value);
    }

    public void set(int r, int c, int value, boolean locked){
        grid[r][c].setValue(value);
        grid[r][c].setLocked(locked);
    }

    //tests if the given value is a valid value at its position in the board
    public boolean validValue (int row, int col, int value){
        if (value >=1 && value <= 9){
            //check if the value is unique in its row and column
            for (int k=0; k<Board.Size; k++){
                if (k!=row && (value == this.value (k, col))){
                    return false;
                }
            for (int m=0; m<Board.Size; m++){
                if (m != col && (value == this.value(row, m))){
                    return false;
                }
            }
        }
            //check if the number is unique in its block
            int r_group = (row/3)*3; //index of the first 3*3 block Cell
            int c_group = (col/3)*3;
            //System.out.println(r_group + " "+ c_group);
            for (int i=r_group; i<r_group+3; i++){
                for (int j=c_group; j<c_group+3; j++){
                    if (!(i == row && j == col)){
                        if (value == this.value(i, j)){
                            return false;
                        }  
                    }
                }
            }
            return true;
        }
        //if the value is out of bound of [1,9], return false
        else{ 
            return false;
        }
    }

    //returns true if the board is solved, return false if any value is 0 or not valid given its loction
    public boolean validSolution(){
        for (int i=0; i<this.getRows(); i++){
            for (int j=0; j<this.getCols(); j++){
                if (this.validValue(i, j, this.value(i, j))==false){
                    return false;
                }
            }
        }
        return true;
    }

    //generates a string representation of the board and breaks it into 3*3 blocks
    public String toString (){
        String board ="";
        for (int i=0; i<Board.Size; i++){
            for (int j=0; j<Board.Size; j++){
                board += this.value(i, j) + " ";
                if (j == 2 || j == 5){
                    board += " ";
                }
            }
            board += "\n";
            if (i==2 || i==5){
                board += "\n";
            }
        }
        return board;
    }

    public void draw(Graphics g, int scale){
        int space = scale/2; //put the data in the middle of the grid
        g.drawRect(0, 0, Board.Size*scale, Board.Size*scale); //draw the board outline
        
        //outline the 3*3 blocks
        int num[] = {3, 6};
        for (int k : num){
            g.drawLine(0, k*scale, Board.Size*scale, k*scale);
            g.drawLine(k*scale, 0, k*scale, Board.Size*scale);
        }
            
        //print the values onto the board
        for (int i=0; i<Board.Size; i++){
            for (int j=0; j<Board.Size; j++){
                this.get(i, j).draw(g, i*scale+space, j*scale+space, scale);
            }
        }
    }

    public static void main(String [] args){
        if (args.length < 1){
            System.out.println("no command found");
            return;
        }
        Board game = new Board();

        game.read(args[0]);
        System.out.println(game.validSolution());
    }
}