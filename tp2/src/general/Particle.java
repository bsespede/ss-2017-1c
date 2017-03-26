package general;



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

    public static void resolveCollision(Particle ... particles){

    }

    public void invertX() {
        Direction newDir = dir;
        if(dir == Direction.UR){
            newDir = Direction.BR;
        }else if (dir == Direction.BR){
            newDir = Direction.UR;
        }else if (dir == Direction.UL) {
            newDir = Direction.BL;
        }else if (dir == Direction.BL) {
            newDir = Direction.UL;
        }
        dir = newDir;
    }

    public void invertY() {
        Direction newDir = dir;
        if(dir == Direction.UR){
            newDir = Direction.UL;
        }else if (dir == Direction.UL){
            newDir = Direction.UR;
        }else if (dir == Direction.R) {
            newDir = Direction.L;
        }else if (dir == Direction.L) {
            newDir = Direction.R;
        }else if (dir == Direction.BL) {
            newDir = Direction.BR;
        }else if (dir == Direction.BR) {
            newDir = Direction.BL;
        }
        dir = newDir;
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
}
