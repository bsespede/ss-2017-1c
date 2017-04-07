package main;

import Simulator.Simulation;

/**
 * Created by julian on 22/03/17.
 */
public class Main {

    public static void main(String[] args) {
    	System.out.println("Started simulation");
    	long time = System.currentTimeMillis();
    	
        Simulation s = new Simulation(40, 30, 5);
        s.simulate(200);
        
        System.out.println("Finished simulation in " + (System.currentTimeMillis() - time) / 1000f + " seconds...");
    }
}