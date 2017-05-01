package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import math.Vector2d;
import simulation.particle.Particle;

public class InputReader {
	
	public static Particle read(final String path) {
		double scale = 0.00001;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String[] input;
			input = br.readLine().split(" ");
			final String name = input[0];
			final Vector2d position = new Vector2d(Double.valueOf(input[1]) * scale, Double.valueOf(input[2]) * scale);
			final Vector2d velocity = new Vector2d(Double.valueOf(input[3]) * scale, Double.valueOf(input[4]) * scale);
			final double radius = Double.valueOf(input[5]) * scale;
			final double mass = Double.valueOf(input[6]) * scale;
			final Particle particle = new Particle(name, position, velocity, radius, mass);
			
			br.close();
			return particle;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Failed to read file " + path);		
	}

}
