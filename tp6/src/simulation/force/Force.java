package simulation.force;

import java.util.List;
import math.Vector2d;
import simulation.particle.Particle;
import terrain.Walls;

public class Force {

	public static Vector2d getTotalForce(final Particle particle, final List<Particle> particles, final Walls walls) {
		Vector2d totalForce = Driving.getForce(particle);
		for (Particle otherParticle: particles) {
			if (!particle.equals(otherParticle)) {
				totalForce = totalForce.add(Social.getForce(particle, otherParticle));
				if (particle.collides(otherParticle)) {
					totalForce = totalForce.add(Granular.getForce(particle, otherParticle));
				}
			}
		}
		Vector2d wallCollision = walls.intersectionPoint(particle);
		if (wallCollision != null) {
			totalForce = totalForce.add(Granular.getForce(particle, walls));
		}
		return totalForce;
	}
	
}
