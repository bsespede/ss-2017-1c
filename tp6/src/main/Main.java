package main;
import io.OutputWriter;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int N = 50;
	public final static double L = 5;
	public final static double W = 3;
	public final static double D = 0;
	public final static double kn = 1E5;
	public final static double kt = 2 * kn;

	private final static double MAX_TIME = 500;
	private final static double INTERVAL = 0.0005;
	private final static double INTERVAL_OUTPUT = 0.01;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
		final OutputWriter writer = new OutputWriter("./results.dat");
		final Simulation simulation = new Simulation("./output", INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, L, W, D, N);
		final Result result = simulation.simulate();
		writer.writeSimulationResult(result);
		writer.close();
		System.out.println("[INFO] Simulations ended");
	}

}