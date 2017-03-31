package simulation.method;

import java.util.Set;

import particle.Particle;
import simulation.Simulation;

public class Bruteforce extends Simulation{
		
	public Bruteforce(final String staticInput, final String dynamicInput, final boolean contour) {
		super(staticInput, dynamicInput, contour);
	}
	
	public Bruteforce(final int L, final int particlesNumber, final boolean contour, final double radius, final double  interactionRadius) {
		super(L, particlesNumber, contour, radius, interactionRadius);
	}

	@Override
	protected Set<Particle> getNeighbourCandidates(Particle particle) {
		return particles;
	}
	
}
