package main;

import Simulator.Simulation;

/**
 * Created by julian on 22/03/17.
 */
public class Main {

    public static void main(String[] args) {
        Simulation s = new Simulation(40, 30, 5);
        s.simulate(200);
    }
}