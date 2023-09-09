/*
Luhang Sun
CS231 Project 4
CategorizedSocialAgentSimulation.java
*/

//need command line arguments for width and height of the landscape, radius and the numbers of two types of agents

import java.util.*;

public class CategorizedSocialAgentSimulation {
    private Landscape scape;
    private Random ran = new Random();

    public CategorizedSocialAgentSimulation(int w, int h, int n1, int n2){ 
        //n1: number of agent 1
        //n2: number of agent 2
        this.scape = new Landscape (w, h);
        for (int i=0; i<n1; i++) {
            scape.addAgent(new CategorizedSocialAgent(ran.nextInt(w), ran.nextInt(h), 0));
        }

        for (int i=0; i<n2; i++) {
            scape.addAgent(new CategorizedSocialAgent(ran.nextInt(w), ran.nextInt(h), 2));
        }
    }

    public static void main(String [] args) throws InterruptedException{
        if (args.length < 3 ){
            System.out.println("Error");
            return;
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int r = Integer.parseInt(args[2]);
        int n1 = Integer.parseInt(args[3]);
        int n2 = Integer.parseInt(args[4]);
        
        CategorizedSocialAgentSimulation sim = new CategorizedSocialAgentSimulation(a, b, n1, n2);
        LandscapeDisplay display = new LandscapeDisplay(sim.scape);

        for (int i=0; i<200; i++){   
            Thread.sleep(10);
            sim.scape.updateAgents(r);
            display.repaint();
        }
    }
}