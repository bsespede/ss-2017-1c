package general;

public class Particle {

    private final int id;
    private double mass;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private final double radius;
    private double interactionRadius;

    public Particle(int id, double mass, double x, double y, double vx, double vy, double radius, double interactionRadius) {
        this.id = id;
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.interactionRadius = interactionRadius;
    }

    public double calculateDistance(final Particle candidateNeighbour) {
        final double dx = candidateNeighbour.x - x;
        final double dy = candidateNeighbour.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getInteractionRadius() {
        return interactionRadius;
    }

    public void setInteractionRadius(double interactionRadius) {
        this.interactionRadius = interactionRadius;
    }

    public int getId() {
        return id;
    }

    public double getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getMass() {
        return mass;
    }

    public double getParticleCollisionTime(Particle p){
        double xTime = this.vx - p.getVx() != 0 ? (p.getX() - this.x) / this.vx - p.getVx() : Double.POSITIVE_INFINITY;
        double yTime = this.vy - p.getVy() != 0 ? (p.getY() - this.y) / this.vy - p.getVy() : Double.POSITIVE_INFINITY;
        return xTime <= yTime ? xTime : yTime;
    }

    public double getWallCollisionTime(final double L){
        double xWall = this.getVx() > 0 ? L : -L;
        double yWall = this.getVy() > 0 ? L : -L;
        double xTime = this.getVx() !=0 ? xWall - this.getX() / this.getVx() : Double.POSITIVE_INFINITY;
        double yTime = this.getVy() !=0 ? yWall - this.getY() / this.getVy() : Double.POSITIVE_INFINITY;
        return xTime <= yTime ? xTime : yTime;
    }

    public void move(double time){
        this.x = this.x + vx * time;
        this.y = this.y + vy * time;
    }

    public void resolveCollision (Particle p){
        //TODO
    }
}
