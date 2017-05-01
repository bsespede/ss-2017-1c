package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import math.Vector3d;
import simulation.particle.Particle;

public class OutputWriter {

	public static void write(final String fileName, final Double time, final Particle spaceship, final List<Particle> particles) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		int count = 1;
		writer.write(String.format("%d\n%g\n", particles.size() + 1, time));
		writer.write(formatParticle(count++, spaceship));
		for (Particle particle : particles) {
			writer.write(formatParticle(count++, particle));
		}
		writer.flush();		
		writer.close();
	}

	private static String formatParticle(final int count, final Particle p) {
		final Vector3d position = p.getPosition();
		return String.format("%s %f %f %f %f\n", count, position.x, position.y, position.z, p.getMass(), p.getRadius());
	}

}