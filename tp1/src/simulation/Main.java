package simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.FileProcessor;
import particle.Particle;
import simulation.method.Bruteforce;
import simulation.method.CellIndex;

public class Main {

	private final static int L = 1000;
	private final static int N = 1000;
	private final static boolean CONTOUR = true;
	private final static int r = 5;
	private final static int rc = 240;
	private final static int M = 4;
	
	public static void main(String[] args) throws IOException {
		exampleMain();
	}

	private static void exampleMain() throws IOException {
		
		
		
		System.out.println("Generating particles...");
		Simulation simulation;
		//simulation = new CellIndex("./resources/Static100.txt", "./resources/Dynamic100.txt", true, 5);
		simulation = new CellIndex(L, N, CONTOUR, r, rc, M);
		//simulation = new Bruteforce(L, N, CONTOUR, r, rc);
		
		long time = System.currentTimeMillis();
		System.out.println("Calculating neighbours...");
		Map<Particle, Set<Particle>> neighboursMap = simulation.getNeighbours();
		
		System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds...");

		List<Particle> keys = new ArrayList<Particle>(neighboursMap.keySet());
		Particle randomParticle = keys.get((int)(Math.random() * keys.size()));
		Set<Particle> neighbours = neighboursMap.get(randomParticle);

		System.out.println("Writing output...");
		FileProcessor.writeExampleNeighbours(randomParticle, neighbours, keys,"./output.txt");
	}

}