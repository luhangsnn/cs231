/*
Luhang Sun
CS 231 project 7
OpenHashingTester.java
*/

import java.io.*;
import java.util.Arrays;

public class CloseHashingTester {
    // tester class for CloseHashingMap using reddit files

    private CloseHashingMap <String, Integer> map;
    private int totalCount;

    public CloseHashingTester (){
        this.map = new CloseHashingMap<String, Integer> (1, new StringAscending());
        this.totalCount = 0;
    }

    // generates the word count of a file
    public void analyze (String filename){
        try{
            FileReader file = new FileReader (filename);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();

            while (line != null) {
                line = line.trim();
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
            System.out.println("WordCounter.analyze():: unable to open file " + filename);
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

    public void reset(){
        this.map.clear();
        this.totalCount = 0;
    }


    public static void main (String [] args){
        if (args.length == 0){
            System.out.println("Error: please provide a filename");
            return;
        }

        CloseHashingTester tester = new CloseHashingTester ();

        //loop through each command line argument
        for (int i=0; i<args.length; i++){
            // analyze the file 5 times and records the time taken
            long values [] = new long [5];
            for (int j=0; j<5; j++){
                tester.reset();
                long startTime = System.currentTimeMillis();
                tester.analyze(args[i]);  
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
            System.out.println("Totoal word count: " + tester.getTotalWordCount());
            System.out.println("Unique word count: " + tester.getUniqueWordCount());
            System.out.println("Num of collisions: " + tester.map.getCollisions() + "\n------------");
        }
    }
}