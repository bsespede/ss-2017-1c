package general;


import static general.Direction.BR;

public class Particle {

    private int x;
    private int y;
    private int id;
    Direction dir;

    public Particle(int x, int y, int id, Direction dir) {
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
        if()
    }

    public void invertY() {
    }
}
