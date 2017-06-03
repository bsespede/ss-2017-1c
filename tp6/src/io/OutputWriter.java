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
	
	private BufferedWriter buffer;
	
	public OutputWriter(final String path) {
		try {
			buffer = new BufferedWriter(new FileWriter(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			buffer.flush();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void writeOutput(final List<Particle> particles, final Terrain terrain){
		try {
			int count = 0;
			buffer.write(String.format("%d\n\n", particles.size() + 2 * terrain.getWalls().size()));
			for (Particle particle : particles) {
				buffer.write(formatParticle(count++, particle));
			}
			int wallId = 1;
			for (Wall wall: terrain.getWalls()) {
				final Vector2d p0 = wall.getP0();
				final Vector2d p1 = wall.getP1();
				buffer.write(formatWall(count++, new Vector2d(p0.x, p0.y), wallId));
				buffer.write(formatWall(count++, new Vector2d(p1.x, p1.y), wallId++));
			}
			buffer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String formatParticle(final int id, final Particle particle) {
		final Vector2d particlePosition = particle.getPosition();
		return String.format("%d %.2f %.2f %.2f %d %d %d %d %.2f\n", id, particlePosition.x, particlePosition.y, particle.getRadius(), 255, 0, 0, 0, particle.getVelocity().module());
	}
	
	private static String formatWall(final int id, final Vector2d wallPosition, final int wallId) {
		return String.format("%d %.2f %.2f %.2f %d %d %d %d %.2f\n", id, wallPosition.x, wallPosition.y , 0.01, 255, 255, 255, wallId, 0.0);
	}

}