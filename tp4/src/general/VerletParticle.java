package general;

/**
 * Created by julian on 26/04/17.
 */
public class VerletParticle extends Particle {

    private double rPrevious;
    private double vPrevious;
    private double vActual;
    private double deltaT;
    private double k;
    private double g;



    public VerletParticle(int id, double mass, double x, double vx, double deltaT, double k, double g) {
        super(id,mass,x,vx);
        this.deltaT = deltaT;
        this.k = k;
        this.g = g;
        init();
    }

    private void init() {
        rPrevious = getX() - deltaT * vActual + Math.pow(deltaT, 2) * (-k * getX() - g * vActual) / (2 * getMass());
        vPrevious = vActual - (deltaT / getMass()) * (-k * getX() - g * vActual);
    }

    public void move() {
        setPosition();
        setVelocity();
    }

    private void setVelocity() {
        double vFuture = getX() - rPrevious / (2 * deltaT);
        vPrevious = vActual;
        vActual = vFuture;
    }

    private void setPosition() {
        double rFuture =  2 * getX() - rPrevious + (Math.pow(deltaT, 2) / getMass()) * (-k * getX() - g * vActual);
        rPrevious = getX();
        setX(rFuture);
    }
}
