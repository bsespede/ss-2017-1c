package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

	public static void outputSimulation(final int[][] obstacles, final double[][] velocities, final int grainSize, final String path) { 
		int curVal = 0;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write(obstacles.length * obstacles[0].length + "");
			bw.newLine();
			bw.newLine();
			for (int i = 0; i < obstacles.length; i++) {
				for (int j = 0; j < obstacles[0].length; j++) {
					// id de la celda
					bw.write(i + j * obstacles.length + " ");
					// posicion x, y
					bw.write(i + " ");
					bw.write(j + " ");
					if(obstacles[i][j] == 1) {
						// tamaño particula, cantidad de rojo
						bw.write(20 + " ");
						bw.write(255 + " ");
					} else {
						bw.write(0 + " ");
						bw.write(0 + " ");
					}
					if (i % grainSize == 0 && j % grainSize == 0) {
						// vector velocidad
						bw.write(velocities[curVal][0] + " ");
						bw.write(velocities[curVal][1] + " ");
						curVal++;
						bw.newLine();
					} else {
						// vector velocidad
						bw.write(0 + " ");
						bw.write(0 + " ");
						bw.newLine();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
