package general;

/**
 * Created by julian on 26/04/17.
 */
public abstract class Particle {

    private double x;
    private double vx;
    private double id;
    private double mass;

    public Particle(int id, double mass, double x, double vx) {
        this.x = x;
        this.vx = vx;
        this.id = id;
        this.mass = mass;
    }

    public abstract void move();

    public double getX() {
        return x;
    }

    public double getVx() {
        return vx;
    }

    public double getId() {
        return id;
    }

    public double getMass() {
        return mass;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }
}
