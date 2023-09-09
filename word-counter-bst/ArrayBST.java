/*
Luhang Sun
CS 231 project 6
ArrayBST.java
*/

//use an array to implement BST

import java.util.*;

public class ArrayBST <K, V> {
    private Object [] list;
    private int size;
    private Comparator <K> comp;

    public ArrayBST( Comparator<K> comp ) {
		this.comp = comp;
        this.size = 0;
        this.list = new Object [10];
    }
    
    public V put (K key, V value) {
        if (this.size == 0) { //empty tree
            this.list[0] = new KeyValuePair <K, V> (key, value);
            this.size++;
            return null;
        }
        return this.put(key, value, 0, this.comp);
    }


    @SuppressWarnings("unchecked")
    public V put (K newK, V newV, int rootIndex, Comparator<K> comp) {
        KeyValuePair <K, V> curRoot = (KeyValuePair <K, V>) this.list[rootIndex];
        int c = comp.compare (newK, curRoot.getKey());

        if (c == 0){
            V oldV = curRoot.getValue();
            curRoot.setValue(newV);
            return oldV;
        }
        else if (c < 0) { //put to the left of the current root
            int leftIndex = 2*rootIndex + 1;

            //double the size of the list if needed
            if (leftIndex > this.list.length-1){
                Object [] newlist = new Object [list.length * 2];
                for (int i = 0; i < this.list.length; i++){
                    newlist [i] = this.list[i];
                }
                this.list = newlist;
            }

            if (this.list[leftIndex] == null) {
                this.list [leftIndex] = new KeyValuePair <K,V> (newK, newV);
                this.size++;
                return null;
            }
            else {
                return put (newK, newV, leftIndex, comp);
            }
        }
        else { //put to the right of the current root
            int rightIndex = 2*rootIndex + 2;

            if (rightIndex > this.list.length-1){
                Object [] newlist = new Object [list.length * 2];
                for (int i = 0; i < this.list.length; i++){
                    newlist [i] = this.list[i];
                }
                this.list = newlist;
            }

            if (this.list[rightIndex] == null) {
                this.list [rightIndex] = new KeyValuePair <K,V> (newK, newV);
                this.size++;
                return null;
            }
            else{
                return put (newK, newV, rightIndex, comp);
            }
        }
    }

    public V get (K key) {
        if (this.size == 0){ 
            System.out.println ("Can't find Key: " + key);
            return null;
        }
        return get (key, 0, this.comp);
    }

    @SuppressWarnings("unchecked")
    public V get (K key, int rootIndex, Comparator<K> comp) {
        KeyValuePair <K, V> curRoot = (KeyValuePair <K, V>) this.list[rootIndex];
        int c = comp.compare (key, curRoot.getKey());
        if (c == 0){;
            return curRoot.getValue();
        }
        else if (c < 0) { //search the left of the tree
            int leftIndex = 2*rootIndex + 1;

            if (leftIndex > this.list.length-1){
                return null;
            }

            if (this.list[leftIndex] == null) {
                System.out.println ("Can't find Key: " + key);
                return null;
            }
            else {
                return get (key, leftIndex, comp);
            }
        }
        else { // search the right of the tree
            int rightIndex = 2*rootIndex + 2;

            if (rightIndex > this.list.length-1){
                return null;
            }

            if (this.list[rightIndex] == null) {
                System.out.println ("Can't find Key: " + key);
                return null;
            }
            else{
                return get (key, rightIndex, comp);
            }
        }
    }

    public boolean containsKey (K key) {
        if (this.size == 0){ return false; }
        return containsKey (key, 0, this.comp);
    }

    @SuppressWarnings("unchecked")
    public boolean containsKey (K key, int rootIndex, Comparator<K> comp) {
        KeyValuePair <K, V> curRoot = (KeyValuePair <K, V>) this.list[rootIndex];
        int c = comp.compare (key, curRoot.getKey());
        if (c == 0){;
            return true;
        }
        else if (c < 0) { //search the left of the tree
            int leftIndex = 2*rootIndex + 1;

            if (leftIndex > this.list.length-1 || this.list[leftIndex] == null){
                return false;
            }
            else {
                return containsKey (key, leftIndex, comp);
            }
        }
        else { // search the right of the tree
            int rightIndex = 2*rootIndex + 2;
            if (rightIndex > this.list.length-1 || this.list[rightIndex] == null){
                return false;
            }
            else{
                return containsKey (key, rightIndex, comp);
            }
        }
    }

    public int size() { return this.size(); }

    public void clear() {
        Object [] newlist = new Object [this.list.length];
        this.size = 0;
        this.list = newlist;
    }

    public static void main (String [] args){
        ArrayBST <String, Integer> bst = new ArrayBST<String, Integer>(new StringAscending());
        bst.put( "twenty", 20 );
		bst.put( "ten", 10 );
        bst.put( "eleven", 11 );
        bst.put( "five", 5 );
        bst.put( "six", 6 );

        for (int i=0; i < bst.list.length; i++){
            if (bst.list[i] != null) {
               System.out.println(bst.list[i]); 
            }
        }
        System.out.println("getting five:");
        System.out.println(bst.get("five"));
    }
}



