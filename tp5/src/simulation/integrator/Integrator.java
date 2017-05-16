package simulation.integrator;

import java.util.List;

import simulation.particle.Particle;
import simulation.silo.Silo;

public interface Integrator {

	void move(final Particle particle, List<Particle> particles, final Silo silo, final double dt);

}