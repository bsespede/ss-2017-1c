package simulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.FileProcessor;
import particle.Particle;

public abstract class Simulation {

	protected final boolean contour;
	protected final int L;
	protected final List<Particle> particles;

	public Simulation(final String staticInput, final String dynamicInput, final boolean contour) {
		this.contour = contour;
		this.L = FileProcessor.readL(staticInput);
		this.particles = FileProcessor.readParticles(staticInput, dynamicInput);
	}

	public Simulation(final int L, final int particlesNumber, final boolean contour, final double radius, final double  interactionRadius) {
		this.contour = contour;
		this.L = L;
		this.particles = generateParticles(L, particlesNumber, radius, interactionRadius);
	}

	private List<Particle> generateParticles(final int L, final int particlesNumber, final double radius, final double  interactionRadius) {
		List<Particle> generatedParticles = new LinkedList<Particle>();
		double particlesDistance = L / Math.sqrt(particlesNumber);
		int id = 1;
		for (double x = 0; x < L ; x += particlesDistance) {
			for (double y = 0; y < L ; y += particlesDistance) {
				generatedParticles.add(new Particle(id, x, y, radius, interactionRadius));
				id++;
			}
		}
		return generatedParticles;
	}

	public Map<Particle, Set<Particle>> getNeighbours() {
		final Map<Particle, Set<Particle>> neighbourMap = new HashMap<Particle, Set<Particle>>();

		for (Particle particle: particles) {
			final Set<Particle> particleNeighbours = new HashSet<Particle>();
			
			for (Particle candidateNeighbour: getNeighbourCandidates(particle)) {
				if (!particle.equals(candidateNeighbour)) {
					double distance = (contour)? particle.calculateContourDistance(candidateNeighbour, L) : particle.calculateDistance(candidateNeighbour);
					if (distance < particle.getInteractionRadius() + particle.getRadius() + candidateNeighbour.getRadius()) {
						particleNeighbours.add(candidateNeighbour);
					}
				}				
			}			
			neighbourMap.put(particle, particleNeighbours);
		}
		return neighbourMap;
	}

	protected abstract List<Particle> getNeighbourCandidates(final Particle particle);

}
