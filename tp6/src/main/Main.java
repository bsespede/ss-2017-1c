package main;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int N = 1;
	private final static double MAX_TIME = 100;
	private final static double INTERVAL = 0.001;
	private final static double INTERVAL_OUTPUT = 0.1;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
		final Simulation simulation = new Simulation("../output", INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, N);
		simulation.simulate();
		System.out.println("[INFO] Simulations ended");
	}

}