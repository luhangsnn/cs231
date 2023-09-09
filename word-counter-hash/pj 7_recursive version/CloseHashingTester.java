/*
Luhang Sun
CS 231 project 7
OpenHashingTester.java
*/

import java.io.*;

public class CloseHashingTester {
    private CloseHashingMap <String, Integer> map;
    private int totalCount;

    public CloseHashingTester (){
        map = new CloseHashingMap<String, Integer> (1, new StringAscending());
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


    public static void main (String [] args){
        if (args.length == 0){
            System.out.println("Error: please provide a filename");
            return;
        }

        CloseHashingTester tester = new CloseHashingTester ();

        long startTime = System.currentTimeMillis();
        tester.analyze(args[0]);
        long endTime = System.currentTimeMillis();

        System.out.println(args[0] + "\nTime taken: " + (endTime - startTime)/1000.0);
        System.out.println("Totoal word count: " + tester.getTotalWordCount());
        System.out.println("Unique word count: " + tester.getUniqueWordCount());
    }
}