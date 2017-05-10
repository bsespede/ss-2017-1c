package simulation.integrator;

import java.util.List;

import simulation.particle.Particle;
import simulation.particle.Walls;

public interface Integrator {

	public void move(final Particle particle, final List<Particle> particles, final Walls walls, final double dt);

}