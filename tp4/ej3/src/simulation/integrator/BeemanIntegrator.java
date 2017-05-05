package simulation.integrator;

import java.util.List;

import math.Vector2d;
import simulation.force.Gravity;
import simulation.particle.Particle;

public class BeemanIntegrator implements Integrator{
	
	public void move(final Particle particle, final List<Particle> particles, final double dt) {
		final Vector2d force = Gravity.gravitationalForceBetween(particle, particles);
        final Vector2d acceleration = force.scale(1.0 / particle.getMass());
        
        final Particle previous = new Particle(particle.getBody(), particle.getPrevPosition(), particle.getPrevVelocity(), particle.getRadius(), particle.getMass());
        final Vector2d prevAcceleration = Gravity.gravitationalForceBetween(previous, particles).scale(1.0 / particle.getMass());

        final Vector2d nextPosition = particle.getPosition().add(particle.getVelocity().scale(dt)).add(acceleration.scale((2.0 / 3.0) * Math.pow(dt, 2))).substract(prevAcceleration.scale((1.0 / 6.0) * Math.pow(dt, 2)));
        particle.setPrevPosition(particle.getPosition());
        particle.setPosition(nextPosition);
        
        final Vector2d nextAcceleration = Gravity.gravitationalForceBetween(particle, particles).scale(1.0 / particle.getMass());
        final Vector2d nextVelocity = particle.getVelocity().add(nextAcceleration.scale((1.0 / 3.0) * dt)).add(acceleration.scale((5.0 / 6.0) * dt)).substract(prevAcceleration.scale((1.0 / 6.0) * dt));
        particle.setPrevVelocity(particle.getVelocity());
        particle.setVelocity(nextVelocity);
    }
	
}
