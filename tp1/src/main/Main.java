package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.FileProcessor;
import neighbours.CellIndex;
import neighbours.Neighbours;
import particle.Particle;

public class Main {

	final static boolean CONTOUR_ON = false;
	final static float L = 100;
	final static int M = 10;

	public static void main(String[] args) throws IOException {

		final Neighbours neighboursMethod = new CellIndex(CONTOUR_ON, L, M);
		final FileProcessor fp = new FileProcessor();

		System.out.println("Reading files...");
		final Set<Particle> particles = fp.processInputFile("./resources/Static100.txt", "./resources/Dynamic100.txt");
		
		System.out.println("Creating cells...");
		long time = System.currentTimeMillis();
		for (Particle particle: particles) {
			neighboursMethod.addParticle(particle);
		}

		System.out.println("Calculating neighbours...");
		Map<Particle, Set<Particle>> neighboursMap = neighboursMethod.getNeighbours();
		System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds...");		
		
		List<Particle> keys = new ArrayList<Particle>(neighboursMap.keySet());
		Particle randomParticle = keys.get((int)(Math.random() * keys.size()));
		Set<Particle> neighbours = neighboursMap.get(randomParticle);
		
		System.out.println("Writing output...");
		fp.writeOutputFile(neighboursMap, "./output.txt");
	}

}