package main;
import io.ResultWriter;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int N = 100;
	private final static double INTERVAL = 0.001;
	private final static double INTERVAL_OUTPUT = 0.1;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulation");
		// Ejercicio A, B, C
		for (int runId = 0; runId < 10; runId++) {
			final Simulation simulation = new Simulation(runId, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, N, 0.8d);
			final Result result = simulation.simulate();
			ResultWriter.writeResult("../" + runId++ + "-", result);
		}
		// Ejercicio D
//		int runId = 0;
//		final Map<Double, List<Double>> evacTimes = new HashMap<>();
//		for (double drivingSpeed = 0.8; drivingSpeed <= 6; drivingSpeed += (6.0 - 0.8) / 10.0) {
//			int times = 10;
//			final List<Double> evacuationTimeForSpeed = new ArrayList<>(times);
//			for (int run = 0; run < times; run++) {
//				final Simulation simulation = new Simulation(runId++, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, N, drivingSpeed);
//				final Result result = simulation.simulate();
//				evacuationTimeForSpeed.add(result.getEvacuationTime());
//			}
//			evacTimes.put(drivingSpeed, evacuationTimeForSpeed);
//		}
//		ResultWriter.writeEvacTimes("../", evacTimes);
		System.out.println("[INFO] Simulations ended");
	}

}