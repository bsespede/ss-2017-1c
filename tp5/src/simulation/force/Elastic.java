package simulation.force;

import main.Main;
import math.Vector2d;
import simulation.particle.Particle;
import simulation.silo.Silo;

public class Elastic {

	public static Vector2d getForce(final Particle particle, final Particle otherParticle) {
		double fX = getNf(particle, otherParticle) * getEnx(particle, otherParticle) + getTf(particle, otherParticle) * (-(getEny(particle, otherParticle)));
		double fY = getNf(particle, otherParticle) * getEny(particle, otherParticle) + getTf(particle, otherParticle) * getEnx(particle, otherParticle);
		return new Vector2d(fX, fY);
	}

	public static Vector2d getForce(final Particle particle, final Silo silo) {
		Vector2d intersectionPoint = silo.getIntersectionPoint(particle);
		if (intersectionPoint != null){

		}
		return new Vector2d(0,0);
	}

	private static double getEny(Particle p, Particle particle) {
		return (particle.getPosition().x - p.getPosition().y) / getDist(particle.getPosition().x, particle.getPosition().y, p.getPosition().x, p.getPosition().y);
	}

	private static double getEnx(Particle p, Particle particle) {
		return (particle.getPosition().x - p.getPosition().x) / getDist(particle.getPosition().x, particle.getPosition().y, p.getPosition().x, p.getPosition().y);
	}
	private static double getTf(Particle p, Particle other) {
		return -Main.kt * getEps(p, other) * (((p.getVelocity().x - other.getVelocity().x) * (-getEny(p, other)))
				+ ((p.getVelocity().y - other.getVelocity().y) * (getEnx(p, other))));
	}

	private static double getEps(Particle p, Particle other) {
		return p.getRadius() + other.getRadius() - (getDist(p.getPosition().x, p.getPosition().y, other.getPosition().x, other.getPosition().y));
	}

	private static double getDist(double x0, double y0, double x1, double y1) {
		return Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
	}

	private static double getNf(Particle p, Particle other) {
		return -Main.kn * getEps(p, other);
	}
}
