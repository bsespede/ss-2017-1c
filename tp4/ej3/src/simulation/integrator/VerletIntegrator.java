package simulation.integrator;

import java.util.List;

import math.Vector2d;
import simulation.force.Gravity;
import simulation.particle.Particle;

public class VerletIntegrator implements Integrator {

	public void move(Particle particle, List<Particle> particles, double dt) {
		// TODO Auto-generated method stub

		Vector2d previousPos = particle.getPosition();
		Vector2d previousVel = particle.getVelocity();
		Double mass = particle.getMass();

		Vector2d f = Gravity.gravitationalForceBetween(particle, particles);
		
//		for (Particle particle2 : particles) {
//			if(!particle.equals(particle2))
//				f.add(Gravity.gravitationalForceBetween(particle, particle2).getForce());
//		}

		Vector2d nextPos = previousPos.add(previousVel.scale(dt)).add(f.scale((Math.pow(dt, 2) / mass)));

		particle.setPosition(nextPos);

		Vector2d f_dt = Gravity.gravitationalForceBetween(particle, particles);
//		for (Particle particle2 : particles) {
//			if(!particle.equals(particle2))
//				f_dt.add(Gravity.gravitationalForceBetween(particle, particle2).getForce());
//		}

		Vector2d nextVel = previousVel.add((f_dt.add(f_dt)).scale(dt / (2 * mass)));

		particle.setVelocity(nextVel);
		
		/*
		 * double newPosition; double newVelocity;
		 * 
		 * double force_tmdt; double force_t;
		 * 
		 * force_t = calculator.calculateForce(previousPosition,
		 * previousVelocity); newPosition = previousPosition + dt *
		 * previousVelocity + (Math.pow(dt, 2) / mass) * force_t;
		 * 
		 * force_tmdt = calculator.calculateForce(newPosition,
		 * previousVelocity); newVelocity = previousVelocity + dt / (2 * mass) *
		 * (force_t + force_tmdt);
		 * 
		 * previousPosition = newPosition; previousVelocity = newVelocity;
		 * return newPosition;
		 */

	}

}
