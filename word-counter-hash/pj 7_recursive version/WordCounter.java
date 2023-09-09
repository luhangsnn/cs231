/*
Luhang Sun
CS 231 project 7
WordCount.java
*/
import java.io.*;
import java.util.*;

public class WordCounter {
    private MapSet<String, Integer> map;
    private int totalCount;

    // useHashMap decides whether the WordCounter uses a HashMap or BSTMap for storing data
    // true initializes a HashMap, false initializes a BSTMap
    public WordCounter(boolean useHashMap){
        if (useHashMap){
            this.map = new HashMap <String, Integer> (new StringAscending());
        }
        else{
            this.map = new BSTMap <String, Integer> (new StringAscending());
        }
        this.totalCount = 0;
    }

    //generates the word count of a file
    public void analyze (String filename){
        try{
            FileReader file = new FileReader (filename);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();

            while (line != null) {
                line = line.trim();
                    // split line into words. 
                    String[] words = line.split("[^a-zA-Z0-9']");
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i].trim().toLowerCase();
                        if (word.length() != 0){
                            Object oldvalue = this.map.get(word);
                            if (oldvalue != null) {
                                this.map.put(word, (Integer)oldvalue + 1);
                            }
                            else{ this.map.put(word, 1); }
                            this.totalCount++;
                        }
                    }
                line = buffer.readLine();
            }
            buffer.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("WordCounter.analyze()::unable to open file " + filename);
        }
        catch(IOException ex){
            System.out.println("WordCounter.analyze():: error reading file " + filename);
        }
    }

    //return the total number of words in the file (not the unique word)
    public int getTotalWordCount(){
        return this.totalCount;
    }

    //returns the number of unique words
    public int getUniqueWordCount(){
        return this.map.size();
    }

    // return the count of a word
    public int getCount (String word){
        return this.map.get(word);
    }

    //returns the frequency of a word
    public double getFrequency(String word){
        return (float) (getCount(word)/getTotalWordCount());
    }

    //creates a file with contents of the BSTMap
    public void writeWordCountFile (String filename){
        try {
            FileWriter file = new FileWriter (filename);  
            file.write ("totalWordCount: " + totalCount + "\n");

            //create an arraylist of entries (pairs)
            ArrayList <KeyValuePair<String,Integer>> entries = this.map.entrySet();
            for (KeyValuePair<String,Integer> kv: entries){
                file.write(kv.toString() + "\n");
            }
            file.close();            
        }
        catch(IOException ex){
            System.out.println("WordCounter.writeWordCountFile()::Unable to create  " + filename);
        }
    }

    // reads in the contents of a word count file and reconstructs the fields of a WordCount object
    public void readWordCountFile (String filename){
        this.map.clear();
        try {
            FileReader file = new FileReader (filename);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            String[] firstWords = line.split(": ");
            this.totalCount = Integer.parseInt(firstWords[1]); // setting the total word count

            line = buffer.readLine();
            while (line != null){
                String words [] = line.split(" ");
                this.map.put(words[0], Integer.parseInt(words[1]));
                line = buffer.readLine();
            }
            buffer.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("WordCounter.analyze()::unable to open file " + filename);
        }
        catch(IOException ex){
            System.out.println("WordCounter.analyze():: error reading file " + filename);
        }
    }

    //clears the word counter
    public void reset (){
        this.map.clear();
        this.totalCount = 0;
    }

    //return the level of BST if the map field is a BST
    public int getBSTDepth(){
        try{
            BSTMap<String, Integer> bst = (BSTMap <String, Integer>) this.map;
            return bst.getDepth();
        }
        catch(Exception e){
            System.out.println("unable to use method getDepth()");
            return 0;
        }
    }

    // The main method uses a hashmap to analyze the data
    // The BST implementation is written in BSTCounter.java
    public static void main (String [] args){
        if (args.length == 0){
            System.out.println("Error: please provide filenames");
            return;
        }
        WordCounter wc = new WordCounter(true);
        
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
            HashMap<String, Integer> hm = (HashMap <String, Integer>) wc.map;
            System.out.println("Num of collisions: " + hm.getNumCollision());
            System.out.println("Total word count: " + wc.getTotalWordCount());
            System.out.println("Unique word count: " + wc.getUniqueWordCount() + "\n-----------");

        }
        
        // prints out the distribution of data in the hash table
        // HashMap<String, Integer> hm = (HashMap <String, Integer>) wc.map;
        // hm.printTable();
    }
}