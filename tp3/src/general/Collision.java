package general;

/**
 * Created by julian on 05/04/17.
 */
public abstract class Collision {
    Particle p1;
    double t;

    public Collision(Particle p1, double t){
        this.p1 = p1;
        this.t = t;
    }

    public abstract void resolveCollision();

    public Particle getP1() {
        return p1;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }
}
