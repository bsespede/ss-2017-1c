package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
        simulation = new Simulation(6000, 2000, 300, "primero");
        simulation.simulate(10000);
        simulation = new Simulation(6000, 2000, 900, "segundo");
        simulation.simulate(10000);
        simulation = new Simulation(6000, 2000, 1200, "tercero");
        simulation.simulate(10000);
    }
}