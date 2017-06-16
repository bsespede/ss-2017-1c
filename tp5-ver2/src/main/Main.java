package main;
import io.ResultWriter;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private static double L = 10;
	private static int N = 250;
	private final static double INTERVAL = 0.0001;
	private final static double INTERVAL_OUTPUT = 0.1;
	private final static double SIMULATION_TIME = 10;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
		for (int runId = 4; runId <= 4; runId++) {
			final Simulation simulation = new Simulation(runId, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, N, SIMULATION_TIME, L);
			final Result result = simulation.simulate();
			ResultWriter.writeResult(runId + "-", result);
			L += 5;
			N += 50;
		}
		System.out.println("[INFO] Simulations ended");
	}

}