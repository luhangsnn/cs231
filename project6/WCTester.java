/*
Luhang Sun
CS 231 project 6
WCTester.java
*/

public class WCTester {
    public static void main (String [] args){
        WordCounter wc = new WordCounter ();
        wc.analyze ("counttest.txt");
        wc.writeWordCountFile("counts_ct.txt");
        wc.readWordCountFile("counts_ct.txt");
        wc.writeWordCountFile("counts_ct_v2.txt");
    }
}