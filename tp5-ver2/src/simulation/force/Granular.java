package simulation.force;

import math.Vector2d;
import simulation.particle.Particle;
import terrain.Wall;

public class Granular {

	private static final double KN = 120000;
	private static final double KT = 240000;
	
	public static Vector2d getForce(final Particle particle, final Particle otherParticle) {
		if (particle.collides(otherParticle)) {
			final double distance = particle.getPosition().distance(otherParticle.getPosition());
			final double totalRadius = particle.getRadius() + otherParticle.getRadius();
			final Vector2d normalDirection = particle.getPosition().substract(otherParticle.getPosition()).normalize();
			final Vector2d normalForce = normalDirection.scale(KN * (totalRadius - distance));			
			final Vector2d tangentialDirection = new Vector2d(-normalDirection.y, normalDirection.x);
			final double deltaVelocity = otherParticle.getVelocity().substract(particle.getVelocity()).dot(tangentialDirection);
			final Vector2d tangentialForce = tangentialDirection.scale(KT * (totalRadius - distance) * deltaVelocity);			
			return tangentialForce.add(normalForce);
		} else {
			return new Vector2d(0, 0);
		}
	}

	public static Vector2d getForce(final Particle particle, final Wall wall) {
		final Vector2d intersectionPoint = wall.intersection(particle);
		final double distance = intersectionPoint.distance(particle.getPosition());
		if (distance < particle.getRadius()) {
			final Vector2d normalDirection = particle.getPosition().substract(intersectionPoint).normalize();
			final Vector2d normalForce = normalDirection.scale(KN * (particle.getRadius() - distance));		
			final Vector2d tangentialDirection = new Vector2d(-normalDirection.y, normalDirection.x);
			final double deltaVelocity = particle.getVelocity().dot(tangentialDirection);
			final Vector2d tangentialForce = tangentialDirection.scale(-KT * (particle.getRadius() - distance) * deltaVelocity);		
			return normalForce.add(tangentialForce);
		} else {
			return new Vector2d(0, 0);
		}
	}

}
