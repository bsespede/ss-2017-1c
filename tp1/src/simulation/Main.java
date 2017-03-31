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

	public static void main(String[] args) throws IOException {
		//TODO hacer un switch con input de usuario para elegir cual hacer
		exampleMain();
		//simulationForM();
	}

	private static void exampleMain() throws IOException {
		
		System.out.println("Generating particles...");
		Simulation simulation;
		//simulation = new CellIndex("./resources/Static100.txt", "./resources/Dynamic100.txt", true, 5);
		simulation = new CellIndex(1000, 1000, true, 5, 240, 4);
		//simulation = new Bruteforce(1000, 1000, true, 5, 240);
		
		long time = System.currentTimeMillis();
		System.out.println("Calculating neighbours...");
		Map<Particle, Set<Particle>> neighboursMap = simulation.getNeighbours();
		
		System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds...");

		List<Particle> keys = new ArrayList<Particle>(neighboursMap.keySet());
		Particle randomParticle = keys.get((int)(Math.random() * keys.size()));
		Set<Particle> neighbours = neighboursMap.get(randomParticle);

		System.out.println("Writing output...");
		FileProcessor.writeExampleNeighbours(randomParticle, neighbours, keys,"./example.txt"); // este supongo que te marca los vecinos del seleccionado mas el resto
	}

}