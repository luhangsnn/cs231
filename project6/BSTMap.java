/*
Luhang Sun
CS 231 project 6
BSTMap.java
*/
import java.util.*;

public class BSTMap<K, V> implements MapSet<K, V> {
	private TNode root;    
	private int size;
	private Comparator<K> comp;

	// constructor: takes in a Comparator object
	public BSTMap( Comparator<K> comp ) {
		this.comp = comp;
		root = null;
		this.size = 0;
	}

	// adds or updates a key-value pair
	// If there is already a pair with new_key in the map, then update the pair's value to new_value.
	// If there is not already a pair with new_key, then add pair with new_key and new_value.
	// returns the old value or null if no old value existed
	public V put( K key, V value ) {
		if (!(this.containsKey(key))){
			this.size++;
		}
		if (this.root == null){
			TNode node = new TNode(key, value);
			this.root = node;
			return null;
		}
		return this.root.put(key, value, comp);
	}
		
	public V insert (K key, V value){
		if (this.root == null) {
			this.root = new TNode(key, value);
			return null;
		}
		return insert (this.root, key, value);
	}

	private V insert (TNode n, K key, V value){
		int c = this.comp.compare(key, n.key);
		if (c<0){
			if (n.left == null) {
				n.left = new TNode(key, value);
				// return null;
			}
			else return insert(n.left, key, value);
		}
		else if (c==0) {
			V old = n.dataPair.getValue();
			n.dataPair.setValue(value);
			return old;
		}
		else {
			if (n.right==null){
				n.right = new TNode(key, value);
				// return null;
			}
			else return insert(n.right, key, value);
		}
		return null;
	}


        // gets the value at the specified key or null
        public V get( K key ) {
			if (this.root == null){
				System.out.println("BST is empty!!");
				return null;
			}
			return this.root.get(key, this.comp);
		}
		
    public boolean containsKey( K key ){
        if (this.root == null){
			return false;
		}
        return this.root.containsKey(key, this.comp);
    }

	//return an arraylist of keys in-order
    public ArrayList<K> keySet(){
		ArrayList <K> keys = new ArrayList <K> ();
		this.root.keySet(keys);
		return keys;
    }

	//return an arraylist of values in-order
    public ArrayList<V> values(){
		ArrayList <V> valueList = new ArrayList <V> ();
		this.root.values(valueList);
		return valueList;
    }
    
    // return an ArrayList of pairs in pre-order
    public ArrayList<KeyValuePair<K,V>> entrySet(){
		ArrayList <KeyValuePair<K,V>> entries = new ArrayList <KeyValuePair<K,V>> ();
		if (this.root != null){
			this.root.entrySet(entries);
		}
		return entries;
    }

    // Returns the number of key-value pairs in the map.
    public int size() { return this.size; }
        
    // removes all mappings from this MapSet
    public void clear(){
		this.root = null;
		this.size = 0;
	}
	
	public void printInOrder(){
		if (this.root != null){
			this.root.printInOrder();
		}
	}

	//prints out the tree in pre-order
	public String toPreOrderString (){
		String s = "root: ";
		if (this.root != null) {
			s += this.root.toPreOrderString(" ");
		}
		return s;
	}

    //generate a string that prints the tree in level
    public String toLevelString (){
		String s = "";
		if (this.root == null){
			System.out.println("empty bst!");
			return s;
		}
        MyQueue <TNode> q = new MyQueue <TNode> ();
		q.offer(this.root);
		
        while (q.size != 0){
			TNode n = q.poll();
			s += n.dataPair.toString();
			if (n.left != null){
				q.offer(n.left);
			}
			if (n.right != null){
				q.offer(n.right);
			}
			s += "\n";
		}
		return s;
	}
	
	public void remove (K key) {
		if (this.root == null) { return; } //empty tree

		this. root = this.root.remove(key, this.root, comp);
	}


    private class TNode {
        private TNode left;
        private TNode right;
        private KeyValuePair <K, V> dataPair;

        public TNode( K k, V v ) {
			this.left = null;
			this.right = null;
			this.dataPair = new KeyValuePair <K, V> (k, v);
        }

		// Takes in a key, a value, and a comparator and inserts the TNode
		// Returns the old value of the node, if replaced, or null if inserted
        public V put( K key, V value, Comparator<K> comp ) {
			int c = BSTMap.this.comp.compare(key, this.dataPair.getKey());
			if (c < 0){ //put the key to the left
				if (this.left == null){ 
					this.left = new TNode(key, value);
				}
				else {
					return this.left.put (key, value, comp);
				}
			}
			else if (c == 0) { //the key exists, update its value
				V oldValue = this.dataPair.getValue();
				this.dataPair.setValue (value);
				return oldValue;
			}
			else{ //put the key to the right
				if (this.right == null){
					this.right = new TNode (key, value);
				}
				else{
					return this.right.put(key, value, comp);
				}
			}
			return null;            
        }

		// Takes in a key and a comparator
		// Returns the value associated with the key or null
        public V get( K key, Comparator<K> comp ) {
			int c = BSTMap.this.comp.compare(key, this.dataPair.getKey());
			if (c == 0){
				return this.dataPair.getValue();
			}
			else if (c < 0){
				if (this.left == null) 
					return null;
				return this.left.get(key, comp);
			}
			else{
				if (this.right == null) 
					return null;
				return this.right.get(key, comp);
			}
        }

