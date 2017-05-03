package io;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import math.Vector2d;
import simulation.particle.Particle;

public class OutputWriter {

	private final static double SCALE = 0.000001;

	public static void writeParticles(final String fileName, final Double time, final List<Particle> particles) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		int count = 1;
		writer.write(String.format("%d\n%g\n", particles.size(), time));
		for (Particle particle : particles) {
			writer.write(formatParticle(count++, particle));
		}
		writer.flush();		
		writer.close();
	}

	private static String formatParticle(final int count, final Particle p) {
		final Vector2d position = p.getPosition();
		final Color color = colorGetter(p);
		return String.format("%d %.2f %.2f %.2f %d %d %d\n", count, position.x * SCALE, position.y * SCALE, p.getRadius() * 0.1, color.getRed(), color.getGreen(), color.getBlue());
	}

	private static Color colorGetter(final Particle particle) {
		switch (particle.getName()) {
		case "EARTH":
			return new Color(0, 0, 255);
		case "SUN":
			return new Color(255, 0, 0);
		case "MARS":
			return new Color(255, 0, 255);
		default:
			return new Color(0, 255, 0);
		}
	}

	public static void writeDistances(String fileName, Map<Double, Double> spaceshipMarsDistance) throws IOException {
		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(fileName));
		for (Double time: spaceshipMarsDistance.keySet()) {
			writer.write(String.format("%.2f %.2f\n", time, spaceshipMarsDistance.get(time)));
		}
		writer.flush();		
		writer.close();
	}

}