package particle;


public class Particle {

	private final int id;
    private final double x;
    private final double y;
    private final double radius;
    private final double interactionRadius;

    public Particle(final int id, final double x, final double y, final double particlesRadius, final double interactionRadius) {
    	this.id = id;
        this.x = x;
        this.y = y;
        this.radius = particlesRadius;
        this.interactionRadius = interactionRadius;
    }

    public int getId() {
    	return id;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getInteractionRadius() {
        return interactionRadius;
    }

    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Particle.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Particle other = (Particle) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
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

}
