package main;
import io.OutputWriter;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int N = 50;
	private final static double L = 3;
	private final static double W = 2;
	private final static double D = 1;
	
	private final static double MAX_RAND_GEN_TIME = 30;

	private final static double MAX_TIME = 5000;
	private final static double INTERVAL = 0.01;
	private final static double INTERVAL_OUTPUT = 100;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
		final OutputWriter writer = new OutputWriter("../results.dat");
		final Simulation simulation = new Simulation("../output", INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, L, W, D, N, MAX_RAND_GEN_TIME);    
		final Result result = simulation.simulate();
		writer.writeSimulationResult(result);
		writer.close();
		System.out.println("[INFO] Simulations ended");
	}

}