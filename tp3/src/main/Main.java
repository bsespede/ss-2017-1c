package main;

import simulation.Simulator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Simulator(150, 10, 4, 2, 4, 0.2, 5000, 0.1).simulate();

    }
}
