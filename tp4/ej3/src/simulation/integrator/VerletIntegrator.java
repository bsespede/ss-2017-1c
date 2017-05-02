package simulation.integrator;

import java.util.List;

import math.Vector2d;
import simulation.force.Gravity;
import simulation.particle.Particle;

public class VerletIntegrator implements Integrator {

	public void move(Particle particle, List<Particle> particles, double dt) {
		final Vector2d force = Gravity.gravitationalForceBetween(particle, particles);  
		
        final Vector2d prevPosition = particle.getPrevPosition();
        final Vector2d nextPosition = particle.getPosition().scale(2).substract(prevPosition).add(force.scale(Math.pow(dt, 2) / particle.getMass()));
        particle.setPrevPosition(particle.getPosition());
        particle.setPosition(particle.getNextPosition());
        particle.setNextPosition(nextPosition);
        
        final Vector2d velocity = nextPosition.substract(prevPosition).scale(1.0 / (2 * dt));
        particle.setPrevVelocity(particle.getVelocity());
        particle.setVelocity(velocity);
	}

}
