package main;

import Simulator.Simulation;

public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
        simulation = new Simulation(960, 320, 64, "primero");
        simulation.simulate(10000);
        simulation = new Simulation(960, 320, 128, "segundo");
        simulation.simulate(10000);
        simulation = new Simulation(960, 320, 256, "tercero");
        simulation.simulate(10000);
    }
}