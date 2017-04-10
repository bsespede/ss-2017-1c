package general;

/**
 * Created by julian on 05/04/17.
 */
public class ParticleCollision extends Collision{
    private Particle p2;

    public ParticleCollision(Particle p1, Particle p2, double t) {
        super(p1, t);
        this.p2 = p2;
    }

    @Override
    public void resolveCollision() {
        double deltaVX = p1.getVx() - p2.getVx();
        double deltaVY = p1.getVy() - p2.getVy();
        double deltaX = p1.getX() - p2.getX();
        double deltaY = p1.getY() - p2.getY();
        double auxVR = deltaX * deltaVX + deltaY * deltaVY;
        double j = 2 * p1.getMass() * p2.getMass() * auxVR;
        j /= (p1.getRadius() + p2.getRadius()) * (p2.getMass() + p1.getMass()) ;
        double jX = j * (p1.getX() - p2.getX()) / p1.getRadius() + p2.getRadius();
        double jY = j * (p1.getY() - p2.getY()) / p1.getRadius() + p2.getRadius();
        p1.setVx(p1.getVx() + (jX / p1.getMass()));
        p2.setVx(p2.getVx() - (jX / p2.getMass()));
        p1.setVy(p1.getVy() + (jY / p1.getMass()));
        p2.setVy(p2.getVy() - (jY / p2.getMass()));
    }
}
