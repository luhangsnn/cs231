/*
Luhang Sun
CS 231 project 7
WordCounterBST.java
*/
import java.util.Arrays;

public class WordCounterBST{
    // creates a WordCounter object witch false for its useHashMap parameter =
    // it will initialize a BSTMap for the map field 
    public static void main (String [] args) {
        if (args.length == 0){
            System.out.println("Error: please provide filenames");
            return;
        }

        WordCounter wc = new WordCounter(false);

        //loop through each command line argument
        for (int i=0; i<args.length; i++){
            // analyze the file 5 times and records the time taken
            long values [] = new long [5];
            for (int j=0; j<5; j++){
                wc.reset();
                long startTime = System.currentTimeMillis();
                wc.analyze(args[i]);  
                long endTime = System.currentTimeMillis();
                values[j] = endTime - startTime;
            }
            
            // drop the lowest and the highest values and compute the avg time taken
            Arrays.sort(values);
            long sum = 0;
            for (int k=1; k<=3; k++){
                sum += values[k];
            }
            double avg = sum/1000.0/3.0;
            System.out.println(args[i] + "\nAvg time taken to analyze" +  ": " + avg + " sec");
            System.out.println("Totoal word count: " + wc.getTotalWordCount());
            System.out.println("Unique word count: " + wc.getUniqueWordCount());
            System.out.println("BSTDepth: " + wc.getBSTDepth() + "\n--------");
            
        }
    }
}