package simulation;

import java.util.ArrayList;
import java.util.List;

import io.OutputWriter;
import math.Vector2d;
import simulation.force.Force;
import simulation.integrator.Integrator;
import simulation.particle.Particle;
import terrain.Terrain;

public class Simulation {
	
	private static final double EPSILON = 0.00001;
	private static final double L = 20;
	private static final double W = 20;
	private static final double D = 1.2;
	private static final double DRIVING_SPEED = 1;
	private static final double MIN_DIAMETER = 0.5;
	private static final double MAX_DIAMETER = 0.58;
	private static final double MASS = 80;
	
	private final Terrain terrain;
	private final List<Particle> particles;
	private final String resultPath;
	private final Integrator integrator;
	private final double dt;
	private final double dt2;
	private final double maxTime;
	
	public Simulation(final String resultPath, final Integrator integrator, final double dt, final double dt2, final double maxTime, final int N) {
		this.resultPath = resultPath;
		this.integrator = integrator;
		this.dt = dt;
		this.dt2 = dt2;
		this.maxTime = maxTime;
		this.terrain = new Terrain(L, W, D);
		this.particles = generateParticles(L, W, N);
		calculatePrevious(particles, dt);
	}

	private List<Particle> generateParticles(double L, double W, int N) {
		List<Particle> particles = new ArrayList<Particle>(N);
		while (particles.size() < N) {
			final double desiredVelocity = Math.random() * 0.2 * (DRIVING_SPEED) + DRIVING_SPEED * 0.9;
			final double particleRadius = (Math.random() * (MAX_DIAMETER - MIN_DIAMETER) + MIN_DIAMETER) / 2;
			final double particleX = Math.random() * (W - 2 * particleRadius) + particleRadius;
			final double particleY = Math.random() * (L - 2 * particleRadius) + particleRadius;
			final Vector2d position = new Vector2d(particleX, particleY);
			final Particle newParticle = new Particle(particles.size(), position, new Vector2d(0, 0), desiredVelocity, MASS, particleRadius);
			
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
		int frame = 0;
		for (double time = 0; time < maxTime; time += dt) {
			move(integrator, dt);
			
			if (time % dt2 < EPSILON) {
				System.out.println(time * 100 / maxTime + "%");
				generateParticlesOutput(frame++);
			}
		}
		return null;
	}

	public void move(final Integrator integrator, final double dt) {
		for (Particle particle : particles) {
			integrator.move(particle, particles, terrain, dt);
		}
	}
	
	private void calculatePrevious(List<Particle> particles, double dt) {
		for (Particle particle: particles) {
			calculatePrevious(particle, dt);
		}
	}
	
	private void calculatePrevious(final Particle particle, final double dt) {
		final Vector2d force = Force.getTotalForce(particle, particles, terrain);
		final Vector2d acceleration = force.scale(1.0 / particle.getMass());

		final Vector2d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt)).add(acceleration.scale(Math.pow(dt, 2) / 2));
		final Vector2d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt));

		particle.setPrevPosition(prevPosition);
		particle.setPrevVelocity(prevVelocity);
	}
	
	public void generateParticlesOutput(double time) {
		OutputWriter.writeParticles(resultPath + "/" + (int) time + ".dat", particles, terrain);
	}
	
}
