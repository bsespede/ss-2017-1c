package general;


import java.util.ArrayList;
import java.util.List;

public class Node {

	private final boolean isSolid;
	private final List<Particle> particles;

	public Node(final boolean isSolid) {
		this.isSolid = isSolid;
		this.particles = new ArrayList<>(6);
	}
	
	public List<Particle> getParticles() {
		return particles;
	}
	
	public boolean addParticle(final Particle particle) {
		if (!particles.contains(particle)) {
			return particles.add(particle);
		}
		return false;
	}

	public boolean removeParticle(final Particle particle) {
		return particles.remove(particle);
	}

	public boolean isSolid() {
		return isSolid;
	}

	public boolean resolveCollision() {
//		switch (particles.size()) {
//			case 2:
//			{
//				final Particle p1, p2;
//				p1 = particles.get(0);
//				p2 = particles.get(1);
//				particles.clear();
//				if (Math.random() > 0.5) {
//					particles.add(new Particle(p1.getX(), p1.getY(), p1.getDirection().clockwise()));
//					particles.add(new Particle(p2.getX(), p2.getY(), p2.getDirection().clockwise()));
//				} else {
//					particles.add(new Particle(p1.getX(), p1.getY(), p1.getDirection().counterClockwise()));
//					particles.add(new Particle(p2.getX(), p2.getY(), p2.getDirection().counterClockwise()));
//				}
//				return true;
//			}
//			case 3: 
//			{
//				final Particle p1, p2, p3;
//				p1 = particles.get(0);
//				p2 = particles.get(1);
//				p3 = particles.get(2);
//				particles.clear();
//				particles.add(new Particle(p1.getX(), p1.getY(), p1.getDirection().reverse()));
//				particles.add(new Particle(p2.getX(), p2.getY(), p2.getDirection().reverse()));
//				particles.add(new Particle(p3.getX(), p3.getY(), p3.getDirection().reverse()));
//				return true;
//			}
//			default:
//				return false;
//		}			
		return true;
	}
	
	
	public double[] getVelocityVector() {
		double x = 0, y = 0, magnitude = 1;
		if (!isSolid()) {
			for (Particle particle: particles) {
				switch (particle.getDirection()) {
				case UR:
					x += 1;
					y += 1;
					break;
		        case BR:
		        	x += 1;
					y += -1;
					break;
		        case BL:
		        	x += -1;
					y += -1;
					break;
		        case UL:
		        	x += -1;
					y += 1;
					break;
		        case R:
		        	x += 1;
					break;
		        default:
		        	x += -1;
					break;
				}
			}
		}
		magnitude = Math.sqrt(x * x + y * y);
		
		return new double[]{x, y, magnitude};
	}
}
