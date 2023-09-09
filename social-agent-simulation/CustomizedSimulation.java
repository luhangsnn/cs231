/*
Luhang Sun
CS231 Project 4
CustomizedSimulation.java
*/

import java.util.*;
import javax.swing.*;

public class CustomizedSimulation {
    private Landscape scape;
    private Random ran = new Random();

    public CustomizedSimulation(int w, int h, int n1, int n2, int n3){ // a simulation with max 3 types of agents
        //n1: number of agent 1
        //n2: number of agent 2
        //n3: number of agent 3
        this.scape = new Landscape (w, h);
        for (int i=0; i<n1; i++) {
            scape.addAgent(new CategorizedSocialAgent(ran.nextInt(w), ran.nextInt(h), 0));
        }

        for (int i=0; i<n2; i++) {
            scape.addAgent(new CategorizedSocialAgent(ran.nextInt(w), ran.nextInt(h), 2));
        }
        for (int i=0; i<n3; i++) {
            scape.addAgent(new CategorizedSocialAgent(ran.nextInt(w), ran.nextInt(h), 1));
        }
    }

    public Landscape getScape(){
        return this.scape;
    }

    public static void main(String [] args) throws InterruptedException{
        String s1 = JOptionPane.showInputDialog("Enter window size(100-700)");
        int a = Integer.parseInt(s1);

        //ask for the type of simualtion
        Object [] options = {"Social Agents Only", "Categorized Agents"};
        int option = JOptionPane.showOptionDialog(null, "What would you like to simulate?", "Agent Type", 
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE, null, options, null);

        String s = JOptionPane.showInputDialog("Enter radius");
        int r = Integer.parseInt(s);

        if (option == 0){
            String s2 = JOptionPane.showInputDialog("How many social agents?");
            int n1 = Integer.parseInt(s2);
            SocialAgentSimulation sim = new SocialAgentSimulation(a, a, n1);
            LandscapeDisplay display = new LandscapeDisplay(sim.getScape());

            for (int i=0; i<200; i++){   
                Thread.sleep(100);
                sim.getScape().updateAgents(r);
                display.repaint();
            }
        }
        else if (option == 1) {
            CustomizedSimulation sim;

            String s3 = JOptionPane.showInputDialog("How many types of categorized social agents? (Maximum 3)");
            int n2 = Integer.parseInt(s3);
            if (n2 > 3){
                System.out.println("Error: maximum 3 types of agents");
                return;
            }

            int t = 1; //agent category index

            ArrayList <Integer> d = new ArrayList <Integer> ();
            for (int i=0; i<n2; i++){
                String s4 = JOptionPane.showInputDialog("Enter the number of agent type " + t);
                d.add(Integer.parseInt(s4));
                t++;
            }
            
            //create the object
            if (n2 == 1){
                sim = new CustomizedSimulation(a, a, d.get(0), 0, 0);
            }
            else if (n2 ==2 ){
                sim = new CustomizedSimulation(a, a, d.get(0), d.get(1), 0);
            }
            else {
                sim = new CustomizedSimulation (a, a, d.get(0), d.get(1), d.get(2));
            }

            LandscapeDisplay display = new LandscapeDisplay(sim.getScape());

            for (int i=0; i<200; i++){   
                Thread.sleep(100);
                sim.getScape().updateAgents(r);
                display.repaint();
            }
        }
    }
}