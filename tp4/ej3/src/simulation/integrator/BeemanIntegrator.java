package simulation.integrator;

import java.util.List;

import math.Vector3d;
import simulation.force.Gravity;
import simulation.particle.Particle;

public class BeemanIntegrator implements Integrator{
	
	public void move(final Particle particle, final List<Particle> particles, final double dt) {

		final Vector3d force = Gravity.gravitationalForceBetween(particle, particles);
        final Vector3d acceleration = force.scale(10.0 / particle.getMass());  
        
        final Vector3d prevVelocity = particle.getVelocity().substract(acceleration.scale(dt / 2.0));
        final Vector3d prevPosition = particle.getPosition().substract(particle.getVelocity().scale(dt / 2.0));        
        final Particle previous = new Particle(particle.getName(), prevPosition, prevVelocity, particle.getRadius(), particle.getMass());
        final Vector3d prevAcceleration = Gravity.gravitationalForceBetween(previous, particles).scale(1.0 / particle.getMass());

        final Vector3d newPosition = particle.getPosition().add(particle.getVelocity().scale(dt)).add(acceleration.scale((2.0 / 3.0) * Math.pow(dt, 2)).substract(prevAcceleration.scale((1.0 / 6.0) * Math.pow(dt, 2))));
        particle.setPosition(newPosition);
        
        final Vector3d a_tmdt = Gravity.gravitationalForceBetween(particle, particles).scale(1.0 / particle.getMass());
        final Vector3d newVelocity = particle.getVelocity().add(a_tmdt.scale((1.0 / 3.0) * dt).add(a_tmdt.scale((5.0 / 6.0) * dt).substract(prevAcceleration.scale((1.0 / 6.0) * dt))));
        particle.setVelocity(newVelocity);
    }
	
}
