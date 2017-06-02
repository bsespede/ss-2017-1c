package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import math.Vector2d;
import simulation.particle.Particle;
import terrain.Terrain;
import terrain.Wall;

public class OutputWriter {
	
	private BufferedWriter results;
	
	public OutputWriter(final String path) {
		try {
			results = new BufferedWriter(new FileWriter(path));
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
	
	public static void writeParticles(final String fileName, final List<Particle> particles, final Terrain terrain){
		try {
			final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			int count = 1;
			writer.write(String.format("%d\n\n", particles.size() + 83));
			for (Wall wall: terrain.getWalls()) {
				final double minX = wall.getMinX();
				final double maxX = wall.getMaxX();
				final double minY = wall.getMinY();
				final double maxY = wall.getMaxY();
				for (double x = minX; x <= maxX; x += 0.5) {
					for (double y = minY; y <= maxY; y += 0.5) {
						writer.write(formatWall(count++, new Vector2d(x, y)));
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

	private static String formatParticle(final int id, final Particle particle) {
		final Vector2d particlePosition = particle.getPosition();
		return String.format("%d %.2f %.2f %.2f %d %d %d\n", id, particlePosition.x, particlePosition.y, particle.getRadius(), 255, 0, 0);
	}
	
	private static String formatWall(final int id, final Vector2d wallPosition) {
		return String.format("%d %.2f %.2f %.2f %d %d %d\n", id, wallPosition.x, wallPosition.y , 0.1, 255, 255, 255);
	}

}