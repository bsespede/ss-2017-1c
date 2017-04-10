package main;

import simulation.Simulator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Simulator(3, 10, 5, 0.2, 1, 0.05, 100, 0.1).simulate();

    }
}
