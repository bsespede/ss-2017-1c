package methods;

import particle.Particle;
import simulator.Oscillator;

public class Gear implements Method {

	double deltaT;

	Integer[] fact = new Integer[6];
	double[] vec = { (3.0 / 20), (251.0 / 260), 1.0, (11.0 / 18), (1.0 / 6), (1.0 / 60) };

	public Gear(double deltaT) {
		this.deltaT = deltaT;
	}

	public Particle moveParticle(Particle p, double time) {
		Particle nextP = new Particle(p.id, p.r, p.x, p.y, p.mass);
		updateRListX(nextP, p);

		nextP.x = nextP.rListX[0];
		nextP.speedX = nextP.rListX[1];

		double real_acceleration = Oscillator.getForceX(time, nextP) / nextP.mass;
		double deltaR2 = (real_acceleration - nextP.rListX[2]) * (Math.pow(deltaT, 2) / factorial(2));

		correction(nextP, deltaR2);

		nextP.x = nextP.rListX[0];
		nextP.speedX = nextP.rListX[1];

		return nextP;
	}

	private void correction(Particle nextP, double deltaR2) {
		for (int i = 0; i < nextP.rListX.length; i++) {
			nextP.rListX[i] = nextP.rListX[i] + (getAlpha(i) * deltaR2 * (factorial(i) / Math.pow(deltaT, i)));
		}

	}

	private double getAlpha(int i) {
		return vec[i];
	}

	private void updateRListX(Particle nextP, Particle p) {
		nextP.rListX[0] = p.rListX[0] + p.rListX[1] * deltaT + p.rListX[2] * (Math.pow(deltaT, 2) / factorial(2))
				+ p.rListX[3] * (Math.pow(deltaT, 3) / factorial(3))
				+ p.rListX[4] * (Math.pow(deltaT, 4) / factorial(4))
				+ p.rListX[5] * (Math.pow(deltaT, 5) / factorial(5));
		nextP.rListX[1] = p.rListX[1] + p.rListX[2] * deltaT + p.rListX[3] * (Math.pow(deltaT, 2) / factorial(2))
				+ p.rListX[4] * (Math.pow(deltaT, 3) / factorial(3))
				+ p.rListX[5] * (Math.pow(deltaT, 4) / factorial(4));
		nextP.rListX[2] = p.rListX[2] + p.rListX[3] * deltaT + p.rListX[4] * (Math.pow(deltaT, 2) / factorial(2))
				+ p.rListX[5] * (Math.pow(deltaT, 3) / factorial(3));
		nextP.rListX[3] = p.rListX[3] + p.rListX[4] * deltaT + p.rListX[5] * (Math.pow(deltaT, 2) / factorial(2));
		nextP.rListX[4] = p.rListX[4] + p.rListX[5] * deltaT;
		nextP.rListX[5] = p.rListX[5];
	}

	private void updateRListY(Particle nextP, Particle p) {
		nextP.rListY[0] = p.rListY[0] + p.rListY[1] * deltaT + p.rListY[2] * (Math.pow(deltaT, 2) / factorial(2))
				+ p.rListY[3] * (Math.pow(deltaT, 3) / factorial(3))
				+ p.rListY[4] * (Math.pow(deltaT, 4) / factorial(4))
				+ p.rListY[5] * (Math.pow(deltaT, 5) / factorial(5));
		nextP.rListY[1] = p.rListY[1] + p.rListY[2] * deltaT + p.rListY[3] * (Math.pow(deltaT, 2) / factorial(2))
				+ p.rListY[4] * (Math.pow(deltaT, 3) / factorial(3))
				+ p.rListY[5] * (Math.pow(deltaT, 4) / factorial(4));
		nextP.rListY[2] = p.rListY[2] + p.rListY[3] * deltaT + p.rListY[4] * (Math.pow(deltaT, 2) / factorial(2))
				+ p.rListY[5] * (Math.pow(deltaT, 3) / factorial(3));
		nextP.rListY[3] = p.rListY[3] + p.rListY[4] * deltaT + p.rListY[5] * (Math.pow(deltaT, 2) / factorial(2));
		nextP.rListY[4] = p.rListY[4] + p.rListY[5] * deltaT;
		nextP.rListY[5] = p.rListY[5];
	}

	private int factorial(int num) {
		if (fact[num] != null)
			return fact[num];
		if (num == 0) {
			fact[num] = 1;
			return 1;
		}
		int n = num * factorial(num - 1);
		fact[num] = n;
		return n;

	}

}
