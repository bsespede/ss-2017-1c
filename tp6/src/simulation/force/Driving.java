package simulation.force;

import math.Vector2d;
import simulation.particle.Particle;

public class Driving {

	private static final double TAU = 0.5;
	
	public static Vector2d getForce(final Particle particle, final Vector2d desiredPoint) {
		final Vector2d drivingdDirection = desiredPoint.substract(particle.getPosition()).normalize();
		final Vector2d drivingVelocity = drivingdDirection.scale(particle.getDesiredVelocity());
		final Vector2d drivingForce = drivingVelocity.substract(particle.getVelocity()).scale(particle.getMass() / TAU);		
		return drivingForce;
	}

}
