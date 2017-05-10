package simulator;
import methods.Method;
import particle.Particle;

import java.util.List;

public class Oscillator {

	public Method method;
	public static double k = Math.pow(10, 4);
	public static double g = 100;
	public double m;
	public double deltaT;
	public static double tf = 5;


	public Particle p;

	public Oscillator(Method method, double m,
					  double deltaT) {
		super();
		this.method = method;
		this.m = m;
		this.deltaT = deltaT;

		p = new Particle(0, 0, 1, 0, -(g / (2 * m)), 0, m);
	}

	public void setPrevState() {
		p.prevX = p.x - deltaT * p.speedX + Math.pow(deltaT, 2) * this.getForceX(0, p) / (2 * p.mass);
		p.prevY = 0;

		p.prevSpeedX = p.speedX - (deltaT / p.mass) * this.getForceX(0, p);
		p.prevSpeedY = 0;
	}

	public void initializeR() {
		p.rListX[0] = p.x;
		p.rListX[1] = p.speedX;
		p.rListX[2] = (this.getForceX(0, p)) / p.mass;
		p.rListX[3] = -(k / m) * p.rListX[1] - (g / m) * p.rListX[2];
		p.rListX[4] = -(k / m) * p.rListX[2] - (g / m) * p.rListX[3];
		p.rListX[5] = -(k / m) * p.rListX[3] - (g / m) * p.rListX[4];
	}

	public static void run(Oscillator analiticOscillator, List<Oscillator> list, double deltaT) {

		double currentTime = 0;

		double[] errors = new double[3];

		while (currentTime < tf) {
			analiticOscillator.p = analiticOscillator.method.moveParticle(analiticOscillator.p, currentTime + deltaT);
			for (int i = 0; i < list.size(); i++) {
				Oscillator oscillator = list.get(i);
				oscillator.p = oscillator.method.moveParticle(oscillator.p, currentTime);
				errors[i] += (Math.pow(analiticOscillator.p.x - oscillator.p.x, 2));
			}
			currentTime += deltaT;
		}

		for (int i = 0; i < errors.length; i++) {
			errors[i] /= (tf / deltaT);
			System.out.print(errors[i] + "\t");
		}
		System.out.println();
	}


	public static double getForceX(double time, Particle p) {
		return -k * p.x - g * p.speedX;
	}

	public static double getForceY(double time, Particle p) {
		return -k * p.y - g * p.speedY;
	}
}
