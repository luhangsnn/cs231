/*
Luhang Sun
CS 231 Project 2
LifeSimulation.java
*/

import java.util.Random;
//import java.util.Scanner;

public class LifeSimulation{
    static Landscape scape;

    //default initialization(no specific patterns)
    public static void build(){
        Random gen = new Random();
        double density = 0.5;

        for (int i=0; i<scape.getRows(); i++){
            for (int j=0; j<scape.getCols(); j++){
                scape.getCell(i, j).setAlive(gen.nextDouble() < density);
            }
        }
    }
    public static void main(String [] args) throws InterruptedException{
        if (args.length == 0) {
            System.out.println("no command found");
            return;
        }

        int w = Integer.parseInt(args[0]);
        int h = Integer.parseInt(args[0]);
        scape = new Landscape(w, h);
        build(); //initialize the cell grid

        LandscapeDisplay display = new LandscapeDisplay(scape, 8);

        for (int i=0; i<100; i++){
            Thread.sleep(250);
            scape.advance();
            display.repaint();
            //display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png" );
        }
    }
}