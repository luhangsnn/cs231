/*
Luhang Sun
CS 231 project 8
NodePQHeap.java
*/
import java.util.*;

public class NodePQHeap <K, V> {
    // node based implementation of heap using the concept of binary representation
    private Node root;
    private int size; // the number of Nodes in the heap
    private Comparator<KeyValuePair<K, V>> comp;

    public NodePQHeap (Comparator<KeyValuePair<K, V>> comp) {
        this.root = null;
        this.size = 0;
        this.comp = comp;
    }

    public int size() {return this.size;}

	// add to the heap in a way that it's always a complete tree 
    public void add (K key, V value){
        if (this.size == 0){
			this.size++;
            this.root = new Node(key, value, null);
            return;
        }

        Node pointer = this.root;
		this.size++;
        String binary = Integer.toBinaryString(this.size);
		int n = binary.length();

        // traverse using the indications from the binary string
        // 0 for go left
        // 1 for go right
        for (int i = 1; i < n-1; i++) {
            if (binary.substring(i, i+1).equals("0")){
                pointer = pointer.left;
            }
            else { pointer = pointer.right;}
        }

		Node newNode = new Node (key, value, pointer);
		if (binary.substring(n-2, n-1).equals("0")){
			pointer.left = newNode;
		}
		else{
			pointer.right = newNode;
		}
		// this.reheapUp (newNode); 
    }

    private class Node {
        private Node left, right, parent;
        private KeyValuePair <K, V> dataPair;

        public Node(K key, V value, Node parent) {
			this.left = null;
			this.right = null;
			this.parent = parent;
			this.dataPair = new KeyValuePair <K, V> (key, value);
        }	
    }// end of Node class

}