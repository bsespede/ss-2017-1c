package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import math.Vector2d;
import simulation.Result;
import simulation.particle.Particle;
import simulation.silo.Silo;

public class OutputWriter {
	
	private static final int WALL_PARTICLES = 100;
	private BufferedWriter results;
	
	public OutputWriter(final String path) {
		try {
			results = new BufferedWriter(new FileWriter(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeSimulationResult(final Result result){
		try {
			//TODO write result output
			results.write(String.format("%b %.2f %.2f %.2f %.2f %.2f %.2f\n", 1));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void close() {
		try {
			results.flush();
			results.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void writeParticles(final String fileName, final List<Particle> particles, final Silo silo){
		try {
			final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			int count = 1;
			for (double i = 0; i < silo.getWidth(); i += silo.getWidth() / WALL_PARTICLES) {
				for (double j = 0; j < silo.getHeight(); j += silo.getHeight() / WALL_PARTICLES) {
					final Vector2d wallPosition = new Vector2d(i, j);
					if (!silo.isHole(wallPosition)) {
						writer.write(formatWall(count, wallPosition));
					}
				}
			}
			for (Particle particle : particles) {
				writer.write(formatParticle(count++, particle));
			}
			writer.flush();		
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String formatParticle(final int count, final Particle particle) {
		final Vector2d particlePosition = particle.getPosition();
		return String.format("%d %.2f %.2f %.2f %d %d %d\n", count, particlePosition.x, particlePosition.y, particle.getRadius(), 255, 0, 0);
	}
	
	private static String formatWall(final int count, final Vector2d wallPosition) {
		return String.format("%d %.2f %.2f %.2f %d %d %d\n", count, wallPosition.x, wallPosition.y , 0.1, 255, 255, 255);
	}

}