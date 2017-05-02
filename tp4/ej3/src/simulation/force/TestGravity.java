package simulation.force;

import java.util.LinkedList;
import java.util.List;

import math.Vector2d;
import simulation.particle.Particle;

public class TestGravity {

	public static void main(String[] args) {
		Particle p1 = new Particle("P1", new Vector2d(0,0), new Vector2d(0, 0), 2, Math.pow(10, 5));
		Particle p2 = new Particle("P2", new Vector2d(10,0), new Vector2d(0, 0), 3, Math.pow(10, 6));
		Particle p3 = new Particle("P3", new Vector2d(20,0), new Vector2d(0, 0), 3, Math.pow(10, 5));
		List<Particle> others = new LinkedList<Particle>();
		others.add(p1);
		others.add(p2);
		others.add(p3);
		System.out.println(Gravity.gravitationalForceBetween(p1, others));
	}
	
}
