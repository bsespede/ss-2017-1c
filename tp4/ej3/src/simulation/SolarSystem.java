package simulation;

import java.util.List;

import simulation.particle.Particle;

public class SolarSystem {
	
	private final String id;
	private final Particle sun;
	private List<Particle> particles;

	public SolarSystem(final String id, final Particle sun, final List<Particle> particles) {
		this.id = id;
		this.sun = sun;
		this.particles = particles;
	}

	public String getId() {
		return id;
	}

	public Particle getSun() {
		return sun;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((particles == null) ? 0 : particles.hashCode());
		result = prime * result + ((sun == null) ? 0 : sun.hashCode());
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
		SolarSystem other = (SolarSystem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (particles == null) {
			if (other.particles != null)
				return false;
		} else if (!particles.equals(other.particles))
			return false;
		if (sun == null) {
			if (other.sun != null)
				return false;
		} else if (!sun.equals(other.sun))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SolarSystem [id=" + id + ", sun=" + sun + ", particles=" + particles + "]";
	}

}
