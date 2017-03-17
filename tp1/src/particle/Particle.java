package particle;


public class Particle {

	private final int id;
    private final float x;
    private final float y;
    private final float radius;
    private final float interactionRadius;

    public Particle(final int id, final float x, final float y, final float radius, final float interactionRadius) {
    	this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.interactionRadius = interactionRadius;
    }

    public int getId() {
    	return id;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public float getInteractionRadius() {
        return interactionRadius;
    }

    @Override
    public int hashCode() {
        //not efficient but works
        return Float.hashCode(x + y);
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

	public boolean collides(final Particle particle) {
		final float distX = x - particle.x;
		final float distY = y - particle.y;
		final double distance = Math.sqrt(distX * distX + distY * distY);
		
		if (distance < radius + particle.radius) {
			return true;
		}
		return false;
	}

	public boolean isNeighbour(Particle neighbour) {
		final float distX = x - neighbour.x;
		final float distY = y - neighbour.y;
		final double distance = Math.sqrt(distX * distX + distY * distY);
		
		if (distance < interactionRadius + radius + neighbour.radius) {
			return true;
		}
		return false;
	}

}
