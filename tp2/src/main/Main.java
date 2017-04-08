package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
        simulation = new Simulation(100, 200, 30, "primera");
        simulation.simulate(5000);
        simulation = new Simulation(100, 200, 50, "segunda");
        simulation.simulate(5000);
        simulation = new Simulation(100, 200, 70, "tercera");
        simulation.simulate(1000);
    }
}