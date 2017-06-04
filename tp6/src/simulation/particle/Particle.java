package simulation.particle;

import math.Vector2d;

public class Particle {

	private int id;
	private Vector2d position;
    private Vector2d velocity;
    private Vector2d prevPosition;
    private Vector2d prevVelocity;
    private final double desiredVelocity;
    private final double mass;
    private final double radius;
    
    public Particle(int id, final Vector2d position, final Vector2d velocity, final double desiredVelocity, final double mass, final double radius) {
    	this.id = id;
    	this.position = position;
		this.velocity = velocity;
		this.prevPosition = null;
		this.prevVelocity = null;
		this.desiredVelocity = desiredVelocity;
		this.mass = mass;
		this.radius = radius;
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

	public void setPosition(final Vector2d position) {
		this.position = position;
	}

	public void setVelocity(final Vector2d velocity) {
		this.velocity = velocity;
	}

	public void setPrevPosition(final Vector2d prevPosition) {
		this.prevPosition = prevPosition;
	}

	public void setPrevVelocity(final Vector2d prevVelocity) {
		this.prevVelocity = prevVelocity;
	}

	public double getDesiredVelocity() {
		return desiredVelocity;
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

	public double distance(final Particle p2) {
		double centerDistance = position.distance(p2.getPosition());
		return centerDistance - radius - p2.getRadius();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(desiredVelocity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((prevPosition == null) ? 0 : prevPosition.hashCode());
		result = prime * result + ((prevVelocity == null) ? 0 : prevVelocity.hashCode());
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
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Particle [position=" + position + ", velocity=" + velocity + ", prevPosition=" + prevPosition
				+ ", prevVelocity=" + prevVelocity + ", mass=" + mass + ", radius=" + radius + "]";
	}

	public boolean collides(final Particle particle) {
		return distance(particle) < 0;
	}

	public int getId() {
		return id;
	}

	public double getEfficiency(final Vector2d desiredPoint) {
		final Vector2d drivingdDirection = desiredPoint.substract(position).normalize();
		return velocity.dot(drivingdDirection)/ desiredVelocity;
	}
	
}