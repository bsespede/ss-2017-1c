package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import neighbours.CellIndex;
import neighbours.Neighbours;
import particle.Particle;

public class Main {

	final static int PARTICLE_NUM = 100;
	final static float RC = 1;
	final static float R = 0.25f;
	final static float L = 100;
	final static int M = 10;

	public static void main(String[] args) throws IOException {

		Neighbours neighboursMethod = new CellIndex(false, L, M);

		System.out.println("Creating particles...");
		long time = System.currentTimeMillis();
		for (int i = 0; i < PARTICLE_NUM; i++) {
			Particle particle = new Particle((float) Math.random() * L, (float) Math.random() * L, R, RC);
			neighboursMethod.addParticle(particle);
		}

		System.out.println("Calculating neighbours...");
		Map<Particle, Set<Particle>> neighboursMap = neighboursMethod.getNeighbours();
		
		System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds...");
		
		List<Particle> keys = new ArrayList<Particle>(neighboursMap.keySet());
		Particle randomParticle = keys.get((int)(Math.random() * keys.size()));
		Set<Particle> neighbours = neighboursMap.get(randomParticle);
		
	}

}