package simulation.particle;

import math.Vector2d;
import simulation.bodies.Body;

public class Particle {

	private final Body body;
	private Vector2d position;
    private Vector2d velocity;
    private Vector2d prevPosition;
    private Vector2d prevVelocity;
    private final double mass;
    private final double radius;
    
    public Particle(final Body body, final Vector2d position, final Vector2d velocity, final double radius, final double mass) {
    	this.body = body;
    	this.position = position;
		this.velocity = velocity;
		this.prevPosition = null;
		this.prevVelocity = null;
		this.radius = radius;
		this.mass = mass;
	}
    
    public Body getBody() {
		return body;
	}

	public Vector2d getPosition() {
    	return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }
    
    public Vector2d getPrevPosition() {
		return prevPosition;
	}

	public Vector2d getPrevVelocity() {
		return prevVelocity;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public void setVelocity(Vector2d velocity) {
		this.velocity = velocity;
	}

	public void setPrevPosition(Vector2d prevPosition) {
		this.prevPosition = prevPosition;
	}

	public void setPrevVelocity(Vector2d prevVelocity) {
		this.prevVelocity = prevVelocity;
	}

	public double getMass() {
        return mass;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double getKineticEnergy(){    	
		return 0.5 * mass * (velocity.x * velocity.x + velocity.y * velocity.y);
	}
	
	public double getPotentialEnergy(double G, double sunMass){
		return (-G) * sunMass * mass / position.module();
	}

	public double distance(final Particle p2) {
		double centerDistance = position.distance(p2.getPosition());
		return centerDistance - radius - p2.getRadius();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((velocity == null) ? 0 : velocity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Particle [name=" + body + ", position=" + position + ", velocity=" + velocity + ", prevPosition="
				+ prevPosition + ", prevVelocity=" + prevVelocity + ", mass=" + mass + ", radius=" + radius + "]";
	}
	
}