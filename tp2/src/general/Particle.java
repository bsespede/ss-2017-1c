package general;


import java.util.HashSet;
import java.util.Set;

public class Particle {

    private int x;
    private int y;
    private long id;
    private Direction dir;
    private int movementCounter = 0;

    public Particle(int x, int y, long id, Direction dir) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public static void resolveCollision(Set<Particle> particles){
        switch (particles.size()){
            case 2:
                resolve2DCollision(particles);
                break;
            case 3:
                if(isValid3DCollision(particles)){
                    resolve3DCollision(particles);
                }else{
                    Set<Particle> pAux = new HashSet(particles);
                    for(Particle p : particles){
                        pAux.remove(p);
                        resolve2DCollision(pAux);
                        pAux.add(p);
                    }
                }
                break;
            case 4:
                Set<Particle> pAux = new HashSet(particles);
                for(Particle p : particles){
                    pAux.remove(p);
                    resolve2DCollision(pAux);
                    pAux.add(p);
                }
                break;
            case 5:

                break;

        }

    }

    private static boolean isValid3DCollision(Set<Particle> particles) {
        Particle[] pArray = particles.toArray(new Particle[particles.size()]);

        return  (Direction.turnLeft(Direction.turnLeft(pArray[0].getDir())) == pArray[1].getDir() ||
                Direction.turnLeft(Direction.turnLeft(pArray[0].getDir())) == pArray[2].getDir()) &&
                (Direction.turnLeft(pArray[1].getDir()) == pArray[0].getDir() ||
                        Direction.turnLeft(pArray[1].getDir()) == pArray[2].getDir()) &&
                (Direction.turnLeft(pArray[2].getDir()) == pArray[0].getDir() ||
                        Direction.turnLeft(pArray[2].getDir()) == pArray[2].getDir())
                ;
    }

    private static void resolve3DCollision(Set<Particle> particles) {
        Particle[] pArray = particles.toArray(new Particle[particles.size()]);
        if(Math.random() >= .5){
            pArray[0].setDir(Direction.turnLeft(pArray[0].getDir()));
            pArray[1].setDir(Direction.turnLeft(pArray[1].getDir()));
            pArray[2].setDir(Direction.turnLeft(pArray[2].getDir()));
        }else{
            pArray[0].setDir(Direction.turnRight(pArray[0].getDir()));
            pArray[1].setDir(Direction.turnRight(pArray[1].getDir()));
            pArray[2].setDir(Direction.turnRight(pArray[2].getDir()));
        }
    }

    private static void resolve2DCollision(Set<Particle> particles) {
        Particle[] pArray = particles.toArray(new Particle[particles.size()]);
        if(pArray.length <= 2){
            return;
        }
        if(Direction.reverseX(pArray[0].getDir()) == pArray[1].getDir()){
            System.out.println("collision");
            if(Math.random() >= .5){
                pArray[0].setDir(Direction.turnLeft(pArray[0].getDir()));
                pArray[1].setDir(Direction.turnLeft(pArray[1].getDir()));
            }else{
                pArray[0].setDir(Direction.turnRight(pArray[0].getDir()));
                pArray[1].setDir(Direction.turnRight(pArray[1].getDir()));
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMovementCounter() {
        return movementCounter;
    }

    public void incMovementCounter() {
        this.movementCounter++;
    }
    public void resetMovementCounter() {
        this.movementCounter = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        if (x != particle.x) return false;
        if (y != particle.y) return false;
        return dir == particle.dir;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (dir != null ? dir.hashCode() : 0);
        return result;
    }
}
