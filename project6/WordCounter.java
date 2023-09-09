/*
Luhang Sun
CS 231 project 6
WordCount.java
*/
import java.io.*;
import java.util.ArrayList;

public class WordCounter {
    private BSTMap<String, Integer> bst;
    private int totalCount;

    public WordCounter(){
        this.bst = new BSTMap <String, Integer> (new StringAscending());
        this.totalCount = 0;
    }

    //generates wordcounts from a file
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
                            Object oldvalue = this.bst.get(word);
                            if (oldvalue != null) {
                                this.bst.put(word, (Integer)oldvalue + 1);
                            }
                            else{
                                this.bst.put(word, 1);
                            }
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
        return this.bst.size();
    }

    public int getCount (String word){
        return bst.get(word);
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
            ArrayList <KeyValuePair<String,Integer>> entries = this.bst.entrySet();
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
        this.bst.clear();
        try {
            FileReader file = new FileReader (filename);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            String[] firstWords = line.split(": ");
            this.totalCount = Integer.parseInt(firstWords[1]); // setting the total word count

            line = buffer.readLine();
            while (line != null){
                // line = line.trim();
                String words [] = line.split(" ");
                this.bst.put(words[0], Integer.parseInt(words[1]));
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
        this.bst.clear();
        this.totalCount = 0;
    }

    public static void main (String [] args){
        if (args.length == 0){
            System.out.println("error: pls provide a filename");
            return;
        }
        WordCounter wc = new WordCounter();
        
        //loop through each command line argument
        for (int i=0; i<args.length; i++){
            double startTime = System.currentTimeMillis();
            wc.analyze(args[i]); 

            //naming the word count file
            wc.writeWordCountFile("countfile_" + (2008+i) + ".txt");
            double endTime = System.currentTimeMillis();
            System.out.println("time taken (ms) for " + (2008+i) + ": " + (endTime - startTime));
            System.out.println("Totoal word count: " + wc.getTotalWordCount());
            System.out.println("Unique word count: " + wc.getUniqueWordCount() + "\n--------");

            wc.reset();
        }        
        //wc.writeWordCountFile("testfile.txt");
    }

}