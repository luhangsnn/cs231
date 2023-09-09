/*
Luhang Sun
CS 231 project 7
HashMap.java
*/
import java.util.*;

@SuppressWarnings("unchecked")
public class HashMap <K, V> implements MapSet <K, V> {
    private Object [] table;
    private Comparator<K> comp;
    private int size, num_collision, num_filledPositions;

    public HashMap (Comparator <K> c){
        this.table = new Object [10];
        this.comp = c;
        this.size = 0;
        this.num_collision = 0;
        this.num_filledPositions = 0;
    }

    //returns the Hash index when the input key is String
    public int hashIndex (K key){
        int i = Math.abs(key.hashCode());
        return i % this.table.length;
    }

    // put an element in the hash table, return the old value if the key exists, otherwise return null
    // insert and expand a BST if collision takes place
    public V put (K new_key, V new_value) {
        this.ensureCap();
        int index = this.hashIndex (new_key);
        if (this.table[index] == null) {
            BSTMap<K, V> bst = new BSTMap<K, V> (this.comp);
            this.table [index] = bst;
            this.size++; 
            this.num_filledPositions++;
            return bst.put(new_key, new_value);
        }
        else{
            BSTMap<K, V> bst = (BSTMap<K, V>) this.table[index];
            if (!bst.containsKey(new_key)){
                this.size++;
                this.num_collision++;
            }
            return bst.put(new_key, new_value);
        }  
    }

    // expand the array is it's half filled
    private void ensureCap (){
        //if the BSTs are not large, do nothing
        if (this.num_filledPositions < this.table.length/2 ) { return; }

        //copy values in the current hash table
        Object [] copy = new Object[this.table.length];

        for (int i=0; i < table.length; i++){
            copy [i] = this.table [i];
        }

        //reset the stats
        this.num_collision = 0;
        this.size = 0;
        this.num_filledPositions = 0;
        this.table = new Object[2 * table.length];

        //re-add all data to the larger hash table
        for (int j = 0; j < copy.length; j++){
            if (copy[j] != null) {
                BSTMap<K, V> tree = (BSTMap <K, V>) copy[j];
                ArrayList <KeyValuePair<K, V>> entries = tree.entrySet();
                for (KeyValuePair<K, V> kvp: entries){
                    this.put(kvp.getKey(), kvp.getValue());
                }
            }
        }
    }

    public boolean containsKey (K key){
        int index = this.hashIndex (key);
        if (this.table[index] == null){
            return false;
        }
        BSTMap<K, V> bst = (BSTMap<K, V>) this.table[index];
        return bst.containsKey(key);
    }

    public V get (K key){
        int index = this.hashIndex (key);
        if (this.table[index] == null){
            return null;
        }
        BSTMap<K, V> bst = (BSTMap<K, V>) this.table[index];
        return bst.get(key);
    }

    // Returns an ArrayList of all the keys in the map
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<K>();
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null){              
                BSTMap<K, V> bst = (BSTMap<K, V>) this.table[i];
                ArrayList<K> bstkeys = bst.keySet();
                keys.addAll(bstkeys);
            }
        }
        return keys;
    }

    // Returns an ArrayList of all the values in the map
    public ArrayList<V> values(){
        ArrayList<V> values = new ArrayList<V>();
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null){
                BSTMap<K, V> bst = (BSTMap<K, V>) this.table[i];
                ArrayList<V> bstvalues = bst.values();
                values.addAll(bstvalues);
            }
        }
        return values;
    }

    // return an ArrayList of KVPs
    public ArrayList<KeyValuePair<K,V>> entrySet(){
        ArrayList<KeyValuePair<K,V>> entries = new ArrayList<KeyValuePair<K,V>>();
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null){
                BSTMap<K, V> bst = (BSTMap<K, V>) this.table[i];
                ArrayList<KeyValuePair<K,V>> bstentries = bst.entrySet();
                entries.addAll(bstentries);
            }
        }
        return entries;
    } 

    public int size(){
        return this.size;
    }

    public void clear(){
        this.table = new Object [10];
        this.size = 0;
        this.num_collision = 0;
        this.num_filledPositions = 0;
    }

    //prints out the array and BST at each indexed location
    public void printInLevel () {
        for (int i=0; i<this.table.length; i++){
            System.out.println(i);
            if (this.table[i] != null){
                BSTMap<K, V> bst = (BSTMap<K, V>) this.table[i];
                bst.printInLevel();  
            }
            System.out.println("");
        }
    }

    public int getNumCollision(){
        return this.num_collision;
    }

    // checks the efficiency of the hash function i.e. how well it spreads out the data
    // by printing out the number of elements at each position in the table
    public void printTable (){
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null) {
                BSTMap<K, V> bst = (BSTMap<K, V>) this.table[i];
                System.out.println(i + ": " + bst.size());
            }
            else{
                System.out.println(i + ": " + 0);
            }
            // if (i > 100){
            //     break;
            // }
        }
    }

    public static void main(String[] args){
        HashMap <String, Integer> hash = new HashMap<String, Integer>(new StringAscending());
        hash.put ("one", 1);
        hash.put ("two", 2);
        hash.put ("three", 3);
        hash.put ("four", 4);
        System.out.println(hash.num_collision);
        hash.put ("four", 4444);
        hash.printInLevel();
    }

}