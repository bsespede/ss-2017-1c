package main;

import java.io.File;
import java.io.IOException;
import java.util.*;

import io.FileProcessor;
import neighbours.CellIndex;
import neighbours.Neighbours;
import particle.Particle;

public class Main {
	final static boolean CONTOUR_ON = false;
	final static float L = 100;


	public static void main(String[] args) throws IOException {
		//TODO hacer un switch con input de usuario para elegir cual hacer
		exampleMain();
		simulationForM();
	}

	private static void exampleMain() throws IOException {


		final int M = 10;
		final Neighbours neighboursMethod = new CellIndex(CONTOUR_ON, L, M);
		final FileProcessor fp = new FileProcessor();

		System.out.println("Reading files...");
		final Set<Particle> inputParticles = fp.processInputFile("./tp1/resources/Static100.txt", "./tp1/resources/Dynamic100.txt");
		System.out.println("Creating cells...");
		long time = System.currentTimeMillis();
		for (Particle particle: inputParticles) {
			neighboursMethod.addParticle(particle);
		}

		System.out.println("Calculating neighbours...");
		Map<Particle, Set<Particle>> neighboursMap = neighboursMethod.getNeighbours();
		System.out.println("Finished in "+ (System.currentTimeMillis() - time) / 1000f +" seconds...");

		List<Particle> keys = new ArrayList<Particle>(neighboursMap.keySet());
		Particle randomParticle = keys.get((int)(Math.random() * keys.size()));
		Set<Particle> neighbours = neighboursMap.get(randomParticle);

		System.out.println("Writing output...");
		fp.writeOutputNeighboursFile(neighboursMap, "./tp1/outputNeighbours.txt");
		fp.writeOutputParticlesFile(keys, "./tp1/outputParticles.txt");
		fp.writeExampleNeighbours(randomParticle, neighbours, keys,"./tp1/example.txt");
	}

	private static void simulationForM() throws IOException {
		System.out.println("Reading files...");
		final FileProcessor fp = new FileProcessor();
		final Set<Particle> inputParticles = fp.processInputFile("./tp1/resources/Static100.txt", "./tp1/resources/Dynamic100.txt");
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
			inputParticles.add(new Particle(100 + inputParticles.size(),(float) Math.random() * L , (float) Math.random() * L , maxRadius, maxIRadius));
		}

		for(int m = 1; (L / maxRadius + 2 * maxIRadius ) >= m && m >= 1 ; m++){
			final Neighbours neighboursMethod = new CellIndex(CONTOUR_ON, L, m);
			System.out.println("Creating cells...");
			long time = System.currentTimeMillis();
			for (Particle particle: inputParticles) {
				neighboursMethod.addParticle(particle);
			}

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