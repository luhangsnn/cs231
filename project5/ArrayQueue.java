/*
Luhang Sun
CS231 Project 5
ArrayQueue.java
*/

public class ArrayQueue <T> {
    private Object list[]; //use a generic array to hold objects(agents)
    private int N; // size of the list or the index of the next free space in the list

    public ArrayQueue (int size){
        this.list = new Object [size];
        this.N =0;
    }

    //empty the list    
    public void clear(){
        this.N=0;
    }

    public int size(){
        return this.N;
    }

    //adding an item at the end of the list
    public void offer(T item){
        //if the array is full, double its size
        if (this.N == list.length){
            Object [] newList = new Object [list.length * 2];

            for (int i=0; i<this.N; i++){
                newList[i] = this.list[i];
            }
            this.list = newList;
        }
        this.list[this.N] = item;
        this.N++;
    }

    //return and remove the first item
    @SuppressWarnings("unchecked")
    public T poll (){
        if (this.N == 0){
            System.out.println("error: queue empty!");
            return null;
        }
        T thing = (T) this.list[0];

        //moving the elements after index up by 1
        for (int i=0; i<this.N-1; i++){
            this.list[i] = this.list[i+1];
        }
        this.N --;
        return thing;
    }

    //print out the list
    public String toString(){
        String s = "";
        for (int i=0; i<N; i++){
            s += this.list[i] + "|";
        }
        return s;
    }

    public static void main(String [] args){
        ArrayQueue <Integer> aq = new ArrayQueue<Integer> (5);
        aq.offer(1);
        aq.offer(2);
        aq.offer(3);
        aq.offer(4);

        aq.poll();
        System.out.println(aq);
    }
}
