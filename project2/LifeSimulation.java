
/*
Luhang Sun
CS 231 Project 2
LifeSimulation.java
*/

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LifeSimulation {
    protected Landscape scape;
    private Random gen = new Random();

    // default initialization(no specific patterns)
    public LifeSimulation(int a, int b, double d) {
        this.scape = new Landscape(a, b);
        double density = d;
        for (int i = 0; i < scape.getRows(); i++) {
            for (int j = 0; j < scape.getCols(); j++) {
                scape.getCell(i, j).setAlive(gen.nextDouble() <= density);
            }
        }
    }

    public LifeSimulation(String pattern, int a, int b) {
        this.scape = new Landscape(a, b);
        if (pattern == "blinker") {
            this.buildBlinker();
        } else if (pattern == "spaceship") {
            this.buildSpaceship();
        } else {
            System.out.println("invalid shape command");
            return;
        }
    }

    public void buildBlinker() {
        for (int k = 0; k < 8; k++) {
            int c = gen.nextInt(scape.getRows() - 2) + 1; // boundary of row index
            int d = gen.nextInt(scape.getCols() - 2) + 1; // boundary of col index
            scape.getCell(c, d).setAlive(true);
            scape.getCell(c + 1, d).setAlive(true);
            scape.getCell(c - 1, d).setAlive(true);
        }
    }

    public void buildSpaceship() {
        ArrayList<Cell> ships = new ArrayList<Cell>(5);

        // adding cells that construct to the shape of spaceship
        ships.add(scape.getCell(5, 5));
        for (int k = 0; k <= 1; k++) {
            ships.add(scape.getCell(k + 5, 7));
            ships.add(scape.getCell(k + 6, 6));
        }

        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).setAlive(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner action = new Scanner(System.in);
        System.out.print("Enter height: ");
        String command1 = action.nextLine(); // read from command line
        int h = Integer.parseInt(command1); // height of the landscape

        System.out.print("Enter width: ");
        String command2 = action.nextLine();
        int w = Integer.parseInt(command2); // wdith of the landscape

        System.out.print("Enter scale: ");
        int s = Integer.parseInt(action.nextLine()); // scale of the landscape

        System.out.print("Enter density of the random initialization(a double between 0 and 1): ");
        double d = Double.parseDouble(action.nextLine());
        action.close();

        LifeSimulation sim = new LifeSimulation(w, h, d);
        // LifeSimulation sim = new LifeSimulation("spaceship", w, h);

        LandscapeDisplay display = new LandscapeDisplay(sim.scape, s);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(250);
            sim.scape.advance();
            display.repaint();
            // display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
        }
    }
}