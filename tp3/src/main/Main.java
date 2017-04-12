package main;

import simulation.Simulator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Simulator(40, 10, 5, 1, 1, 0.1, 100000, 0.5).simulate();

    }
}