        public boolean containsKey (K key, Comparator<K> comp){
			K original = this.dataPair.getKey();
			int c = BSTMap.this.comp.compare(key, original);
			if (c == 0) {return true;}
			else if (c < 0){ //on the left subtree
				if (this.left == null) {
					return false;
				}
				else{
					return this.left.containsKey(key, comp);
				}
			}
			else{ //on the right subtree
				if (this.right == null){
					return false;
				}
				else{
					return this.right.containsKey(key, comp);
				}
			}
		}

		// adds the keys to an arraylist in order
		public void keySet (ArrayList<K> key){
			if (this.left != null){
				this.left.keySet (key);
			}
			key.add(this.dataPair.getKey());
			if (this.right != null){
				this.right.keySet (key);
			}
		}

		//adds the values to an arraylist in order
		public void values (ArrayList<V> valueList){
			if (this.left != null){
				this.left.values(valueList);
			}
			valueList.add(this.dataPair.getValue());
			if (this.right != null){
				this.right.values(valueList);
			}
		}

		// adds the pair to an arrylist in pre-order
		public void entrySet (ArrayList <KeyValuePair<K,V>> entries){
			entries.add(this.dataPair);
			if (this.left != null){
				this.left.entrySet(entries);
			}
			if (this.right != null){
				this.right.entrySet(entries);
			}
		}
		
        public void printInOrder (){
			if (this.left != null){
				this.left.printInOrder();
			}
			System.out.println(this.dataPair);
			if (this.right != null){
				this.right.printInOrder();
			}
		}
		
		public String toPreOrderString (String a) {
			String s = a + this.dataPair.toString() + "\n";
			if (this.left != null){
				s += "left: " + this.left.toPreOrderString(a + "  ");
			}
			if (this.right != null){
				s += "right: " + this.right.toPreOrderString(a + "  ");
			}
			return s;
		}

		public void setRight (TNode n) { this.right = n; }
		
		public void setLeft (TNode n) { this.left = n; }


		//functions for remove
		
		//returns the largest TNode in the tree of the curent TNode
		public TNode getMax() {
			if (this.right != null) {
				return this.right.getMax ();
			}
			return this;
		}

		//returns the root of the new tree with maximum node removed
		public TNode removeMax() {
			if (this.right == null){
				return this.left; // replacing r w/ the left TNode
			}
			this.setRight(this.right.removeMax());
			return this;
		}

		public TNode remove (K key, TNode r, Comparator <K> comp) {
			if (r == null) {
				return null;
			}

			int c = BSTMap.this.comp.compare(key, r.dataPair.getKey());
			if (c == 0) { //r is the TNode to remove
				if (r.left == null && r.right == null) {
					return null;
				}
				else if (r.left == null) { //only has right child
					return r.right;
				}
				else if (r.right == null) { //only has left child
					return r.left;
				}
				else { //has two children
					//get the largest TNode in left sub-tree
					TNode leftMax = r.left.getMax();

					// remove the largest TNode from the left subtree
					r.setLeft(r.left.removeMax());

					leftMax.setLeft (r.left);
					leftMax.setRight (r.right);

					return leftMax;
				}
			}

			//r is not the one to remove
			else if (c < 0) { //remove from the left
				r.setLeft (this.remove (key, r.left, comp));
			}
			else { //remove from the right
				r.setRight(this.remove(key, r.right, comp));
			}
			return r;
		}
			
    }// end of TNode class


    // test function
    public static void main( String[] argv ) {
        // create a BSTMap
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new StringAscending() );

		bst.put( "twenty", 20 );
		bst.put( "ten", 10 );
        bst.put( "eleven", 11 );
        bst.put( "five", 5 );
		bst.put( "six", 6 );

        System.out.println( "getting eleven: " + bst.get( "eleven" ) );
        System.out.println( "getting none: " + bst.get( "none" ) );

		//printing the tree in different ways
		System.out.println("printing in order");
        bst.printInOrder();     

		System.out.println("Printing in pre-order");
		System.out.println(bst.toPreOrderString());
		
		//testing remove
		System.out.println("removed ten: ");
		bst.remove("ten");
		System.out.println(bst.toPreOrderString());

		
		// ArrayList <String> strKeys = bst.keySet();
		// for (String sk: strKeys){
		// 	System.out.println (sk);
		// }
	
		// ArrayList <Integer> valueList = bst.values();
		// for (Integer v: valueList){
		// 	System.out.println(v);
		// }

		// ArrayList <KeyValuePair<String, Integer>> entries = bst.entrySet();
		// for (KeyValuePair <String, Integer> data: entries){
		// 	System.out.println(data);
		// }

		// System.out.println("updating 6 " + bst.put("six", 2));
		// System.out.println(bst.put("five", 5));
		// bst.printInLevel();
    }
}

// comparator class separate
class StringAscending implements Comparator<String> {
	public int compare( String a, String b ) {
		return a.compareTo(b);
	}
}
