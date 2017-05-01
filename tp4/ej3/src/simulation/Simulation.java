package simulation;

import java.util.LinkedList;
import java.util.List;

import io.InputReader;
import simulation.integrator.Integrator;
import simulation.particle.Particle;

public class Simulation {

	private static final double G = 6.693*Math.pow(10.0,-11.0);
    private static final double HOUR = 3600;
    private static final double DAY = 24 * HOUR;
    private static final double YEAR = 365 * DAY;
	
	private final List<Particle> particles = new LinkedList<Particle>();
	
	private final Integrator integrator;
	private final double interval;
	private final double maxTime;
	
	private double time = 0;
	private long count = 0;

	public Simulation(final Integrator integrator, final int interval, final int maxTime) {
		this.integrator = integrator;
		this.interval = interval;
		this.maxTime = maxTime;
		generateSystem();
	}

	private void generateSystem() {
		final Particle sun = InputReader.read("./resources/sun.dat");
		final Particle earth = InputReader.read("./resources/earth.dat");
		final Particle mars = InputReader.read("./resources/mars.dat");
		final Particle spaceship = generateSpaceship(earth, sun);
		
		particles.add(sun);
		particles.add(earth);
		particles.add(mars);
		particles.add(spaceship);
	}

	public void simulate() {
		for (int time = 0; time < maxTime; time += interval) {
			
			if (checkCollisions())
				break;
			}
		
			for (Particle particle : particles) {
				integrator.move(particle, particles, interval);
			}
		}
	
	private Particle generateSpaceship(Particle earth, Particle sun) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkCollisions() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
