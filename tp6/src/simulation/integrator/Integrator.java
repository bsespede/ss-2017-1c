package simulation.integrator;

import java.util.List;

import simulation.particle.Particle;
import terrain.Terrain;

public interface Integrator {

	void move(final Particle particle, final List<Particle> particles, final Terrain terrain, final double dt);

}