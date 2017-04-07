package general;

/**
 * Created by julian on 05/04/17.
 */
public abstract class Collision {
    Particle p1;
    double t;

    public abstract void resolveCollision();

    public Particle getP1() {
        return p1;
    }

    public double getT() {
        return t;
    }
}
