package simulation.force;

import math.Vector2d;

public class Force {

	private final double module;
	private final Vector2d direction;

	public Force(final double module, final Vector2d direction) {
		this.module = module;
		this.direction = direction;
	}

	public double getModule() {
		return module;
	}

	public Vector2d getDirection() {
		return direction;
	}

	public Vector2d getForce() {
		return direction.scale(module);
	}
	
	public Double getForceModuleInDirection(Vector2d direction2) {
		double angle = direction.dot(direction2) / direction2.module();
		return module * Math.cos(angle);
	}
	
}
