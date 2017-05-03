package simulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.InputReader;
import io.OutputWriter;
import math.Vector2d;
import simulation.force.Gravity;
import simulation.integrator.Integrator;
import simulation.particle.Particle;

public class Simulation {
	
	private static final double V0 = 7;
	private static final double SPATIAL_STATION_DISTANCE = 1500 * 1000;
	private static final double SPACESHIP_RADIUS = 50;
	
	private static final double HOUR = 3600;
	private static final double DAY = HOUR * 24;
	private static final double WEEK = DAY * 7;
	private static final double MONTH = DAY * 30;
	private static final double YEAR = DAY * 365;

	private final String name;
	private final Integrator integrator;
	private final double dt;
	private final double dt2;
	private final double maxTime;
	private double angle;
	private double launchTime;
	
	private List<Particle> particles;
	private Particle earth;
	private Particle sun;
	private Particle mars;
	private Particle spaceship;
	
	private final Map<Double, Double> spaceshipMarsDistance;

	public Simulation(final String name, final Integrator integrator, final double dt, final double dt2, final double maxTime) {
		this.name = name;
		this.integrator = integrator;
		this.dt = dt;
		this.dt2 = dt2;
		this.maxTime = maxTime;
		this.sun = InputReader.read("../resources/sun.dat");
		this.earth = InputReader.read("../resources/earth.dat");
		this.mars = InputReader.read("../resources/mars.dat");
		this.particles = new LinkedList<Particle>();
		particles.add(earth);
		particles.add(sun);
		particles.add(mars);
		calculatePrevious(particles, dt);	
		this.spaceshipMarsDistance = new HashMap<Double, Double>();
		this.angle = 180;
		this.launchTime = MONTH;
	}
	
	public Simulation(final String name, final Integrator integrator, final double dt, final double dt2, final double maxTime, final double angle, final double launchTime) {
		this(name, integrator, dt, dt2, maxTime);
		this.angle = angle;
		this.launchTime = launchTime;
	}

	private void calculatePrevious(List<Particle> particles, double dt) {
		for (Particle particle: particles) {
			calculatePrevious(particle, dt);
		}
	}

	public void simulate() {
		for (double time = 0; time < maxTime; time += dt) {
			if (checkCollisions(time)) {
				break;
			}
			if (shouldLaunchSpaceship(time)) {
				launchSpaceship(dt);
			}
			move(integrator, dt);
			if (time % dt2 == 0) {
				generateOutput(time);
			}
		}
		generateDistanceOutput();
	}
	
	private void generateDistanceOutput() {
		try {
			OutputWriter.writeDistances("../" + name + ".dat", spaceshipMarsDistance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void calculatePrevious(Particle particle, double dt) {
		final Vector2d force = Gravity.gravitationalForceBetween(particle, particles);
		final Vector2d acceleration = force.scale(1.0 / particle.getMass());

		final Vector2d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt)).add(acceleration.scale(Math.pow(dt, 2) / 2));
		final Vector2d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt));

		particle.setPrevPosition(prevPosition);
		particle.setPrevVelocity(prevVelocity);
	}

	public void move(Integrator integrator, double dt) {
		for (Particle particle : particles) {
			integrator.move(particle, particles, dt);
		}
	}

	public boolean checkCollisions(double time) {
		for (int i = 0; i < particles.size(); i++) {
			for (int j = i + 1; j < particles.size(); j++) {
				final Particle p1 = particles.get(i);
				final Particle p2 = particles.get(j);
				double distance = p1.getPosition().distance(p2.getPosition());
				if (time % dt2 == 0 && (p1.equals(spaceship) && p2.equals(mars) )|| (p1.equals(mars) && p2.equals(spaceship))) {
					spaceshipMarsDistance.put(time / YEAR, distance);
				}
				if (distance < p1.getRadius() + p2.getRadius() + 1500) {
					System.out.println("[INFO] " + p1.getName() + " colisiono con " + p2.getName() + " en " + time);
					return true;
				}
			}
		}
		return false;
	}

	public double spaceshipMarsDistance() {
		return spaceship.getPosition().distance(mars.getPosition());
	}
	
	public void launchSpaceship(double dt) {
		Vector2d stationDirection = earth.getPosition().substract(sun.getPosition());
		double launchAngle = Math.atan2(stationDirection.x, stationDirection.y) + Math.toRadians(angle);

		double distanceFromEarth = earth.getRadius() + SPATIAL_STATION_DISTANCE + SPACESHIP_RADIUS;
		Vector2d adjustAngle = new Vector2d(Math.cos(launchAngle), Math.sin(launchAngle)).scale(distanceFromEarth);
		Vector2d spaceshipPosition = earth.getPosition().add(adjustAngle);

		Vector2d velocityAdjust = new Vector2d(Math.cos((Math.PI / 2) + launchAngle), Math.sin((Math.PI / 2) + launchAngle)).scale(V0 * 1000);				
		Vector2d spaceshipVelocity = earth.getVelocity().add(velocityAdjust);

		Particle spaceship = new Particle("SPACESHIP", spaceshipPosition, spaceshipVelocity, SPACESHIP_RADIUS, 2 * Math.pow(10, 5));
		calculatePrevious(spaceship, dt);

		this.spaceship = spaceship;
		particles.add(spaceship);
	}

	public boolean shouldLaunchSpaceship(double time) {
		return !hasLaunchedSpaceship() && time >= launchTime;
	}

	public boolean hasLaunchedSpaceship() {
		return spaceship != null;
	}
	
	public void generateOutput(double time) {
		try {
			Files.createDirectories(Paths.get("../output/"));
			OutputWriter.writeParticles("../output/" + name + "-" + time + ".dat", (double) time, particles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
