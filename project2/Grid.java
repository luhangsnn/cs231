
/*
Luhang Sun
CS 231 Project 2
Grid.java
*/

import java.util.Random;

public class Grid {
    private static String[][] ranger;

    public static void main(String[] args) {
        if (args.length > 0) {

            int yogi = Integer.parseInt(args[0]);
            int booboo = Integer.parseInt(args[1]);
            Random rnd = new Random();
            String letter = "qazwsxedcrfvtgbyhnujmikolp";

            ranger = new String[yogi][booboo];

            for (int i = 0; i < yogi; i++) {
                for (int j = 0; j < booboo; j++) {
                    char c = letter.charAt(rnd.nextInt(letter.length()));
                    String d = String.valueOf(c);
                    ranger[i][j] = d;
                    System.out.print(ranger[i][j] + " ");
                }
                System.out.println("");
            }
        } else {
            System.out.println("no argument found");
            return;
        }
    }
}
