package simulation.force;
import math.Vector2d;
import simulation.particle.Collisionable;

public class Gravity {
	
	public final static double G = 9.81;

	public static Vector2d getForce(final Collisionable body) {
		return new Vector2d(0, G * body.getMass());
	}

}