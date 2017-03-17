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
	
	final static boolean CONTOUR_ON = true;

	public static void main(String[] args) throws IOException {
		//TODO hacer un switch con input de usuario para elegir cual hacer
		exampleMain();
		//simulationForM();
	}

	private static void exampleMain() throws IOException {


		final int M = 10;
		final FileProcessor fp = new FileProcessor();

		System.out.println("Reading files...");
		final Set<Particle> inputParticles = fp.processInputFile("./resources/Static100.txt", "./resources/Dynamic100.txt");
		final Neighbours neighboursMethod = new CellIndex(inputParticles, CONTOUR_ON, M);

		System.out.println("Creating cells...");
		long time = System.currentTimeMillis();

		System.out.println("Calculating neighbours...");
		Map<Particle, Set<Particle>> neighboursMap = neighboursMethod.getNeighbours();
		System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds...");

		List<Particle> keys = new ArrayList<Particle>(neighboursMap.keySet());
		Particle randomParticle = keys.get((int)(Math.random() * keys.size()));
		Set<Particle> neighbours = neighboursMap.get(randomParticle);

		System.out.println("Writing output...");
		fp.writeOutputNeighboursFile(neighboursMap, "./outputNeighbours.txt"); // este es el output viejo con vecinos nomas
		fp.writeOutputParticlesFile(keys, "./outputParticles.txt"); // este te dice todas las particulas
		fp.writeExampleNeighbours(randomParticle, neighbours, keys,"./example.txt"); // este supongo que te marca los vecinos del seleccionado mas el resto
	}

	private static void simulationForM() throws IOException {
		System.out.println("Reading files...");
		final FileProcessor fp = new FileProcessor();
		final Set<Particle> inputParticles = fp.processInputFile("./resources/Static100.txt", "./resources/Dynamic100.txt");
		float maxIRadius = 0;
		float maxRadius = 0;
		for(Particle particle: inputParticles){
			if(particle.getRadius() > maxRadius){
				maxRadius = particle.getRadius();
			}
			if(particle.getRadius() > maxRadius){
				maxIRadius = particle.getInteractionRadius();
			}
		}
		while(inputParticles.size()<=5000){
			inputParticles.add(new Particle(100 + inputParticles.size(),(float) Math.random() * inputParticles.size() , (float) Math.random() * inputParticles.size() , maxRadius, maxIRadius));
		}

		for(int m = 1; (inputParticles.size() / maxRadius + 2 * maxIRadius ) >= m && m >= 1 ; m++){
			final Neighbours neighboursMethod = new CellIndex(inputParticles, CONTOUR_ON, m);
			System.out.println("Creating cells...");
			long time = System.currentTimeMillis();

			System.out.println("Calculating neighbours...");
			Map<Particle, Set<Particle>> neighboursMap = neighboursMethod.getNeighbours();
			System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds... with m = " + String.valueOf(m));

		}
	}/*
	private static void simulationForN() throws IOException {
		System.out.println("Reading files...");
		final FileProcessor fp = new FileProcessor();
		final Set<Particle> inputParticles = fp.processInputFile("./tp1/resources/Static100.txt", "./tp1/resources/Dynamic100.txt");
		float maxIRadius = 0;
		float maxRadius = 0;
		for (Particle particle : inputParticles) {
			if (particle.getRadius() > maxRadius) {
				maxRadius = particle.getRadius();
			}
			if (particle.getRadius() > maxRadius) {
				maxIRadius = particle.getInteractionRadius();
			}
		}
		while (inputParticles.size() <= 5000) {
			inputParticles.add(new Particle(100 + inputParticles.size(), (float) Math.random() * L, (float) Math.random() * L, maxRadius, maxIRadius));
		}
		final int m = (int) Math.floor(L / maxRadius + 2 * maxIRadius);
		for (; inputParticles.size() <= 5000; ) {
			final Neighbours neighboursMethod = new CellIndex(CONTOUR_ON, L, m);
			System.out.println("Creating cells...");
			long time = System.currentTimeMillis();
			for (Particle particle : inputParticles) {
				neighboursMethod.addParticle(particle);
			}

			System.out.println("Calculating neighbours...");
			Map<Particle, Set<Particle>> neighboursMap = neighboursMethod.getNeighbours();
			System.out.println("Finished in " + (System.currentTimeMillis() - time) / 1000f + " seconds... with m = " + String.valueOf(m));

		}
	}*/

	}