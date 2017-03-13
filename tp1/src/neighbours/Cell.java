package neighbours;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import particle.Particle;

public class Cell {
	
	private final List<Particle> cellParticles = new LinkedList<Particle>();
	
	public void addParticle(final Particle particle) {
		if (hasCollision(particle)) {
			throw new IllegalStateException("Particles collide");
		}
		cellParticles.add(particle);		
	}
	
	public List<Particle> getParticles() {
		return cellParticles;
	}
	
	public boolean hasCollision(final Particle newParticle) {
		for (Particle particle: cellParticles) {
			if (particle.collides(newParticle)) {
				return true;
			}
		}
		return false;
	}

	public Set<Particle> getValidNeighbours(Particle particle) {
		final Set<Particle> validNeighbours = new HashSet<Particle>();
		for (Particle neighbour: cellParticles) {
			if (particle.isNeighbour(neighbour)) {
				validNeighbours.add(neighbour);
			}
		}
		return validNeighbours;
	}

}
