package terrain;

import math.Collision;
import math.Vector2d;
import simulation.particle.Particle;

public class Walls {

	private final static double EPSILON = 0.01;
	private final double L;
	private final double W;
	private final double D;	
	
	// El 0,0 esta arriba a la izq
	public Walls(final double L, final double W, final double D) {
		this.L = L;
		this.W = W;
		this.D = D;
	}

	public Vector2d intersectionPoint(final Particle particle) {
		Vector2d particlePosition = particle.getPosition();
		Vector2d linePoint1, linePoint2;
		Vector2d intersectionPoint;

		//checks right intersection
		linePoint1 = new Vector2d(W, 0);
		linePoint2 = new Vector2d(W, L);
        intersectionPoint = Collision.distanceLinePoint(particlePosition, linePoint1, linePoint2);
        if(intersectionPoint != null && intersectionPoint.distance(particlePosition) <= particle.getRadius()) {
        	return intersectionPoint;
        }

		//checks left intersection
		linePoint1 = new Vector2d(0, 0);
		linePoint2 = new Vector2d(0, L);
		intersectionPoint = Collision.distanceLinePoint(particlePosition, linePoint1, linePoint2);
		if(intersectionPoint != null && intersectionPoint.distance(particlePosition) <= particle.getRadius()) {
			return intersectionPoint;
		}

		//checks bottom left intersection
		linePoint1 = new Vector2d(0, L);
		linePoint2 = new Vector2d((W - D) / 2, L);
		intersectionPoint = Collision.distanceLinePoint(particlePosition , linePoint1, linePoint2);
		if(intersectionPoint != null && intersectionPoint.distance(particlePosition) <= particle.getRadius()) {
			return intersectionPoint;
		}

		//checks bottom right intersection
		linePoint1 = new Vector2d((W - D) / 2 + D, L);
		linePoint2 = new Vector2d(W, L);
		intersectionPoint = Collision.distanceLinePoint(particlePosition , linePoint1, linePoint2);
		if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) {
			return intersectionPoint;
		}

		//checks end intersection
//		linePoint1 = new Vector2d(-W, L);
//		linePoint2 = new Vector2d(W, L);
//		intersectionPoint = distanceLinePoint(x, y , -W , L + Main.BOTTOM_DISTANCE,  W * 2, L + Main.BOTTOM_DISTANCE);
//		if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) {
//			return intersectionPoint;
//		}

		return null;
	}	

	public double getWidth() {
		return W;
	}

	public double getHeight() {
		return L;
	}

	public boolean isDoor(final Vector2d position) {
		final double holeFrom = (W - D) / 2;
		final double holeTo = (W - D) / 2 + D;

        return position.x >= holeFrom && position.x <= holeTo && Math.abs(position.y - L) < EPSILON;
	}
	
}
