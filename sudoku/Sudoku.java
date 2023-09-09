/*
Luhang Sun
CS231 Project 3
Sudoku.java
*/

import java.util.Random;
import java.util.ArrayList;

public class Sudoku{
    private Board board;
    LandscapeDisplay display;

    //constructor: creates a board with some random inititial values
    public Sudoku(int scale){
        board = new Board(); //a board of 0's by default
        display = new LandscapeDisplay(board, scale);
    }

    //constructor that takes the number of populated starting values N
    public Sudoku(int N, int scale){
        Random ran = new Random();
        board = new Board();
        for (int i=0; i<N; i++){
            int r = ran.nextInt(board.getRows());
            int c = ran.nextInt(board.getCols());
            int value = ran.nextInt(10);
            if (board.value(r, c) == 0 && (board.validValue(r, c, value))){
                board.set(r, c, value, true);
            }
            else{
                N++;
            }
        }
        display = new LandscapeDisplay(board, scale);
    }

    public boolean solve(int delay){
        CellStack stack = new CellStack(100);
        int curRow = 0; int curCol = 0; 
        int curValue = 1; int time = 0;

        while (stack.size() < Board.Size * Board.Size){
            time++;
            if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }

            //handling cells with predefined values
            if (board.isLocked(curRow, curCol)){
                stack.push(board.get(curRow, curCol));
                if (curCol == Board.Size-1){
                    curCol = 0;
                    curRow++;
                }
                else{
                    curCol ++;
                }
                continue;
            }
            
            for (int i=curValue; i<10; i ++){
                if (board.validValue(curRow, curCol, i)){
                    curValue = i;
                    break;
                }
            }

            if (board.validValue(curRow, curCol, curValue)){
                stack.push(new Cell(curRow, curCol, curValue));
                board.set(curRow, curCol, curValue);
                if (curCol < Board.Size-1){
                    curCol ++;
                }
                else{
                    curCol = 0;
                    curRow++;
                }
                curValue = 1;
            }
            else{
                if (stack.size() > 0){
                    Cell top = stack.pop();
                    while (top.isLocked()){
                        if(stack.size()>0){
                            top = stack.pop();
                        }
                        else{
                            System.out.println(time);
                            return false;
                        }
                    }
                    curRow = top.getRow();
                    curCol = top.getCol();
                    curValue = top.getValue()+1;
                    board.set(curRow, curCol, 0);
                }
                else{
                    System.out.println(time);
                    return false;
                }
            }
        }
        System.out.println(time);
        return true;
    }

    public String toString(){
        return board.toString();
    }

    public static void main(String [] args){

        int n=0; //keeps track of the number of board solved
        ArrayList <Sudoku> arr = new ArrayList <Sudoku> (5);
        
        //create 5 boards and push them into an arraylist
        for (int i=0; i<5; i++){
            arr.add(new Sudoku(Integer.parseInt(args[0]),40));
        }

        //trying to solve all of the 5 boards
        for (int k=0; k<5; k++){
            if (arr.get(k).solve(1)){
                n++;
            }
        }
        System.out.println("Solved boards: " + n + "/5");

        // Sudoku s = new Sudoku(Integer.parseInt(args[0]),40);
        // s.board.read(args[0]);
        // System.out.println(s.solve(1));
        // System.out.println(s.toString());
    }
}