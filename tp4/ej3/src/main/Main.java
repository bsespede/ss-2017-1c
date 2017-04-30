package main;
import java.io.IOException;

import io.OutputWriter;
import planets.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {

	private final static int MAX_STEPS = 10000;
	
    public static void main(String[] args) {
        try {
        	long time = System.currentTimeMillis();        	
        	final OutputWriter writer = new OutputWriter("solarSystem");
        	final Integrator integrator = new BeemanIntegrator();
        	final Simulation simulation = new Simulation(integrator, 0.1, writer);
        	long timeStart = System.currentTimeMillis();
        	
        	for (int s = 0; s < MAX_STEPS; s++) {
        		if (s % 100 == 0) {
        			long elapsedTime = System.currentTimeMillis() - timeStart;
        			System.out.printf("i=%d, prog= %g\n", s, (double) s / MAX_STEPS);
        		}
        		simulation.simulate(s);
        	}
        
        	time = System.currentTimeMillis() - time;        	
        	System.out.println("[INFO] Tiempo de simulacion: " + time);
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
