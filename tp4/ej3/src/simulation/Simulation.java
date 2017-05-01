package simulation;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import io.InputReader;
import io.OutputWriter;
import math.Vector2d;
import simulation.integrator.Integrator;
import simulation.particle.Particle;

public class Simulation {

	private static final double V0 = 7;
	private static final double FLIGHT_SPEED = 3;
	private static final double SPATIAL_STATION_DISTANCE = 1500;

	private final List<Particle> particles = new LinkedList<Particle>();

	private final Integrator integrator;
	private final double interval;
	private final double maxTime;

	public Simulation(final Integrator integrator, final double interval, final double maxTime) {
		this.integrator = integrator;
		this.interval = interval;
		this.maxTime = maxTime;
		generateSystem();
	}

	private void generateSystem() {
		final Particle sun = InputReader.read("../resources/sun.dat");
		final Particle earth = InputReader.read("../resources/earth.dat");
		final Particle mars = InputReader.read("../resources/mars.dat");
		final Particle spaceship = generateSpaceship(earth, sun);

		particles.add(sun);
		particles.add(earth);
		//particles.add(mars);
		//particles.add(spaceship);
	}

	public void simulate() {
		new File("../output").mkdirs();
		try {
			int count = 0;
			for (double time = 0; time < maxTime; time += interval) {			
				if (checkCollisions(time)) {
					break;
				}
				for (Particle particle : particles) {
					integrator.move(particle, particles, interval);
				}
				//if (count++ % 1 == 0) {
					OutputWriter.write("../output/output-" + count++ + ".dat", (double) time, particles);
				//}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Particle generateSpaceship(final Particle earth, final Particle sun) {
		double stationDistanceFromSun = earth.getPosition().distance(sun.getPosition()) + earth.getRadius() + SPATIAL_STATION_DISTANCE;
		Vector2d stationDirection = earth.getPosition().substract(sun.getPosition()).normalize();
		Vector2d spaceshipPosition = stationDirection.scale(stationDistanceFromSun);
		
		Vector2d spaceshipVelocityDirection = new Vector2d(stationDirection.y , -stationDirection.x); 
		Vector2d spaceshipVelocity = spaceshipVelocityDirection.scale(V0 + FLIGHT_SPEED);
		
		return new Particle("SPACESHIP", spaceshipPosition, spaceshipVelocity, 5, 2 * Math.pow(10, 5));
	}

	private boolean checkCollisions(double time) {
		for (int i = 0; i < particles.size(); i++) {
			for (int j = i + 1; j < particles.size(); j++) {
				final Particle p1 = particles.get(i);
				final Particle p2 = particles.get(j);
				double distance = p1.getPosition().distance(p2.getPosition());
				if (distance < p1.getRadius() + p2.getRadius()) {
					System.out.println("[INFO] " + p1.getName() + " colisiono con " + p2.getName() + " en " + time);
					return true;
				}
			}
		}
		return false;
	}

}
