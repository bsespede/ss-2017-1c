package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import math.Vector2d;
import simulation.Result;
import simulation.particle.Particle;

public class OutputWriter {

	private static final double SCALE = 0.001;
	
	private BufferedWriter results;
	
	public OutputWriter(final String path) {
		try {
			results = new BufferedWriter(new FileWriter(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeSimulationResult(final Result result, final double angle, final double velocity){
		try {
			results.write(String.format("%b %.2f %.2f %.2f %.2f %.2f %.2f\n", result.hasPassedMarsOrbit(), velocity, angle, result.getLaunchTime(), result.getMinDistance(), result.getRelativeSpeed(), result.getTravelTime()));
			results.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void close() {
		try {
			results.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void writeParticles(final String fileName, final Double time, final List<Particle> particles){
		try {
			final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			int count = 1;
			writer.write(String.format("%d\n%g\n", particles.size(), time));
			for (Particle particle : particles) {
				writer.write(formatParticle(count++, particle));
			}
			writer.flush();		
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String formatParticle(final int count, final Particle p) {
		final Vector2d position = p.getPosition();
		return String.format("%d %.2f %.2f %.2f %d %d %d\n", count, position.x * SCALE, position.y * SCALE, p.getRadius() * 0.1, p.getBody().getRed(), p.getBody().getGreen(), p.getBody().getBlue());
	}

}