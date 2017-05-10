package methods;


import particle.Particle;
import simulator.Oscillator;

public class Verlet implements Method {

	private double deltaT;

	public Verlet(double deltaT) {
		super();
		this.deltaT = deltaT;
	}

	public Particle moveParticle(Particle p, double time) {
		Particle nextP = new Particle(p.id, p.r, p.x, p.y, p.mass);
		setPositions(nextP, p, time);
		setSpeeds(nextP, p);
		return nextP;
	}

	private void setSpeeds(Particle nextP, Particle p) {
		nextP.speedX = (nextP.x - p.prevX) / (2 * deltaT);
		nextP.speedY = (nextP.y - p.prevY) / (2 * deltaT);
	}

	private void setPositions(Particle nextP, Particle p, double time) {
		nextP.x = 2 * p.x - p.prevX + (Math.pow(deltaT, 2) / p.mass) * Oscillator.getForceX(time, p);
		nextP.y = 2 * p.y - p.prevY + (Math.pow(deltaT, 2) / p.mass) * Oscillator.getForceY(time, p);
	}
}
