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
        double deltaVX = this.getVx() - p.getVx();
        double deltaVY = this.getVy() - p.getVy();
        double deltaX = this.getX() - p.getX();
        double deltaY = this.getY() - p.getY();
        double auxVR = deltaX * deltaVX + deltaY * deltaVY;
        double auxVV = Math.pow(deltaVX, 2) +  Math.pow(deltaVY, 2);
        double auxRR = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);

        if(auxVR >= 0){
            return Double.POSITIVE_INFINITY;
        }

        double d = Math.pow((auxVR),2) - auxVV * (auxRR - Math.pow(this.getRadius() + p.getRadius(),2));

        if (d < 0){
            return Double.POSITIVE_INFINITY;
        }

        return -(auxVR + Math.sqrt(d))/auxVV;


    }

    public double getWallCollisionTime(final double L){
        double xWall = vx > 0 ? L : -L;
        double yWall = vy > 0 ? L : -L;
        double xTime = vx != 0 ? (xWall - x - (Math.signum(vx) * radius)) / vx : Double.POSITIVE_INFINITY;
        double yTime = vy != 0 ? (yWall - y - (Math.signum(vy) * radius)) / vy : Double.POSITIVE_INFINITY;
        return xTime <= yTime ? xTime : yTime;
    }

    public void move(double time){
        x = x + vx * time;
        y = y + vy * time;
    }
}
