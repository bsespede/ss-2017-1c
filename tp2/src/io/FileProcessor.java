package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

	public static void outputBoundaries(final int[][] obstacles, final String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (int i = 0; i < obstacles.length; i++) {
				for (int j = 0; j < obstacles[0].length; j++) {
					if (obstacles[i][j] == 1) {
						bw.write(i + "," + j);
	                    bw.newLine();
					} else if (j == 0 || j == obstacles[0].length - 1) {
						bw.write(i + "," + j);
	                    bw.newLine();
					}
				}
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void outputVelocities(final double[][][] velocities, final String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (int i = 0; i < velocities.length; i++) {
				bw.write(velocities[i][0][0] +","+ velocities[i][0][1] +","+ velocities[i][1][0] +","+ velocities[i][1][1]);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
