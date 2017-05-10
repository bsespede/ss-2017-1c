package simulation;

import java.util.LinkedList;
import java.util.List;

import io.OutputWriter;
import math.Vector2d;
import simulation.force.Gravity;
import simulation.integrator.Integrator;
import simulation.particle.Particle;
import simulation.particle.Walls;

public class Simulation {
	
	private static final double EPSILON = 0.01;

	private final String resultPath;
	private final Integrator integrator;
	private final double dt;
	private final double dt2;
	private final double maxTime;
	private final Walls walls;
	
	private final List<Particle> particles;
	
	public Simulation(final String resultPath, final Integrator integrator, final double dt, final double dt2, final double maxTime, final double L, final double W, final double D, final double maxRandGenTime) {
		this.resultPath = resultPath;
		this.integrator = integrator;
		this.dt = dt;
		this.dt2 = dt2;
		this.maxTime = maxTime;
		this.walls = new Walls(L, W, D);
		this.particles = new LinkedList<Particle>();
		particles.addAll(walls.generateParticles(maxRandGenTime));
		calculatePrevious(particles, dt);
	}

	public Result simulate() {
		for (double time = 0; time < maxTime; time += dt) {
			move(integrator, dt);
			if (resultPath != null &&time % dt2 < EPSILON) {
				generateOutput(time);
			}
		}
		return null;
	}
	
	public void move(final Integrator integrator, final double dt) {
		for (Particle particle : particles) {
			integrator.move(particle, particles, walls, dt);
		}
	}
	
	private void calculatePrevious(List<Particle> particles, double dt) {
		for (Particle particle: particles) {
			calculatePrevious(particle, dt);
		}
	}
	
	private void calculatePrevious(final Particle particle, final double dt) {
		final Vector2d force = Gravity.getForce(particle);
		final Vector2d acceleration = force.scale(1.0 / particle.getMass());

		final Vector2d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt)).add(acceleration.scale(Math.pow(dt, 2) / 2));
		final Vector2d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt));

		particle.setPrevPosition(prevPosition);
		particle.setPrevVelocity(prevVelocity);
	}

	public void generateOutput(double time) {
		OutputWriter.writeParticles(resultPath + "/" + (int) time + ".dat", (double) time, particles);
	}
	
}
