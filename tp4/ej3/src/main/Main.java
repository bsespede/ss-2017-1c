package main;
import java.io.File;

import io.OutputWriter;
import simulation.Result;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {
	
	private static final double HOUR = 3600;
	private static final double DAY = HOUR * 24;
	private static final double YEAR = DAY * 365;
	
	private final static double MAX_TIME = YEAR * 5;
	private final static double MAX_FLIGHT_TIME = YEAR * 3;
	private final static double LAUNCH_FREQUENCY = DAY;
	private final static double INTERVAL = 100;
	private final static double INTERVAL_OUTPUT = DAY / 2;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		System.out.println("[INFO] Starting simulations");
		final OutputWriter writer = new OutputWriter("../results.dat");
		for (int v = 3; v <= 9; v += 3) {
			for (int angle = 0; angle < 360; angle += 45) {
				System.out.println("[INFO] Launching simulations for VELOCITY " + v + " and ANGLE " + angle);
				for (int i = 0; i < MAX_TIME / LAUNCH_FREQUENCY; i++) {
					System.out.println("[INFO] Launching simulation [" + i + "/" + (int)(MAX_TIME / LAUNCH_FREQUENCY) + "]");
					final String particlesOutputPath = "../output/velocidad-" + v + "/angulo-" + angle + "/dia-" + i;
					new File(particlesOutputPath).mkdirs();
					final Simulation simulation = new Simulation(particlesOutputPath, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, MAX_FLIGHT_TIME, angle, i * LAUNCH_FREQUENCY, v);    
					final Result result = simulation.simulate();
					writer.writeSimulationResult(result, angle, v);
				}
			}
		}
		writer.close();
		System.out.println("[INFO] Simulations ended");
	}

}