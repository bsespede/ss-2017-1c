package main;
import java.util.HashMap;
import java.util.Map;

import io.ResultWriter;
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
		// Ejercicio A, B, C
		for (int runId = 0; runId < 10; runId++) {
			final Simulation simulation = new Simulation(runId++, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, N, 0.8d);
			final Result result = simulation.simulate();
			ResultWriter.writeResult("../" + runId + "-", result);
		}
		// Ejercicio D
		//int runId = 0;
		//final Map<Double, Double> evacTimes = new HashMap<>();
		//for (double drivingSpeed = 0.8; drivingSpeed <= 6.0; drivingSpeed += (6.0 - 0.8) / 10.0) {
		//	final Simulation simulation = new Simulation(runId++, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, N, drivingSpeed);
		//	final Result result = simulation.simulate();
		//	evacTimes.put(drivingSpeed, result.getEvacuationTime());
		//}
		//ResultWriter.writeEvacTimes("../", evacTimes);
		System.out.println("[INFO] Simulations ended");
	}

}