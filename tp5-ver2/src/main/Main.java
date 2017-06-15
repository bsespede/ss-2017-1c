package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.ResultWriter;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int N = 200;
	private final static double INTERVAL = 0.001;
	private final static double INTERVAL_OUTPUT = 0.1;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();
	private static final long SIMULATION_TIME = 10;

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
//		 Ejercicio A, B, C
		for (int runId = 1; runId <= 1; runId++) {
			final Simulation simulation = new Simulation(runId, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, N, SIMULATION_TIME);
			final Result result = simulation.simulate();
			ResultWriter.writeResult("../" + runId + "-", result);
		}
		// Ejercicio D
//		int runId = 0;
//		final Map<Double, List<Double>> evacTimes = new HashMap<>();
//		for (double drivingSpeed = 0.8; drivingSpeed <= 6; drivingSpeed += (6.0 - 0.8) / 5.0) {
//			int times = 5;
//			final List<Double> evacuationTimeForSpeed = new ArrayList<>(times);
//			for (int run = 0; run < times; run++) {
//				final Simulation simulation = new Simulation(runId++, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, N, drivingSpeed);
//				final Double result = simulation.simulationOnlyEvacuation();
//				evacuationTimeForSpeed.add(result);
//			}
//			evacTimes.put(drivingSpeed, evacuationTimeForSpeed);
//		}
//		ResultWriter.writeEvacTimes("../", evacTimes);
		System.out.println("[INFO] Simulations ended");
	}

}