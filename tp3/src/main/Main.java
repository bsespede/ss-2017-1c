package main;

import simulation.Simulator;

public class Main {

    public static void main(String[] args) {
        new Simulator(10, 10, 5, 2, 1, 0.1, 10, 0.1).simulate();

    }
}
