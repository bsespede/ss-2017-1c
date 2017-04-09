package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
        simulation = new Simulation(960, 320, 64, 32, 100, "primero");
        simulation.simulate(10000);
        simulation = new Simulation(960, 320, 128, 32, 100, "segundo");
        simulation.simulate(10000);
        simulation = new Simulation(960, 320, 256, 32, 100, "tercero");
        simulation.simulate(10000);
    }
}