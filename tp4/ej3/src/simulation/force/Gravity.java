package simulation.force;

import math.Vector2d;
import simulation.particle.Particle;

public class Gravity {
	
	public final static double G = 6.74 * Math.pow(10, -11);

	public static Force gravitationalForceBetween(final Particle p1, final Particle p2) {		
		final Vector2d r = p2.getPosition().substract(p1.getPosition());
		double module = G * p1.getMass() * p2.getMass() / Math.pow(r.module(), 2);
		return new Force(module, r.normalize());
	}
}
