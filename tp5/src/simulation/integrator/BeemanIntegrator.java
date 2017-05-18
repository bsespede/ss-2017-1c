package simulation.integrator;

import java.util.List;

import math.Vector2d;
import simulation.force.Force;
import simulation.particle.Particle;
import simulation.silo.Silo;

public class BeemanIntegrator implements Integrator {

	public void move(final Particle particle, final List<Particle> particles, final Silo silo, final double dt) {

		final Vector2d totalForce = Force.getTotalForce(particle, particles, silo);
        final Vector2d acceleration = totalForce.scale(1.0 / particle.getMass());

        final Particle previous = new Particle(particle.getPrevPosition(), particle.getPrevVelocity(), particle.getRadius(), particle.getMass(), 0);
        final Vector2d prevAcceleration = Force.getTotalForce(previous, particles, silo).scale(1.0 / particle.getMass());

        final Vector2d nextPosition = particle.getPosition().add(particle.getVelocity().scale(dt)).add(acceleration.scale((2.0 / 3.0) * Math.pow(dt, 2))).substract(prevAcceleration.scale((1.0 / 6.0) * Math.pow(dt, 2)));
        particle.setPrevPosition(particle.getPosition());
        particle.setPosition(nextPosition);

        final Vector2d nextAcceleration = Force.getTotalForce(particle, particles, silo).scale(1.0 / particle.getMass());
        final Vector2d nextVelocity = particle.getVelocity().add(nextAcceleration.scale((1.0 / 3.0) * dt)).add(acceleration.scale((5.0 / 6.0) * dt)).substract(prevAcceleration.scale((1.0 / 6.0) * dt));
        particle.setPrevVelocity(particle.getVelocity());
        particle.setVelocity(nextVelocity);
    }

}
