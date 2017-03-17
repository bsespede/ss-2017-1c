package neighbours;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import particle.Particle;

public class Bruteforce implements Neighbours{
	
	private Set<Particle> particles = new HashSet<Particle>();
	
	public void addParticle(final Particle particle) {
		if (!hasCollision(particle)) {
			particles.add(particle);
		}
	}

	public Map<Particle, Set<Particle>> getNeighbours() {
		final Map<Particle, Set<Particle>> neighbourMap = new HashMap<Particle, Set<Particle>>();

		for (Particle particle: particles) {
			final Set<Particle> neighbours = new HashSet<Particle>();
			
			for (Particle particle2: particles) {
				if (particle.isNeighbour(particle2) && !particle.equals(particle2)) {
					neighbours.add(particle2);
				}
			}			
			neighbourMap.put(particle, neighbours);
		}
		
		return neighbourMap;
	}
	
	public boolean hasCollision(final Particle newParticle) {
		for (Particle particle: particles) {
			if (particle.collides(newParticle)) {
				return true;
			}
		}
		return false;
	}
	
}
