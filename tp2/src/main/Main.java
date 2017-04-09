package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
        simulation = new Simulation(3000, 1000, 250, "primero");
        simulation.simulate(10000);
        simulation = new Simulation(3000, 1000, 500, "segundo");
        simulation.simulate(10000);
        simulation = new Simulation(3000, 1000, 750, "tercero");
        simulation.simulate(10000);
    }
}