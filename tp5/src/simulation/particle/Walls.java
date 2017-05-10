package simulation.particle;

import java.util.List;

public class Walls implements Collisionable {

	private final double L;
	private final double W;
	private final double D;
	
	public Walls(double L, double W, double D) {
		this.L = L;
		this.W = W;
		this.D = D;
	}

	public List<Particle> generateParticles(double maxRandGenTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean collides(Collisionable body) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getMass() {
		return Double.MAX_VALUE;
	}

}
