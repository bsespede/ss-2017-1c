package general;

/**
 * Created by julian on 05/04/17.
 */
public class WallCollision extends Collision {

    public WallCollision(Particle collisionP1, double t) {
        super(collisionP1, t);
    }

    @Override
    public void resolveCollision(){
        //TODO replace magic number with L
        if(Math.abs(p1.getX() + p1.getRadius()) >= 10 && Math.abs(p1.getX() + p1.getRadius()) >= 10){
            p1.setVx(p1.getVx() * -1);
            p1.setVy(p1.getVy() * -1);
            return;
        }
        double xf = p1.getX() * p1.getVx() * t;
        double yf = p1.getY() * p1.getVy() * t;
        if(Math.abs(xf) > Math.abs(yf)){
            p1.setVx(p1.getVx() * -1);
        }else{
            p1.setVy(p1.getVy() * -1);
        }
    }
}

