package simulation.silo;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import math.Vector2d;
import simulation.particle.Particle;

public class Silo {

	private final static double EPSILON = 0.01;
	private final double L;
	private final double W;
	private final double D;	
	
	public Silo(final double L, final double W, final double D) {
		this.L = L;
		this.W = W;
		this.D = D;
	}

	public List<Particle> generateParticles(final int N, final double maxRandGenTime) {
		List<Particle> particles = new ArrayList<>();
		particles.add(new Particle(new Vector2d(W/2,L), new Vector2d(0,0), 0.5, 1));
		return particles;
	}

	public Vector2d getIntersectionPoint(final Particle particle) {
//		if(isHole(particle.getPosition())) return null;
		double x = particle.getPosition().x;
		double y = particle.getPosition().y;
		Vector2d intersectionPoint;

		//checks right intersection
        intersectionPoint = distanceLinePoint(x, y , W, 0, W, L);
        if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) return intersectionPoint;

		//checks left intersection
		intersectionPoint = distanceLinePoint(x, y , 0 , 0, 0, L);
		if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) return intersectionPoint;

		//checks bottom left intersection
		intersectionPoint = distanceLinePoint(x, y , 0 , L, (W - D) / 2, L);
		if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) return intersectionPoint;

		//checks bottom right intersection
		intersectionPoint = distanceLinePoint(x, y , 0 , L, ((W - D) / 2) + D, L);
		if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) return intersectionPoint;

		//checks end intersection
		intersectionPoint = distanceLinePoint(x, y , -W , L + Main.BOTTOM_DISTANCE,  W * 2, L + Main.BOTTOM_DISTANCE);
		if(intersectionPoint != null && intersectionPoint.distance(particle.getPosition()) <= particle.getRadius()) return intersectionPoint;

		return null;
	}

	// El 0,0 esta arriba a la izq
	public boolean escapedSilo(Particle particle) {
		return particle.getPosition().y > L + D;
	}

	public void resetParticle(Particle particle) {
		// TODO Auto-generated method stub
		
	}

	public double getWidth() {
		return W;
	}

	public double getHeight() {
		return L;
	}

	public boolean isHole(final Vector2d position) {
		final double holeFrom = W / 2 - D / 2;
		final double holeTo = W /2 + D / 2;

        return position.x >= holeFrom && position.x <= holeTo && Math.abs(position.y - L) < EPSILON;
	}

	//returns the line's point which is at minimum distance from the sphere's center
	public Vector2d distanceLinePoint(double x,double y,double x1,double y1,double x2,double y2) {

		double a = x - x1;
		double b = y - y1;
		double c = x2 - x1;
		double d = y2 - y1;

		double dot = a * c + b * d;
		double lenSq = c * c + d * d;
		double param = -1;
		if (lenSq != 0) //in case of 0 length line
			param = dot / lenSq;

		double xx, yy;

		if (param < 0) {
			xx = x1;
			yy = y1;
		}
		else if (param > 1) {
			xx = x2;
			yy = y2;
		}
		else {
			xx = x1 + param * c;
			yy = y1 + param * d;
		}

		return new Vector2d(xx, yy);
	}
}
