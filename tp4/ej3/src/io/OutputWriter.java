package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import math.Vector2d;
import simulation.particle.Particle;

public class OutputWriter {
	
	private final BufferedWriter writer;

	public OutputWriter(final String fileName) throws IOException {
		this.writer = new BufferedWriter(new FileWriter(fileName));
	}

	public void write(final Double time, final Particle sun, final List<Particle> particles) throws IOException {
		writer.write(String.format("%d\n%g\n", particles.size() + 1, time));
		writer.write(formatParticle(sun));
		for (Particle particle : particles) {
			writer.write(formatParticle(particle));
		}		
		writer.flush();
	}

	private static String formatParticle(Particle p) {
		final Vector2d position = p.getPosition();
		return String.format("%s %f %f %f %f\n", p.getId(), position.x, position.y, p.getMass(), p.getRadius());
	}

	public void closeWriter() throws IOException {
		writer.close();
	}

}
