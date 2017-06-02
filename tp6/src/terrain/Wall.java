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

	public boolean collides(final Particle particle) {
		final Vector2d particlePosition = particle.getPosition();
		final Vector2d intersectionPoint = Collision.distanceLinePoint(particlePosition, p0, p1);
        return intersectionPoint != null && intersectionPoint.distance(particlePosition) <= particle.getRadius();
	}
	
}
