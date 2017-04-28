package general;

/**
 * Created by julian on 28/04/17.
 */
public class GearParticle extends Particle {

    private double rPrevious;
    private double vPrevious;
    private double vActual;
    private double deltaT;
    private double k;
    private double g;
    Integer[] factorials = new Integer[20];
    double[] vec = {  (3.0 / 20), (251.0 / 260), 1.0, (11.0 / 18), (1.0 / 6), (1.0 / 60) };

    double[] rListPrev = new double[6];
    double[] rListActual = new double[6];

    public GearParticle(int id, double mass, double x, double vx, double deltaT, double k, double g) {
        super(id,mass,x,vx);
        this.deltaT = deltaT;
        this.k = k;
        this.g = g;
        init();
    }

    private void init() {
        rListPrev[0] = getX();
        rListPrev[1] = vActual;
        rListPrev[2] = (-k * getX() - g * vActual) / getMass();
        rListPrev[3] = -(k / getMass()) * rListPrev[1] - (g / getMass()) * rListPrev[2];
        rListPrev[4] = -(k / getMass()) * rListPrev[2] - (g / getMass()) * rListPrev[3];
        rListPrev[5] = -(k / getMass()) * rListPrev[3] - (g / getMass()) * rListPrev[4];
    }

    @Override
    public void move() {
        updateRList();

        setX(rListActual[0]);
        setVx(rListActual[1]);

        double force = (-k * getX() - g * vActual) / getMass();
        double deltaR2 = (force - rListActual[2]) * (Math.pow(deltaT, 2) / getFactorial(2));

        correctRList(deltaR2);

        setX(rListActual[0]);
        setVx(rListActual[1]);
        for (int i = 0; i < rListPrev.length; i++) {
            rListPrev[i] = rListActual[i];
        }
    }

    private void updateRList() {
        rListActual[0] = rListPrev[0] + rListPrev[1] * deltaT + rListPrev[2] * (Math.pow(deltaT, 2) / getFactorial(2))
                + rListPrev[3] * (Math.pow(deltaT, 3) / getFactorial(3))
                + rListPrev[4] * (Math.pow(deltaT, 4) / getFactorial(4))
                + rListPrev[5] * (Math.pow(deltaT, 5) / getFactorial(5));
        rListActual[1] = rListPrev[1] + rListPrev[2] * deltaT + rListPrev[3] * (Math.pow(deltaT, 2) / getFactorial(2))
                + rListPrev[4] * (Math.pow(deltaT, 3) / getFactorial(3))
                + rListPrev[5] * (Math.pow(deltaT, 4) / getFactorial(4));
        rListActual[2] = rListPrev[2] + rListPrev[3] * deltaT + rListPrev[4] * (Math.pow(deltaT, 2) / getFactorial(2))
                + rListPrev[5] * (Math.pow(deltaT, 3) / getFactorial(3));
        rListActual[3] = rListPrev[3] + rListPrev[4] * deltaT + rListPrev[5] * (Math.pow(deltaT, 2) / getFactorial(2));
        rListActual[4] = rListPrev[4] + rListPrev[5] * deltaT;
        rListActual[5] = rListPrev[5];
    }

    private void correctRList(double deltaR2) {
        for (int i = 0; i < rListActual.length; i++) {
            rListActual[i] = rListActual[i] + (vec[i] * deltaR2 * (getFactorial(i) / Math.pow(deltaT, i)));
        }
    }

    private int getFactorial(int i) {
        if (factorials[i] != null)
            return factorials[i];
        if (i == 0) {
            factorials[i] = 1;
            return 1;
        }
        int n = i * getFactorial(i - 1);
        factorials[i] = n;
        return n;
    }
}
