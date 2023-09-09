/*
Luhang Sun
CS 231 project 8
PQHeap.java
*/
import java.util.*;

@SuppressWarnings("unchecked")
public class PQHeap <T> {
    T[] heap;
    int size; //the number of elements 
    Comparator<T> comp;
    
    public PQHeap (Comparator<T> comparator) {
        this.heap = (T[]) new Object[10];
        this.size = 0;
        this.comp = comparator;
    }

    public int size (){
        return this.size;
    }

    //add the value to the heap and reshape the heap if needed
    public void add (T obj) {
        if (this.size == this.heap.length) { //expand the array if it's full
            T[] newTable = (T[]) new Object[this.heap.length * 2];
            for (int i=0; i<this.heap.length; i++){
                newTable [i] = this.heap[i];
            }
            this.heap = newTable;
        }
        this.heap[size] = obj;
        this.reheapUp (this.size, obj);
        this.size++;
    }

    // reshape the heap after adding a new element
    private void reheapUp(int i, T obj) {
        if (i==0) return;
        int parentIdx = (i - 1)/2;

        //compare the new element w/ its parent
        int c = this.comp.compare (obj, this.heap[parentIdx]);
        if (c <= 0) { // return if the new element is smaller than its parent
            return;
        }
        else { //reheap up if the element is larger than its parent
            this.swap (i, parentIdx);
            this.reheapUp (parentIdx, obj);
        }
    }

    // remove and return the highest priority element from the heap
    public T remove () {
        if (this.size == 0) { //empty heap
            System.out.println("Error: empty heap");
            return null;
        }
        T head = this.heap[0];

        if (this.size == 1){ //one element case
            this.heap[0] = null;
            size--;
            return head;
        }
        this.heap[0] = this.heap [size-1];
        this.heap[size-1] = null;
        this.reheapDown(0, this.heap[0]);
        size--;
        return head;
    }

    // reshape the heap after removing from the new root
    private void reheapDown(int i, T obj){
        int leftChild = 2 * i +1;
        int rightChild = 2 * i +2;

        if (leftChild > this.heap.length-1 || this.heap[leftChild] == null){
            return;
        }
        if (rightChild > this.heap.length-1 || this.heap[rightChild] == null) {
            return; 
        }
        int cLeft = this.comp.compare (obj, this.heap[leftChild]);
        int cRight = this.comp.compare (obj, this.heap[rightChild]);
        
        if (cLeft >= 0 && cRight >= 0){
            return;
        }
        if (cLeft < 0 || cRight < 0 ) {
            int cLR = this.comp.compare (this.heap[leftChild], this.heap[rightChild]); //compare the left and right child
            if (cLR > 0){ // swap with left child
                this.swap (i, leftChild);
                this.reheapDown (leftChild, obj);  
            }
            else { // swap with right child
                this.swap (i, rightChild);
                this.reheapDown(rightChild, obj);
            }
        }
    }

    public void swap (int i, int j) {
        T copy = heap[i];
        heap[i] = heap[j];
        heap[j] = copy;
    } 

    public void print(){
        for (int i=0; i<this.heap.length; i++){
            if (this.heap[i] == null) return;
            System.out.println(i + ": " + this.heap[i]);
        }
    }
}

class VertexComparator implements Comparator<Vertex>{
    public int compare(Vertex v1, Vertex v2){
        return (v2.getCost() - v1.getCost());
    }
}

