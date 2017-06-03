package main;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int N = 100;
	private final static double MAX_TIME = 1000;
	private final static double INTERVAL = 0.00001;
	private final static double INTERVAL_OUTPUT = 0.1;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
		final int runId = 0;
		final Simulation simulation = new Simulation(runId, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, N);
		final Result result = simulation.simulate();
		result.writeResults(runId);
		System.out.println("[INFO] Simulations ended");
	}

}