package particle;


public class Particle {
	public final int id;
	public final double r;

	public double x;
	public double y;
	public double prevX;
	public double prevY;

	public double speedX;
	public double speedY;

	public double prevSpeedX;
	public double prevSpeedY;

	public final double mass;

	public double[] rListX = new double[6];
	public double[] rListY = new double[6];

	public Particle(int id, double r, double x, double y, double prevX, double prevY, double speedX, double speedY,
			double prevSpeedX, double prevSpeedY, double mass) {
		super();
		this.id = id;
		this.r = r;
		this.x = x;
		this.y = y;
		this.prevX = prevX;
		this.prevY = prevY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.prevSpeedX = prevSpeedX;
		this.prevSpeedY = prevSpeedY;
		this.mass = mass;
	}

	public Particle(int id, double r, double x, double y, double prevX, double prevY, double speedX, double speedY,
			double mass) {
		super();
		this.id = id;
		this.r = r;
		this.x = x;
		this.y = y;
		this.prevX = prevX;
		this.prevY = prevY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.mass = mass;
	}

	public Particle(int id, double r, double x, double y, double speedX, double speedY, double mass) {
		super();
		this.id = id;
		this.r = r;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.mass = mass;
	}

	public Particle(int id, double r, double prevX, double prevY, double mass) {
		super();
		this.id = id;
		this.r = r;
		this.prevX = prevX;
		this.prevY = prevY;
		this.mass = mass;
	}
}