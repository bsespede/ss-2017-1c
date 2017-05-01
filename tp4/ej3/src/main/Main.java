package main;
import simulation.Simulation;
import simulation.integrator.Integrator;
import simulation.integrator.VerletIntegrator;

public class Main {

	private final static double INTERVAL = 0.5;
	private final static double MAX_STEPS = 1000;
	private final static Integrator INTEGRATOR = new VerletIntegrator();

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		final Simulation simulation = new Simulation(INTEGRATOR, INTERVAL, MAX_STEPS);    
		simulation.simulate();        	
		time = System.currentTimeMillis() - time;        	
		System.out.println("[INFO] Tiempo de simulacion: " + time);
	}

}