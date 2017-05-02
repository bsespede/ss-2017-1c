package main;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static double INTERVAL = 0.01;
	private final static double MAX_STEPS = 1000;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		final Simulation simulation = new Simulation(INTEGRATOR, INTERVAL, MAX_STEPS);    
		simulation.simulate();        	
		time = System.currentTimeMillis() - time;        	
		System.out.println("[INFO] Tiempo de simulacion: " + time);
	}

}