package neighbours;

import java.util.Map;
import java.util.Set;

import particle.Particle;

public interface Neighbours {
	
	public Map<Particle, Set<Particle>> getNeighbours();

}
