package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	System.out.println("Started simulation");
    	long time = System.currentTimeMillis();
    	
        Simulation simulation = new Simulation(300, 100, 75);
        simulation.simulate(2000);
        
        System.out.println("Finished simulation in " + (System.currentTimeMillis() - time) / 1000f + " seconds...");
    }
}