package terrain;

import java.util.ArrayList;
import java.util.List;

import math.Collision;
import math.Vector2d;
import simulation.particle.Particle;

public class Terrain {

	private final double L;
	private final double W;
	private final double D;	
	private final List<Wall> walls;
	private final Vector2d escapePoint;
	
	// El 0,0 esta arriba a la izq
	public Terrain(final double L, final double W, final double D) {
		this.L = L;
		this.W = W;
		this.D = D;
		this.escapePoint = new Vector2d(W / 2, 2 * L);
		this.walls = generateWalls();		
	}
	
	private List<Wall> generateWalls() {
		final List<Wall> walls = new ArrayList<Wall>(5);		
		walls.add(new Wall(new Vector2d(0, 0), new Vector2d(W, 0))); // upper wall
		walls.add(new Wall(new Vector2d(0, 0), new Vector2d(0, L))); // left wall
		walls.add(new Wall(new Vector2d(W, 0), new Vector2d(W, L))); // right wall
		walls.add(new Wall(new Vector2d(0, L), new Vector2d((W - D) / 2, L))); // bottom left
		walls.add(new Wall(new Vector2d((W - D) / 2 + D, L), new Vector2d(W, L))); // bottom right		
		return walls;
	}	

	public double getWidth() {
		return W;
	}

	public double getHeight() {
		return L;
	}

	public Vector2d getEscapePoint() {
		return escapePoint;
	}
	
	public List<Wall> getWalls() {
		return walls;
	}
	
	public boolean crossedDoor(final Particle particle) {
		return particle.getPosition().y + particle.getRadius() > L;
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
		if(intersectionPoint != null && intersectionPoint.distance(particlePosition) <= particle.getRadius()) {
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
	
}
