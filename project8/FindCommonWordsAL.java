/*
Luhang Sun
CS 231 project 8
FindCommonWordsAL.java
*/
import java.util.*;
import java.text.DecimalFormat;

public class FindCommonWordsAL extends FindCommonWords {
    // use an ArrayList to identify the top N words

    public FindCommonWordsAL(Comparator <KeyValuePair<String,Integer>> c){
        super(c);
    }

    // use an ArrayList to sort out the N most common words
    public void printCommonWords (int N) {
        // System.out.println("using al");
        ArrayList <KeyValuePair<String, Integer>> entries = this.wc.getEntrySet();
        entries.sort(this.comp.reversed()); //sort the arraylist based in descending order
        DecimalFormat decimalFormat = new DecimalFormat("#0.0000");

        for (int i=0; i<N; i++){
            KeyValuePair <String,Integer> kvp = entries.get(i);
            double frequency = kvp.getValue() * 1.0 /this.wc.getTotalWordCount();
            System.out.println(kvp.getKey() + " " + decimalFormat.format(frequency));
        }
    }

    public static void main (String[] args) {
        if (args.length == 0){
            System.out.println("Usage: FindCommonWords <N> <WC file 1> <...>");
            return;
        }

        int N = Integer.parseInt(args[0]);

        FindCommonWordsAL find = new FindCommonWordsAL (new KVPValueComparator());
        for (int i=1; i<args.length; i++){
            long start = System.currentTimeMillis();
            System.out.println(args[i]);

            find.readWordCountFile(args[i]);
            find.printCommonWords(N);

            long end = System.currentTimeMillis();
            System.out.println("Time taken (ms): " + (end - start));
            System.out.println("--------------");
        }
    }
}