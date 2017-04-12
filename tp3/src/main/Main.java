package main;

import simulation.Simulator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Simulator(150, 10, 5, 1, 1, 0.1, 10000, 0.1).simulate();

    }
}
