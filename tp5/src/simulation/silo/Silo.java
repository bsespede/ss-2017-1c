package simulation.silo;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	public boolean collides(final Particle particle) {
		final boolean atHole = isHole(particle.getPosition());
        return !atHole && (intersectsBottom(particle) || intersectsLeft(particle) || intersectsRight(particle));
	}

    private boolean intersectsRight(Particle particle) {
        return particle.getPosition().x + particle.getRadius() > W;
	}

    private boolean intersectsLeft(Particle particle) {
        return particle.getPosition().x - particle.getRadius() < 0;
	}

	//TODO Review
    private boolean intersectsBottom(Particle particle) {
        return particle.getPosition().y + particle.getRadius() > L;
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

}
