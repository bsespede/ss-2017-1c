package simulation.integrator;

import java.util.List;

import math.Vector2d;
import simulation.force.Gravity;
import simulation.particle.Particle;

public class BeemanIntegrator implements Integrator{
	
	public void move(final Particle particle, final List<Particle> particles, final double dt) {

		final Vector2d force = Gravity.gravitationalForceBetween(particle, particles);
        final Vector2d acceleration = force.scale(10.0 / particle.getMass());  
        
        final Vector2d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt / 2.0));
        final Vector2d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt / 2.0));        
        final Particle previous = new Particle(particle.getName(), prevPosition, prevVelocity, particle.getRadius(), particle.getMass());
        final Vector2d prevAcceleration = Gravity.gravitationalForceBetween(previous, particles).scale(1.0 / particle.getMass());

        final Vector2d newPosition = particle.getPosition().add(particle.getVelocity().scale(dt)).add(acceleration.scale((2.0 / 3.0) * Math.pow(dt, 2)).substract(prevAcceleration.scale((1.0 / 6.0) * Math.pow(dt, 2))));
        particle.setPosition(newPosition);
        
        final Vector2d newAccel = Gravity.gravitationalForceBetween(particle, particles).scale(1.0 / particle.getMass());
        final Vector2d newVelocity = particle.getVelocity().add(newAccel.scale((1.0 / 3.0) * dt).add(newAccel.scale((5.0 / 6.0) * dt).substract(prevAcceleration.scale((1.0 / 6.0) * dt))));
        particle.setVelocity(newVelocity);
    }
	
}
