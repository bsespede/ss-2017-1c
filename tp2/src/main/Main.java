package main;

import Simulator.Simulation;


public class Main {

    public static void main(String[] args) {
    	Simulation simulation;
//        simulation = new Simulation(960, 320, 32, 16, 100, "primero");
//        simulation.simulate(10000);
//        simulation = new Simulation(960, 320, 128, 16, 100, "segundo");
//        simulation.simulate(10000);
//        simulation = new Simulation(960, 320, 256, 16, 100, "tercero");
//        simulation.simulate(10000);
        simulation = new Simulation(3072, 1024, 256, 64, 300, "mega-4");
        simulation.simulate(30000);
    }
}