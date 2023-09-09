/*
Luhang Sun
CS 231 project 8
FindTrends.java
*/
import java.io.*;

public class FindTrends {
    // use the BST to read in multiple word count files and generate the trends of words indicated in command line
    
    public static void main (String [] args) {
        if (args.length == 0){
            System.out.println("Usage: <BaseFilename> <FileNumberBegin> <FileNumberEnd> <Word1> <Word2>...");
            return;
        }

        WordCounter wc = new WordCounter(false);
        int fileNumBegin = Integer.parseInt(args[1]);
        int fileNumEnd = Integer.parseInt(args[2]);
        String BaseFileName = args[0];

        try{
            // create a filewriter object
            FileWriter output = new FileWriter("WordTrends.csv");
            output.append(" " + ",");
            // write the headings
            for (int k = 3; k <args.length; k++){
                output.append(args[k] + ",");
            }

            // loop through each file
            for (int i = fileNumBegin; i <= fileNumEnd; i++){
                output.write("\n");
                output.append(i + ",");
                wc.readWordCountFile(BaseFileName + Integer.toString(i) + ".txt");
                // loop through each word
                for (int j = 3; j < args.length; j++){
                    output.append(Double.toString(wc.getFrequency(args[j])) + ",");
                }
            }
            output.flush();
            output.close();
        }
        catch (Exception e){
            System.out.println("Error writing CSV file");
            return;
        }
    }
}