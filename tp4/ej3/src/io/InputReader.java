package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import math.Vector3d;
import simulation.particle.Particle;

public class InputReader {
	
	public static Particle read(final String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String[] input;
			input = br.readLine().split(" ");
			final String name = input[0];
			final Vector3d position = new Vector3d(Double.valueOf(input[1]), Double.valueOf(input[2]), Double.valueOf(input[3]));
			final Vector3d velocity = new Vector3d(Double.valueOf(input[4]), Double.valueOf(input[5]), Double.valueOf(input[6]));
			final double radius = Double.valueOf(input[7]);
			final double mass = Double.valueOf(input[8]);
			final Particle particle = new Particle(name, position, velocity, radius, mass);
			
			br.close();
			return particle;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Failed to read file " + path);		
	}

}
