package simulation;

import java.util.LinkedList;
import java.util.List;

import io.InputReader;
import io.OutputWriter;
import math.Vector2d;
import simulation.bodies.Body;
import simulation.force.Gravity;
import simulation.integrator.Integrator;
import simulation.particle.Particle;

public class Simulation {
	
	private static final double AREOSTATIC_ORBIT = 17031;
	private static final double SPACESHIP_ORBITAL_VELOCITY = 7.12;
	private static final double SPATIAL_STATION_DISTANCE = 1500;
	private static final double SPACESHIP_RADIUS = 0.5;
	private static final double EPSILON = 0.01;
	private static final double DAY = 3600 * 24;

	private final String resultPath;
	private final Integrator integrator;
	private final double dt;
	private final double dt2;
	private final double maxTime;
	private double V0;
	private double angle;
	private double launchTime;
	private double maxFlightTime;
	
	private List<Particle> particles;
	private Particle earth;
	private Particle sun;
	private Particle mars;
	private Particle spaceship;
	private double minDistance;
	private double minDistanceTime;
	
	public Simulation(final String resultPath, final Integrator integrator, final double dt, final double dt2, final double maxTime, final double maxFlightTime, final double angle, final double launchTime, final double V0) {
		this.resultPath = resultPath;
		this.integrator = integrator;
		this.dt = dt;
		this.dt2 = dt2;
		this.maxTime = maxTime;
		this.angle = angle;
		this.launchTime = launchTime;
		this.maxFlightTime = maxFlightTime;
		this.V0 = V0;
		this.minDistance = Double.MAX_VALUE;
		this.minDistanceTime = Double.MAX_VALUE;
		this.particles = new LinkedList<Particle>();
		this.sun = InputReader.read("../resources/sun.dat");
		this.earth = InputReader.read("../resources/earth.dat");
		this.mars = InputReader.read("../resources/mars.dat");
		particles.add(earth);
		particles.add(sun);
		particles.add(mars);
		calculatePrevious(particles, dt);
	}

	public Result simulate() {
		for (double time = 0; time < maxTime + maxFlightTime; time += dt) {
			final Collision collision = checkCollisions(time);
			if (collision.hasCollided()) {
				return getCurrentResult();				
			}
			if (shouldLaunchSpaceship(time)) {
				launchSpaceship(dt);
			}
			move(integrator, dt);
			if (hasLaunchedSpaceship()) {
				if (time % dt2 < EPSILON) {
					generateOutput(time);
				}
				double marsDistance = spaceship.distance(mars);
				if (marsDistance < minDistance) {
					minDistance = marsDistance;
					minDistanceTime = time;
				}
				if (time - launchTime > maxFlightTime) {
					return getCurrentResult();
				}
			}
		}
		return getCurrentResult();
	}

	public Result getCurrentResult() {
		return new Result(minDistance < AREOSTATIC_ORBIT, launchTime / DAY, spaceship.getVelocity().module() - mars.getVelocity().module(), minDistance, (minDistanceTime - launchTime) / DAY);
	}
	
	private void calculatePrevious(Particle particle, double dt) {
		final Vector2d force = Gravity.gravitationalForceBetween(particle, particles);
		final Vector2d acceleration = force.scale(1.0 / particle.getMass());

		final Vector2d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt)).add(acceleration.scale(Math.pow(dt, 2) / 2));
		final Vector2d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt));

		particle.setPrevPosition(prevPosition);
		particle.setPrevVelocity(prevVelocity);
	}

	private void calculatePrevious(List<Particle> particles, double dt) {
		for (Particle particle: particles) {
			calculatePrevious(particle, dt);
		}
	}

	public void move(Integrator integrator, double dt) {
		for (Particle particle : particles) {
			integrator.move(particle, particles, dt);
		}
	}

	public Collision checkCollisions(double time) {
		for (int i = 0; i < particles.size(); i++) {
			for (int j = i + 1; j < particles.size(); j++) {
				final Particle p1 = particles.get(i);
				final Particle p2 = particles.get(j);
				double distance = p1.distance(p2);
				if (distance < 0 ) {
					new Collision(p1, p2);
				}
			}
		}
		return new Collision(null, null);
	}
	
	public void launchSpaceship(double dt) {
		final Particle from = earth;
		
		Vector2d stationDirection = from.getPosition().substract(sun.getPosition()).normalize();
		double distanceFromEarth = from.getRadius() + SPATIAL_STATION_DISTANCE + SPACESHIP_RADIUS;
		Vector2d fromEarthToParticle = stationDirection.scale(distanceFromEarth);		
		Vector2d spaceshipPosition = from.getPosition().add(fromEarthToParticle);
		
		Vector2d orbitVelocity = stationDirection.scale(SPACESHIP_ORBITAL_VELOCITY).rotateCounterClockwise(90);
		Vector2d spaceshipVelocity = stationDirection.scale(V0).rotateCounterClockwise(angle);
		spaceshipVelocity = earth.getVelocity().add(spaceshipVelocity).add(orbitVelocity);
		
		Particle spaceship = new Particle(Body.SPACESHIP, spaceshipPosition, spaceshipVelocity, SPACESHIP_RADIUS, 2 * Math.pow(10, 5));
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
		OutputWriter.writeParticles(resultPath + "/" + (int) time + ".dat", (double) time, particles);
	}
	
	private class Collision {	
		
		private final Particle p1;
		private final Particle p2;
		
		public Collision(final Particle p1, final Particle p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		public boolean hasCollided() {
			return p1 != null && p2 != null;
		}
		
	}
	
}
