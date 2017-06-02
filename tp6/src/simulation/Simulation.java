package simulation;

import java.util.ArrayList;
import java.util.List;

import io.OutputWriter;
import math.Vector2d;
import simulation.force.Force;
import simulation.integrator.Integrator;
import simulation.particle.Particle;
import terrain.Walls;

public class Simulation {
	
	private static final double EPSILON = 0.0001;
	
	private static final double MIN_DIAMETER = 0.5;
	private static final double MAX_DIAMETER = 0.7;
	private static final double MASS = 80;

	private final List<Particle> particles;
	private final String resultPath;
	private final Integrator integrator;
	private final double dt;
	private final double dt2;
	private final double maxTime;
	private final Walls walls;
	
	public Simulation(final String resultPath, final Integrator integrator, final double dt, final double dt2, final double maxTime, final double L, final double W, final double D, final int N) {
		this.resultPath = resultPath;
		this.integrator = integrator;
		this.dt = dt;
		this.dt2 = dt2;
		this.maxTime = maxTime;
		this.walls = new Walls(L, W, D);
		this.particles = generateParticles(L, W, N);
		calculatePrevious(particles, dt);
	}

	private List<Particle> generateParticles(double L, double W, int N) {
		List<Particle> particles = new ArrayList<Particle>(N);
		while (particles.size() < N) {
			final double particleRadius = (Math.random() * (MAX_DIAMETER - MIN_DIAMETER) + MIN_DIAMETER) / 2;
			final double particleX = Math.random() * (W - 2 * particleRadius) + particleRadius;
			final double particleY = Math.random() * (L - 2 * particleRadius) + particleRadius;
			final Vector2d position = new Vector2d(particleX, particleY);
			final Particle newParticle = new Particle(position, null, particleRadius, MASS, particles.size());
			
			boolean collidedAnotherParticle = false;
			for (Particle particle: particles) {
				if (particle.collides(newParticle)) {
					collidedAnotherParticle = true;
					break;
				}
			}
			
			if (collidedAnotherParticle) {
				continue;
			} else {
				particles.add(newParticle);
			}
		}
		return particles;
	}

	public Result simulate() {
		for (double time = 0; time < maxTime; time += dt) {
			move(integrator, dt);
			if (time % dt2 < EPSILON) {
				generateParticlesOutput(time);
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
		final Vector2d force = Force.getTotalForce(particle, particles, walls);
		final Vector2d acceleration = force.scale(1.0 / particle.getMass());

		final Vector2d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt)).add(acceleration.scale(Math.pow(dt, 2) / 2));
		final Vector2d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt));

		particle.setPrevPosition(prevPosition);
		particle.setPrevVelocity(prevVelocity);
	}

	public void generateParticlesOutput(double time) {
		OutputWriter.writeParticles(resultPath + "/" + (int) time + ".dat", particles, walls);
	}
	
}
