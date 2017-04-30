package general;

/**
 * Created by julian on 28/04/17.
 */
public class BeemanParticle extends Particle{

    private double rPrevious;
    private double vPrevious;
    private double vActual;
    private double deltaT;
    private double k;
    private double g;

    public BeemanParticle(int id, double mass, double x, double vx, double deltaT, double k, double g) {
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

    @Override
    public void move() {
        setPosition();
        setVelocity();
    }

    private void setVelocity() {
        double vFuture = vActual + (1.0 / 3) * ((-k * getX() - g * vActual) / getMass()) * deltaT
                + (5.0 / 6) * ((-k * getX() - g * vActual) / getMass()) * deltaT
                - (1.0 / 6) * ((-k * getX() - g * vActual) / getMass()) * deltaT;
        vPrevious = vActual;
        vActual = vFuture;
    }

    private void setPosition() {
        double rFuture =  getX() + vActual * deltaT + (2.0 / 3) * ((-k * getX() - g * vActual) / getMass()) * Math.pow(deltaT, 2)
                - (1.0 / 6) * ((-k * getX() - g * vActual) / getMass()) * Math.pow(deltaT, 2);
        rPrevious = getX();
        setX(rFuture);
    }


}
