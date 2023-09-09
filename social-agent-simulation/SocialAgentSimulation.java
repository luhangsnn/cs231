/*
Luhang Sun
CS231 Project 4
SocialAgentSimulation.java
*/

import java.util.*;

public class SocialAgentSimulation {
    private Landscape scape;
    private Random ran = new Random();

    public SocialAgentSimulation(int w, int h, int N){ 
        //N:the number of agents initialized on the landscape
        this.scape = new Landscape (w, h);
        for (int i=0; i<N; i++) {
            scape.addAgent(new SocialAgent(ran.nextInt(w), ran.nextInt(h)));
        }
    }

    public Landscape getScape (){
        return this.scape;
    }

    public static void main(String [] args) throws InterruptedException{
        if (args.length < 3 ){
            System.out.println("Error");
            return;
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);
        
        SocialAgentSimulation sim = new SocialAgentSimulation(a, b, n);
        LandscapeDisplay display = new LandscapeDisplay(sim.scape);

        for (int i=0; i<100; i++){   
            Thread.sleep(100);
            sim.scape.updateAgents(25);
            display.repaint();
        }
    }
}