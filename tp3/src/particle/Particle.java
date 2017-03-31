package particle;

public class Particle {

    private final int id;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private final double radius;
    private double interactionRadius;

    public Particle(int id, double x, double y, double vx, double vy, double radius, double interactionRadius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.interactionRadius = interactionRadius;
    }

    public double calculateContourDistance(final Particle candidateNeighbour, final double L) {
        double lInv = 1.0 / L;
        double dx = candidateNeighbour.x - x;
        dx -= L * Math.round(dx * lInv);
        double dy = candidateNeighbour.y - y;
        dy -= L * Math.round(dy * lInv);
        return  Math.sqrt(dx * dx + dy * dy);
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
}
