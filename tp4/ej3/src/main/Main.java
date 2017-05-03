package main;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {
	
	private static final double HOUR = 3600;
	private static final double DAY = HOUR * 24;
	private static final double MONTH = DAY * 30;
	private static final double YEAR = DAY * 365;
	
	private final static double MAX_TIME = YEAR * 3;
	private final static double INTERVAL = 1000;
	private final static int INTERVAL_OUTPUT = (int) MONTH;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		for (int i = 0; i < MAX_TIME / MONTH; i++) {
			final Simulation simulation = new Simulation("DistanceMes-" + i, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, 90, i * MONTH);    
			simulation.simulate();   	
		}
		time = System.currentTimeMillis() - time;        	
		System.out.println("[INFO] Tiempo de simulacion: " + time);
	}

}