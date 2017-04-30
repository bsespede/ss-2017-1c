package simulation.integrator;

import java.util.List;

import simulation.particle.Particle;

public interface Integrator {

	public void move(final Particle particle, final List<Particle> particles, final Particle sun, final double dt);

}