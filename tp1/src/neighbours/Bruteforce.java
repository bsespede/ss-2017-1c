package neighbours;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import particle.Particle;

public class Bruteforce implements Neighbours{
	
	private Set<Particle> particles = new HashSet<Particle>();
	
	public void addParticle(final Particle particle) {

		particles.add(particle);
	}

	public Map<Particle, Set<Particle>> getNeighbours() {
		final Map<Particle, Set<Particle>> neighbourMap = new HashMap<Particle, Set<Particle>>();

		for (Particle particle: particles) {
			final Set<Particle> neighbours = new HashSet<Particle>();
			
			for (Particle particle2: particles) {
				if (!particle.equals(particle2)) {
					if (particle.isNeighbour(particle2)) {
						neighbours.add(particle2);
					}
				}
			}			
			neighbourMap.put(particle, neighbours);
		}
		
		return neighbourMap;
	}
	
	public boolean hasCollision(final Particle newParticle) {
		for (Particle particle: particles) {
			if (particle.collides(newParticle)) {
				System.out.println("old: "+particle.getX() + ", "+ particle.getY()+" r: "+ particle.getRadius());
				System.out.println("new: "+newParticle.getX() + ", "+ newParticle.getY()+" r: "+ newParticle.getRadius());
				System.out.println(particle.equals(newParticle));
				return true;
			}
		}
		return false;
	}
	
}
