/*
Luhang Sun
CS231 Project 8
MyQueue.java
*/

import java.util.*;

public class MyQueue <T> implements Iterable <T>{
    Node head;
    Node tail;
    int size;

    public MyQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void offer (T item) { //add to the tail
        Node newNode = new Node(item);
        
        //check if list is empty
        if (this.tail == null){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            this.tail = newNode;
        }
        this.size++;
    }

    public T poll (){ //remove the head
        //check if the queue is empty
        if (this.size == 0){
            return null;
        }

        T thing = this.head.getThing();
        if (this.size == 1){
            this.head = null;
            this.tail = null;
            this.size--;
            return thing;
        }
        this.head = this.head.getNext();
        this.head.setPrev(null);
        this.size--;
        return thing;
    }

    public T peek (){
        if (this.size ==0){
            return null;
        }
        return this.head.getThing();
    }

    public Iterator <T> iterator(){
        return new LLIterator(this.head);
    }


    private class Node {
        private Node next;
        private Node prev;
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

        public void setPrev(Node n){
            this.prev = n;
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
    }

    public String toString (){
        String s ="";
        for (T t: this){
            s += t + " | ";
        }
        return s;
    }
}