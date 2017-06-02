package simulation.force;

import math.Vector2d;
import simulation.particle.Particle;
import terrain.Wall;

public class Social {

	private static final double A = 2000;
	private static final double B = 0.08;
	
	public static Vector2d getForce(final Particle particle, final Particle otherParticle) {
		final double distance = particle.getPosition().distance(otherParticle.getPosition());
		final double totalRadius = particle.getRadius() + otherParticle.getRadius();
		final Vector2d direction = particle.getPosition().substract(otherParticle.getPosition()).normalize();
		return direction.scale(A * Math.exp((totalRadius - distance) / B));
	}

	public static Vector2d getForce(final Particle particle, final Wall wall) {
		final Vector2d intersectionPoint = wall.intersection(particle);
		final double distance = intersectionPoint.distance(particle.getPosition());
		final Vector2d direction = particle.getPosition().substract(intersectionPoint).normalize();
		return direction.scale(A * Math.exp((particle.getRadius() - distance) / B));
	}

}
