package io;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import particle.Particle;

/**
 * Class created to parse data for the program to function.
 *
 */
public class FileProcessor {

	/**
	 * Parses the input file, which contains two positions (x and y) per line.
	 *
	 * @param staticFile The path to the Particle's static properties.
	 * @param dynamicFile The path to the Particle's dynamic properties.
	 * @return A set of Particles to be considered valid for the simulation, or {@code null}
	 * on error.
	 * @throws IOException If the Scanner can't be closed.
	 */
	public static Set<Particle> processInputFile(final String staticFile, final String dynamicFile) throws IOException {
		final Set<Particle> result = new HashSet<>();
		final Scanner staticScanner = new Scanner(new File(staticFile));
		final Scanner dynamicScanner = new Scanner(new File(dynamicFile));
		int count = staticScanner.nextInt();
		int L = staticScanner.nextInt();
		dynamicScanner.nextLine();
		for(int i = 0; i < count; i++) {
			float x = dynamicScanner.nextFloat();
			float y = dynamicScanner.nextFloat();
			float radius = staticScanner.nextFloat();
			float integrationRadius = staticScanner.nextFloat();
			result.add(new Particle(i + 1, x, y, radius, integrationRadius));
		}
		return result;
	}

	/**
	 * Writes the neighbours of each Particle to a file with the specified path.
	 *
	 * @param neighbours A map with the particle as the key and its neighbours as the value.
	 * @param path  The path of the output file.
	 * @return {@code true} If the write was completed successfully, {@code false} on error.
	 * @throws IOException If the write stream can't be closed.
	 */
	public static boolean writeOutputNeighboursFile(Map<Particle,Set<Particle>> neighbours, String path) throws IOException {
		boolean success = true;
		FileWriter w = null;
		Set<Particle> particles = neighbours.keySet();
		try {
			w = new FileWriter(path);
			for (Particle p : particles) {
				w.write(String.valueOf(p.getId()));
				for (Particle p2 : neighbours.get(p)) {
					w.write(","+ p2.getId());
				}
				w.write(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			success = false;
		} finally {
			if (w != null) w.close();
		}
		return success;
	}

	/**
	 * Writes all Particles' properties to a file with the specified path.
	 *
	 * @param particles A collection with the particles.
	 * @param path  The path of the output file.
	 * @return {@code true} If the write was completed successfully, {@code false} on error.
	 * @throws IOException If the write stream can't be closed.
	 */
	public static boolean writeOutputParticlesFile(Collection<Particle> particles, String path) throws IOException {
		boolean success = true;
		FileWriter w = null;
		try {
			w = new FileWriter(path);
			w.write(String.valueOf(particles.size()));
			w.write(System.getProperty("line.separator"));
			w.write(System.getProperty("line.separator"));
			for (Particle p : particles) {
				writeParticle(p, w, 0 , 0, 255);
			}
		} catch (IOException e) {
			success = false;
		} finally {
			if (w != null) w.close();
		}
		return success;
	}

	/**
	 * Writes one Particle and its neighbours' properties to a file with the specified path.
	 *
	 * @param particle The randomly picked particle.
	 * @param neighbours A collection of its neighbours.
	 * @param allParticles A collection with all particles.
	 * @param path  The path of the output file.
	 * @return {@code true} If the write was completed successfully, {@code false} on error.
	 * @throws IOException If the write stream can't be closed.
	 */
	public static boolean writeExampleNeighbours(Particle particle, Collection<Particle> neighbours, Collection<Particle> allParticles, String path) throws IOException {
		boolean success = true;
		FileWriter w = null;
		try {
			w = new FileWriter(path);
			w.write(String.valueOf(allParticles.size()));
			w.write(System.getProperty("line.separator"));
			w.write(System.getProperty("line.separator"));
			for (Particle p : allParticles) {
				if(neighbours.contains(p)){
					writeParticle(p , w, 255, 0 , 0);
				}
				else if(particle.equals(p)){
					writeParticle(p , w, 0, 255 , 0);
				}
				else{
					writeParticle(p , w, 0, 0 , 255);
				}
			}
		} catch (IOException e) {
			success = false;
		} finally {
			if (w != null) w.close();
		}
		return success;
	}

	private static void writeParticle(Particle p, FileWriter w, int r, int g, int b) throws IOException {
		w.write(String.valueOf(p.getId()) + " ");
		w.write(String.valueOf(p.getX()) + " ");
		w.write(String.valueOf(p.getY()) + " ");
		w.write(String.valueOf(0) + " ");
		w.write(String.valueOf(p.getRadius()) + " ");
		w.write(String.valueOf(r) + " ");
		w.write(String.valueOf(g) + " ");
		w.write(String.valueOf(b) + " ");
		w.write(System.getProperty("line.separator"));
	}

	public static int readL(final String staticInput) {
		Scanner staticScanner;
		try {
			staticScanner = new Scanner(new File(staticInput));
			int count = staticScanner.nextInt();
			return staticScanner.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;		
	}

	public static List<Particle> readParticles(final String staticInput, final String dynamicInput) {
		final List<Particle> result = new LinkedList<Particle>();
		try {
			final Scanner staticScanner = new Scanner(new File(staticInput));
			final Scanner dynamicScanner = new Scanner(new File(dynamicInput));
			int count = staticScanner.nextInt();
			int L = staticScanner.nextInt();
			dynamicScanner.nextLine();
			for(int i = 1; i <= count; i++) {
				float x = dynamicScanner.nextFloat();
				float y = dynamicScanner.nextFloat();
				float radius = staticScanner.nextFloat();
				float integrationRadius = staticScanner.nextFloat();
				result.add(new Particle(i, x, y, radius, integrationRadius));
			}
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
