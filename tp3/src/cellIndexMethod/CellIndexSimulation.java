package cellIndexMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import general.Particle;

public abstract class CellIndexSimulation {

	protected final int L;
	protected final Set<Particle> particles;


	public CellIndexSimulation(final int L, Set<Particle> particles) {
		this.L = L;
		this.particles = particles;
	}

	public Map<Particle, Set<Particle>> getNeighbours() {
		final Map<Particle, Set<Particle>> neighbourMap = new HashMap<Particle, Set<Particle>>();

		for (Particle particle: particles) {
			final Set<Particle> particleNeighbours = new HashSet<Particle>();
			
			for (Particle candidateNeighbour: getNeighbourCandidates(particle)) {
				if (!particle.equals(candidateNeighbour)) {
					double distance = particle.calculateDistance(candidateNeighbour);
					if (distance < particle.getInteractionRadius() + particle.getRadius() + candidateNeighbour.getRadius()) {
						particleNeighbours.add(candidateNeighbour);
					}
				}				
			}			
			neighbourMap.put(particle, particleNeighbours);
		}
		return neighbourMap;
	}

	protected abstract Set<Particle> getNeighbourCandidates(final Particle particle);

}
