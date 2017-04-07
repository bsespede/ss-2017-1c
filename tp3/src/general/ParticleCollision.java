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

    }
}
