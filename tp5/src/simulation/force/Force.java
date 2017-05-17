package simulation.force;

import java.util.List;
import math.Vector2d;
import simulation.particle.Particle;
import simulation.silo.Silo;

public class Force {

	public static Vector2d getTotalForce(final Particle particle, final List<Particle> particles, final Silo silo) {
		Vector2d totalForce = Gravity.getForce(particle);
		for (Particle otherParticle: particles) {
			if (particle.collides(otherParticle)) {
				totalForce = totalForce.add(Elastic.getForce(particle, otherParticle));
			}
		}
		if (silo.getIntersectionPoint(particle)) {
			totalForce = totalForce.add(Elastic.getForce(particle, silo));
		}
		return totalForce;
	}
	
}
