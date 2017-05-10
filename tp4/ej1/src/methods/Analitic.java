package methods;


import particle.Particle;

public class Analitic implements Method {

	double k;
	double g;

	public Analitic(double k, double g) {
		super();
		this.k = k;
		this.g = g;
	}

	// Only sets position
	public Particle moveParticle(Particle p, double time) {
		Particle nextP = new Particle(p.id, p.r, p.x, p.y, p.mass);
		double m = p.mass;
		nextP.x = Math.pow(Math.E, (-(g * time / (2 * m))))
				* Math.cos(Math.sqrt((k / m) - (Math.pow(g, 2) / (4 * Math.pow(m, 2)))) * time);
		return nextP;

	}
}
