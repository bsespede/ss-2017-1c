package simulation.particle;

import math.Vector2d;

public class Particle {

	private final String name;
    private Vector2d position;
    private Vector2d velocity;
    private final double mass;
    private final double radius;
    
    public Particle(final String name, final Vector2d position, final Vector2d velocity, final double radius, final double mass) {
    	this.name = name;
		this.position = position;
		this.velocity = velocity;
		this.radius = radius;
		this.mass = mass;
	}
    
    public String getName() {
		return name;
	}

	public Vector2d getPosition() {
    	return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }
    
    public double getMass() {
        return mass;
    }
    
    public double getRadius() {
        return radius;
    }

    public void setPosition(final Vector2d position) {
    	this.position = position;
    }

    public void setVelocity(final Vector2d velocity) {
    	this.velocity = velocity;
    }
    
    public double getKineticEnergy(){    	
		return 0.5 * mass * (velocity.x * velocity.x + velocity.y * velocity.y);
	}
	
	public double getPotentialEnergy(double G, double sunMass){
		return (-G) * sunMass * mass / position.module();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Particle [name=" + name + ", position=" + position + ", velocity=" + velocity + ", mass=" + mass
				+ ", radius=" + radius + "]";
	}
	
}