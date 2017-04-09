package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
        simulation = new Simulation(600, 200, 50, "test");
        simulation.simulate(2000);
//        simulation = new Simulation(100, 200, 50);
//        simulation.simulate(1000);
//        simulation = new Simulation(100, 200, 70);
//        simulation.simulate(1000);
    }
}