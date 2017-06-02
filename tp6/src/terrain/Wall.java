package terrain;

import math.Collision;
import math.Vector2d;
import simulation.particle.Particle;

public class Wall {

	private final Vector2d p0;
	private final Vector2d p1;
	
	public Wall(final Vector2d p0, final Vector2d p1) {
		this.p0 = p0;
		this.p1 = p1;
	}
	
	public double getMinX() {
		return Math.min(p0.x, p1.x);
	}
	
	public double getMaxX() {
		return Math.max(p0.x, p1.x);
	}
	
	public double getMinY() {
		return Math.min(p0.y, p1.y);
	}
	
	public double getMaxY() {
		return Math.max(p0.y, p1.y);
	}

	public Vector2d intersection(final Particle particle) {
		return Collision.distanceLinePoint(particle.getPosition(), p0, p1);
	}

	@Override
	public String toString() {
		return "Wall [p0=" + p0 + ", p1=" + p1 + "]";
	}
	
}
