package methods;


import particle.Particle;
import simulator.Oscillator;

public class Beeman implements Method {

	private double deltaT;

	public Beeman(double deltaT) {
		super();
		this.deltaT = deltaT;
	}

	public Particle moveParticle(Particle p, double time) {
		Particle nextP = new Particle(p.id, p.r, p.x, p.y, p.mass);
		nextP.prevSpeedX = p.speedX;
		nextP.prevSpeedY = p.speedX;

		setPositions(nextP, p, time);
		setSpeeds(nextP, p, time);
		return nextP;
	}

	private void setSpeeds(Particle nextP, Particle p, double time) {
		double m = p.mass;

		Particle previousP = new Particle(p.id, p.r, p.prevX, p.prevY, 0, 0, p.prevSpeedX, p.prevSpeedY, 0, 0, p.mass);
		Particle currP = new Particle(p.id, p.r, nextP.x, nextP.y, 0, 0, p.speedX, p.speedY, 0, 0, p.mass);

		nextP.speedX = p.speedX + (1.0 / 3) * (Oscillator.getForceX(time + deltaT, currP) / m) * deltaT
				+ (5.0 / 6) * (Oscillator.getForceX(time, p) / m) * deltaT
				- (1.0 / 6) * (Oscillator.getForceX(time - deltaT, previousP) / m) * deltaT;
		nextP.speedY = p.speedY + (1.0 / 3) * (Oscillator.getForceY(time + deltaT, currP) / m) * deltaT
				+ (5.0 / 6) * (Oscillator.getForceY(time, p) / m) * deltaT
				- (1.0 / 6) * (Oscillator.getForceY(time - deltaT, previousP) / m) * deltaT;

	}

	private void setPositions(Particle nextP, Particle p, double time) {
		double m = p.mass;

		Particle previousP = new Particle(p.id, p.r, p.prevX, p.prevY, 0, 0, p.prevSpeedX, p.prevSpeedY, 0, 0, p.mass);

		nextP.x = p.x + p.speedX * deltaT + (2.0 / 3) * (Oscillator.getForceX(time, p) / m) * Math.pow(deltaT, 2)
				- (1.0 / 6) * (Oscillator.getForceX(time - deltaT, previousP) / m) * Math.pow(deltaT, 2);
		nextP.y = p.y + p.speedY * deltaT + (2.0 / 3) * (Oscillator.getForceY(time, p) / m) * Math.pow(deltaT, 2)
				- (1.0 / 6) * (Oscillator.getForceY(time - deltaT, previousP) / m) * Math.pow(deltaT, 2);
	}

}
