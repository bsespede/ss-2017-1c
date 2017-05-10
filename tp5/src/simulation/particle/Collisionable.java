package simulation.particle;

public interface Collisionable {

	boolean collides(final Collisionable body);

	double getMass();

}
