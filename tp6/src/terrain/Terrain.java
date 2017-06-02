package terrain;

import java.util.ArrayList;
import java.util.List;

import math.Collision;
import math.Vector2d;
import simulation.particle.Particle;

public class Terrain {

	private final double EPSILON = 0.001;
	private final double L;
	private final double W;
	private final double D;	
	private final List<Wall> walls;
	private final Vector2d doorLeft, doorRight, escapePoint;
	
	// El 0,0 esta arriba a la izq
	public Terrain(final double L, final double W, final double D) {
		this.L = L;
		this.W = W;
		this.D = D;
		this.doorLeft = new Vector2d(W / 2 - D / 2, L);
		this.doorRight = new Vector2d(W / 2 + D / 2, L);
		this.escapePoint = new Vector2d(W / 2, 2 * L);
		this.walls = generateWalls();		
	}
	
	private List<Wall> generateWalls() {
		final List<Wall> walls = new ArrayList<Wall>(5);		
		walls.add(new Wall(new Vector2d(0, 0), new Vector2d(W, 0))); // upper wall
		walls.add(new Wall(new Vector2d(0, 0), new Vector2d(0, L * 2))); // left wall
		walls.add(new Wall(new Vector2d(W, 0), new Vector2d(W, L * 2))); // right wall
		walls.add(new Wall(new Vector2d(0, L), new Vector2d(W / 2 - D / 2, L))); // bottom left
		walls.add(new Wall(new Vector2d(W / 2 + D / 2, L), new Vector2d(W, L))); // bottom right		
//		walls.add(new Wall(new Vector2d(0, L), new Vector2d(W, L))); // bottom right
		return walls;
	}	

	public double getWidth() {
		return W;
	}

	public double getHeight() {
		return L;
	}

	public Vector2d getEscapePoint(final Particle particle) {
		final Vector2d particlePosition = particle.getPosition();
		if (particlePosition.y < L) {
			return Collision.distanceLinePoint(particlePosition, doorLeft.add(new Vector2d(particle.getRadius(), 0)), doorRight.substract(new Vector2d(particle.getRadius(), 0)));
		} else {
			return escapePoint;
		}
	}
	
	public List<Wall> getWalls() {
		return walls;
	}
	
	public boolean crossedDoor(final Particle particle) {
		return particle.getPosition().y + particle.getRadius() >= L;
	}

	public boolean escapedRoom(final Particle particle) {
		return particle.getPosition().y + particle.getRadius() >= 2 * L;
	}
	
}
