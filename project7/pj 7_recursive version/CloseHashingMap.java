/*
Luhang Sun
CS 231 project 7
CloseHashingMap.java
*/
import java.util.*;

@SuppressWarnings("unchecked")
public class CloseHashingMap <K, V> implements MapSet<K, V> {
    private Object [] table;
    private Comparator<K> comp;
    private int size, num_filledPositions, stepLength; //length of each probing

    public CloseHashingMap(int s, Comparator <K> c){
        this.table =  new Object [2];
        this.size = 0;
        this.num_filledPositions = 0;
        this.stepLength = s;
        this.comp = c;
    }

    //returns the Hash index when the input key is String
    public int hashIndex (K key){
        int i = Math.abs(key.hashCode());
        return i % this.table.length;
    }

    // put an element in the hash table, return the old value if the key exists, otherwise return null
    // use linear probing if collisions occur
    public V put (K new_key, V new_value) {
        this.expandCap();
        int index = this.hashIndex (new_key);
        return this.putProbing (new_key, new_value, index);
    }

    private V putProbing (K new_key, V new_value, int index){
        if (index > this.table.length-1){
            index = index % this.table.length; //wrapping to the beginning of the table if the index reaches the end of the array
        }
        if (this.table[index] == null) { // put the KVP if the position is empty
            KeyValuePair<K, V> kvp = new KeyValuePair<K, V> (new_key, new_value); 
            this.table [index] = kvp;
            this.size++; 
            this.num_filledPositions++; 
            return null;
        }

        KeyValuePair<K, V> i = (KeyValuePair<K, V>) this.table[index];
        int c = this.comp.compare(new_key, i.getKey());
        if (c == 0) { // update the value if the key exists
            V oldValue = i.getValue();
            i.setValue(new_value);
            return oldValue;
        }
        else{ // keep probing until found empty space
            return this.putProbing(new_key, new_value, index + this.stepLength);
        }
    }

    // expand the array if it's half filled
    private void expandCap (){
        // do nothing if the array is not half filled
        if (this.num_filledPositions < this.table.length/2 ) { return; }

        //copy values in the current hash table
        Object [] copy = new Object[this.table.length];

        for (int i=0; i < table.length; i++){
            copy [i] = this.table [i];
        }

        //reset the stats
        this.size = 0;
        this.num_filledPositions = 0;
        this.table = new Object[2 * table.length];

        // re-add all data to the larger hash table
        for (int j=0; j<copy.length; j++){
            if (copy[j] != null){
                KeyValuePair <K, V> kvp = (KeyValuePair <K, V>) copy[j];
                this.put(kvp.getKey(), kvp.getValue());
            }
        }
    }

    public boolean containsKey(K key){
        int index = this.hashIndex (key);
        return this.containsKeyProbing(key, index); //calls the recursive function probing
    }

    // check if contains key by probing
    private boolean containsKeyProbing (K key, int index){
        if (index > this.table.length-1){
            index = index % this.table.length;
        }
        if (this.table[index] == null){
            return false;
        }
        
        KeyValuePair<K, V> i = (KeyValuePair<K, V>) this.table[index];
        if (i.getKey().equals(key)){
            return true;
        }
        return this.containsKeyProbing(key, index + this.stepLength);
    }

    public V get (K key) {
        int index = this.hashIndex (key);
        return this.getProbing(key, index); //call the recursive get function
    }

    // get by recursively probing
    private V getProbing (K key, int index){
        if (index > this.table.length-1){
            index = index % this.table.length;
        }

        if (this.table[index] == null){
            return null;
        }
        KeyValuePair<K, V> i = (KeyValuePair<K, V>) this.table[index];
        if (i.getKey().equals(key)){
            return i.getValue();
        }
        return this.getProbing(key, index + this.stepLength);
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<K>();
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null){
                KeyValuePair<K, V> kvp = (KeyValuePair<K, V>) this.table[i];
                keys.add(kvp.getKey());
            }
        }
        return keys;
    }

    public ArrayList <V> values() {
        ArrayList <V> value = new ArrayList<V>();
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null){
                KeyValuePair<K, V> kvp = (KeyValuePair<K, V>) this.table[i];
                value.add(kvp.getValue());
            }
        }
        return value;
    }

    public ArrayList<KeyValuePair<K,V>> entrySet(){
        ArrayList<KeyValuePair<K,V>> entries = new ArrayList<KeyValuePair<K,V>>();
        for (int i=0; i<this.table.length; i++){
            if (this.table[i] != null){
                KeyValuePair<K, V> kvp = (KeyValuePair<K, V>) this.table[i];
                entries.add(kvp);
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
        this.num_filledPositions = 0;
    }

    // print out the hash table
    public void print () {
        for (int i=0; i<this.table.length; i++){
            System.out.println(i);
            if (this.table[i] != null){
                KeyValuePair<K, V> kvp = (KeyValuePair<K, V>) this.table[i];
                System.out.println(kvp);
            }
            System.out.println("");
        }
    }

    public static void main(String [] args){
        //create a close hashmap with probing size 1
        CloseHashingMap <String, Integer> hash = new CloseHashingMap<String, Integer>(1, new StringAscending());        
        hash.put( "twenty", 20 );
		hash.put( "ten", 10 );
        hash.put( "eleven", 11 );
        hash.put( "five", 5 );
		hash.put( "six", 6 );
		hash.put( "six", 6666 );
		hash.put( "six", 66666 );
        hash.print();
    }
}