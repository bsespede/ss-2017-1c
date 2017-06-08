package simulation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.OutputWriter;
import math.Vector2d;
import simulation.force.Force;
import simulation.integrator.Integrator;
import simulation.particle.Particle;
import terrain.Terrain;

public class Simulation {
	
	private static final double STATS_WINDOW = 5;
	private static final double EPSILON = 0.001;
	private static final double L = 20;
	private static final double W = 20;
	private static final double D = 1.2;
	private static final double MIN_DIAMETER = 0.5;
	private static final double MAX_DIAMETER = 0.58;
	private static final double MASS = 80;
	
	private final OutputWriter writer;
	private final Terrain terrain;
	private final List<Particle> particles;
	private final Integrator integrator;
	private final int particlesNumber;
	private final double dt;
	private final double dt2;
	private final double drivingSpeed;
	
	private final Map<Double, Integer> discharges = new LinkedHashMap<>();
	private final Map<Double, Double> flow = new LinkedHashMap<>();
	private final Map<Double, Double> meanFlow = new LinkedHashMap<>();
	private final Map<Double, Double> kineticEnergy = new LinkedHashMap<>();
	private final Map<Double, Double> pressure = new LinkedHashMap<>();
	private int currentDischarges = 0;
	private int currentWindowDischarges = 0;
	private double evacuationTime = 0;
	
	public Simulation(final int runId, final Integrator integrator, final double dt, final double dt2, final int N, final double drivingSpeed) {
		this.writer = new OutputWriter("../"+ runId +"-result.xyz");
		this.integrator = integrator;
		this.dt = dt;
		this.dt2 = dt2;
		this.drivingSpeed = drivingSpeed;
		this.terrain = new Terrain(L, W, D);
		this.particles = generateParticles(L, W, N);
		this.particlesNumber = particles.size();
		calculatePrevious(particles, dt);
	}

	private List<Particle> generateParticles(double L, double W, int N) {
		List<Particle> particles = new ArrayList<Particle>(N);
		while (particles.size() < N) {
			final double desiredVelocity = Math.random() * 0.2 * (drivingSpeed) + drivingSpeed * 0.9;
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
		double time = 0;
		while (particles.size() > 0) {
			move(integrator, dt);
			final List<Particle> particlesToRemove = new LinkedList<Particle>();
			for (Particle particle: particles) {
				if (terrain.justCrossedDoor(particle)) {
					currentWindowDischarges++;
					currentDischarges++;
					discharges.put(time, currentDischarges);
				}
				if (terrain.escapedRoom(particle)) {
					particlesToRemove.add(particle);					
				}
			}
			particles.removeAll(particlesToRemove);
			if (currentDischarges == particlesNumber) {
				evacuationTime = time;
			}
			if (time % STATS_WINDOW < EPSILON) {
				double totalEnergy = 0, totalPressure = 0;
				for (Particle particle: particles) {
					totalEnergy += particle.getKineticEnergy();
					totalPressure += Force.getTotalForce(particle, particles, terrain).module() / (Math.PI * particle.getRadius());
				}
				pressure.put(time, totalPressure);
				kineticEnergy.put(time, totalEnergy / particles.size());
				if (time > 0) {
					meanFlow.put(time, currentDischarges / time);
					flow.put(time, currentWindowDischarges / STATS_WINDOW);
					currentWindowDischarges = 0;
				}
			}
			if (time % dt2 < EPSILON) {
				System.out.println(String.format("[INFO] Time: %.2f Particles-left: %.2f%s", time, particles.size() * 100.0 / particlesNumber, "%"));
				generateParticlesOutput();
			}
			time += dt;
		}		
		System.out.println(String.format("[INFO] Simulation ended at %.2f seconds", time));
		writer.close();
		return new Result(discharges, meanFlow, evacuationTime, kineticEnergy, flow, pressure);
	}
	
	public double simulationOnlyEvacuation() {
		double time = 0;
		while (particles.size() > 0) {
			move(integrator, dt);
			final List<Particle> particlesToRemove = new LinkedList<Particle>();
			for (Particle particle: particles) {
				if (terrain.justCrossedDoor(particle)) {
					currentDischarges++;
				}
				if (terrain.escapedRoom(particle)) {
					particlesToRemove.add(particle);			
				}
			}
			particles.removeAll(particlesToRemove);
			if (currentDischarges == particlesNumber) {
				evacuationTime = time;
			}
			time += dt;
		}
		System.out.println(String.format("[INFO] Simulation ended at %.2f seconds", time));
		writer.close();
		return evacuationTime;
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
	
	public void generateParticlesOutput() {
		writer.writeOutput(particles, terrain);
	}
	
}
