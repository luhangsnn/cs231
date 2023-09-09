/*
Luhang Sun
CS 231 project 8
FindCommonWords.java
*/
import java.util.*;
import java.text.DecimalFormat;

public class FindCommonWords {
    protected WordCounter wc;
    private PQHeap <KeyValuePair<String,Integer>> heap;
    protected Comparator <KeyValuePair<String,Integer>> comp;

    public FindCommonWords(Comparator <KeyValuePair<String,Integer>> c){
        this.comp = c;
        this.wc = new WordCounter (false); //initialize a wordcounter using BST
        this.heap = new PQHeap <KeyValuePair<String,Integer>> (this.comp);
    }

    // construct BST from word count file
    public void readWordCountFile (String filename) {
        this.wc.readWordCountFile(filename);
    }

    // use a heap to organize the kvp in order based on the word's frequency
    public void printCommonWords(int N) {
        wc.addToPQ(this.heap);

        DecimalFormat decimalFormat = new DecimalFormat("#0.0000"); //limit decimals of the double to ten-thousands
        for (int j=0; j<N; j++) {
            KeyValuePair <String,Integer> kvp = this.heap.remove();
            double frequency = kvp.getValue() * 1.0 /this.wc.getTotalWordCount();
            System.out.println(kvp.getKey() + " " + decimalFormat.format(frequency));
        }
    }

    // return the number of unique words in the heap
    public int getCount() {
        return this.wc.getUniqueWordCount();
    }

    public static void main (String[] args) {
        if (args.length == 0){
            System.out.println("Usage: FindCommonWords <N> <WC file 1> <...>");
            return;
        }
        int N = 0;
        try{
            N = Integer.parseInt(args[0]);
        }
        catch(Exception e){
            System.out.println("Usage Error: FindCommonWords <N> <WC file 1> <...>");
            return;
        }

        FindCommonWords find = new FindCommonWords (new KVPValueComparator());
    
        for (int i=1; i<args.length; i++){
            long start = System.currentTimeMillis();
            System.out.println(args[i]);

            find.readWordCountFile(args[i]);
            find.printCommonWords(N);
            
            long end = System.currentTimeMillis();
            System.out.println("Time taken (ms): " + (end - start));
            System.out.println("---------------------");
        }       
    }
}

// a KVP comparator based on the value
class KVPValueComparator implements Comparator <KeyValuePair<String,Integer>> {
    public int compare( KeyValuePair<String, Integer> i1, KeyValuePair<String, Integer> i2 ) {
        return i1.getValue() - i2.getValue();
    }
}