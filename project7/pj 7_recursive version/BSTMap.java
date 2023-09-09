/*
Luhang Sun
CS 231 project 7
BSTMap.java
*/
import java.util.*;

public class BSTMap<K, V> implements MapSet<K, V> {
        private TNode root;    
        private int size; // number of Nodes in the tree i.e. unique word count
        private Comparator<K> comp;

	// constructor: takes in a Comparator object
	public BSTMap( Comparator<K> comp ) {
		this.comp = comp;
		root = null;
		this.size = 0;
	}

	public K getRootKey(){
		return this.root.dataPair.getKey();
	}

	// adds or updates a key-value pair
	// If there is already a piar with new_key in the map, then update the pair's value to new_value.
	// If there is not already a pair with new_key, then add pair with new_key and new_value.
	// returns the old value or null if no old value existed
	public V put( K key, V value ) {
		if (this.root == null){
			this.size++;
			TNode node = new TNode(key, value);
			this.root = node;
			return null;
		}
		return this.root.put(key, value, comp);
    }

	// gets the value at the specified key or null
	public V get( K key ) {
		if (this.root == null){
			// System.out.println("BST is empty!!");
			return null;
		}
		return this.root.get(key, this.comp);
	}

    // functions in the MapSet interface
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
			this.root.printOrder();
		}
	}

	public void printPostOrder(){
		if (this.root != null){
			this.root.printPostOrder();
		}
	}

    //print in level
    public void printInLevel (){
		if (this.root == null){
			System.out.println("empty bst!");
		}
        MyQueue <TNode> q = new MyQueue <TNode> ();
        q.offer(this.root);
        while (q.size != 0){
			TNode n = q.poll();
			System.out.println(n.dataPair);
			if (n.left != null){
				q.offer(n.left);
			}
			if (n.right != null){
				q.offer(n.right);
			}
        }
	}
	
	public int getDepth(){
		if (this.root == null){
			return 0;
		}
		return this.root.getDepth();
	}

    private class TNode {
        private TNode left, right;
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
					BSTMap.this.size++;
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
					BSTMap.this.size++;
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
		
        public void printOrder (){
			if (this.left != null){
				this.left.printOrder();
			}
			System.out.println(this.dataPair);
			if (this.right != null){
				this.right.printOrder();
			}
        }

        public void printPostOrder() {
			if (this.left != null) {
				this.left.printPostOrder();
			}
			if (this.right != null) {
				this.right.printPostOrder();
			}
			System.out.println( this.dataPair );
		}

		// return the number of levels of the BST
		public int getDepth(){
			int leftCount = 0;
			int rightCount = 0;

			if (this.left != null){
				leftCount = this.left.getDepth();
			}
			if (this.right != null){
				rightCount = this.right.getDepth();
			}

			if (leftCount < rightCount){
				return rightCount + 1;
			}
			else{
				return leftCount + 1;
			}
		}	
    }// end of TNode class


    // test function of BSTMap
    public static void main( String[] argv ) {
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new StringAscending() );

		bst.put( "twenty", 20 );
		bst.put( "ten", 10 );
        bst.put( "eleven", 11 );
        bst.put( "five", 5 );
		bst.put( "six", 6 );
		bst.put( "six", 6666 );

        System.out.println( "getting eleven: " + bst.get( "eleven" ) );
        System.out.println( "getting none: " + bst.get( "none" ) );

		//printing the tree in different ways
		System.out.println("printing in order");
        bst.root.printOrder();     

        System.out.println("Printing in levels");
		bst.printInLevel();

		System.out.println("number of levels: "+ bst.getDepth());
    }
}

// comparator class separate
class StringAscending implements Comparator<String> {
	public int compare( String a, String b ) {
		return a.compareTo(b);
	}
}
