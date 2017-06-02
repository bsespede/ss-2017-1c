//package simulation.integrator;
//
//
//import math.Vector2d;
//import simulation.force.Force;
//import simulation.particle.Particle;
//import simulation.silo.Silo;
//
//import java.util.List;
//
//public class Verlet implements Integrator {
//
//	private double deltaT;
//
//	public Verlet(/*double deltaT*/) {
//		super();
////		this.deltaT = deltaT;
//	}
//
//	@Override
//	public Particle move(Particle p, List<Particle> particles, final Silo silo, final double dt) {
//		Vector2d totalForce = Force.getTotalForce(p, particles,silo);
//		Particle nextP = new Particle( new Vector2d(0, 0), new Vector2d(0,0), p.getRadius(), p.getMass(), p.getId());
//		nextP.setPrevPosition(new Vector2d(p.getPosition().x, p.getPosition().y));
//		setPositions(nextP, p, dt, totalForce, dt);
//		setSpeeds(nextP, p, dt);
//		return nextP;
//	}
//
//	private void setSpeeds(Particle nextP, Particle p, double dt) {
//		double speedX = (nextP.getPosition().x - p.getPrevPosition().x) / (2 * dt);
//		double speedY = (nextP.getPosition().y - p.getPrevPosition().y) / (2 * dt);
//		nextP.setVelocity(new Vector2d(speedX, speedY));
//	}
//
//	private void setPositions(Particle nextP, Particle p, double time, Vector2d totalForce, double dt) {
//
//		double x = 2 * p.getPosition().x - p.getPrevPosition().x + (Math.pow(dt, 2) / p.getMass()) * totalForce.x;
//		double y = 2 * p.getPosition().y - p.getPrevPosition().y + (Math.pow(dt, 2) / p.getMass()) * totalForce.y;
//		nextP.setPosition(new Vector2d(x, y));
//	}
//}
