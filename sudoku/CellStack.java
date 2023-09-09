/*
Luhang Sun
CS231 Project 3
CellStack.java
*/

public class CellStack{
    private Cell grid[];
    private int max; //maximum number of items that fits into the stack
    private int top; //the next free loction on the stack;

    public CellStack(){
        this.max = 10;
        this.grid = new Cell [10];
        this.top = 0;
    }

    public CellStack(int x){
        this.max = x;
        this.grid = new Cell [x];
        this.top = 0;
    }

    public void push(Cell c){
        if (this.top >= this.grid.length){
            Cell tem [] = new Cell [this.grid.length*2];
            for (int i=0; i<this.top; i++){
                tem [i] = this.grid [i];
            }
            this.grid = tem;
            this.max = this.grid.length;
        }
        this.grid[this.top++] = c;
    }

    public Cell pop(){
        if (this.top > 0){
            Cell item = this.grid[this.top-1];
            this.grid[this.top-1] = null;
            this.top--;
            return item;
        }
        else{
            return null;
        }
    }

    public int size(){
        return this.top;
    }

    public boolean empty(){
        if (this.top == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
