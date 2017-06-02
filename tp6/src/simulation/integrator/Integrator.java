package simulation.integrator;

import java.util.List;

import simulation.particle.Particle;
import terrain.Walls;

public interface Integrator {

	void move(final Particle particle, List<Particle> particles, final Walls silo, final double dt);

}