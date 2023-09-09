/*
Luhang Sun
CS231 Project 9
LinkedList.java
*/

import java.util.*;

public class LinkedList <T> implements Iterable <T> { 
    //T is the type of value stored in the Node(container)

    private Node head; //field of the head
    private int size; // the number of items in the list

    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    public void clear(){
        this.head = null;
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    public void addFirst(T item){
        Node n = new Node(item);
        n.setNext (this.head);
        this.head = n;
        this.size++;
    }

    public void addLast(T item){
        Node n = new Node(item);
        Node p = this.head;
        if (this.size == 0){
            this.head = n;
        }
        else {
            for (int i=0; i<this.size-1; i++) {
                p = p.getNext();
            }
            p.setNext(n);
        }
        this.size++;
    }

    public void add (int index, T item){
        if (index <0 || index > this.size){
            System.out.println("index out of bound");
            return;
        }

        if (index == 0){
            Node n = new Node (item);
            n.setNext(this.head);
            this.head = n;
        }

        else{
            Node n = new Node (item);
            Node p = this.head;
            for (int i = 0; i < index-1; i++){
                p = p.getNext();
            }
            n.setNext(p.getNext());
            p.setNext(n);
        }
        this.size++;
    }

    public T remove(int index){
        if (index > size-1){
            System.out.println("error: index out of range");
            return null;
        }

        Node p = this.head;
        if (index == 0){
            this.head = this.head.getNext();
            this.size --;
            return p.getThing();
        }

        //Node p = this.head;
        for (int i=0; i<index-1; i++){
            p = p.getNext();
        }
        Node removed = p.getNext();
        p.setNext(removed.getNext());
        this.size --;
        return removed.getThing();
    }

    public ArrayList <T> toArrayList(){
        ArrayList <T> arr = new ArrayList <T> ();
        for (T n: this ){
            arr.add(n);
        }
		return arr;
	}

	public ArrayList <T> toShuffledList(){
        ArrayList <T> array = this.toArrayList();
        ArrayList <T> shuffled = new ArrayList <T> (array.size());
		Random ran = new Random();
		while (array.size() > 0){
            int n = ran.nextInt(array.size());
            shuffled.add(array.remove(n));
        }
        return shuffled;
    }
    
    public T getFirst () {
        if (this.head != null){
            return this.head.getThing();
        }
        else{
            return null;
        }
    }

    public Iterator <T> iterator(){
        return new LLIterator(this.head);
    }

    private class Node { //in any occasion, not return Nodes to the user
        private Node next;
        private T thing;

        public Node (T item){
            this.next = null;
            this.thing = item;
        }

        public T getThing (){
            return this.thing;
        }

        public void setNext(Node n){
            this.next = n;
        }

        public Node getNext(){
            return this.next;
        }
    }

    private class LLIterator implements Iterator <T> { 
        Node curNode;  // the Node that holds the next node to be provided during the traversal

        public LLIterator(Node head){
            curNode = head;
        }

        public boolean hasNext(){
            if ( curNode == null ){
                return false;
            }
            return true;
        }

        public T next(){
            T n = curNode.getThing();
            curNode = curNode.getNext();
            return n;
        }

        public void remove(){
            return;
        }
    }
}