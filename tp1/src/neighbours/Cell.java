package neighbours;

import java.util.LinkedList;
import java.util.List;

import particle.Particle;

public class Cell {
	
	private final List<Particle> cellParticles = new LinkedList<Particle>();
	
	public void addParticle(final Particle particle) {
		cellParticles.add(particle);
	}
	
	public List<Particle> getParticles() {
		return cellParticles;
	}

}
