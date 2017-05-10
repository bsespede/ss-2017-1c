package simulation.force;

import java.util.List;

import math.Vector2d;
import simulation.particle.Particle;
import simulation.particle.Walls;

public class Force {

	public static Vector2d getTotalForce(final Particle p1, final List<Particle> particles, final Walls walls) {
		Vector2d totalForce = Gravity.getForce(p1);
		for (Particle p2: particles) {
			if (p1.collides(p2)) {
				totalForce = totalForce.add(Elastic.getForce(p1, p2));
			}
		}
		if (p1.collides(walls)) {
			totalForce = totalForce.add(Elastic.getForce(p1, walls));	
		}
		return totalForce;
	}
	
}
