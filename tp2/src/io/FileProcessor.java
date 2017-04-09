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
						bw.write(i + " " + j);
	                    bw.newLine();
					} else if (j == 0 || j == obstacles[0].length - 1) {
						bw.write(i + " " + j);
	                    bw.newLine();
					}
				}
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void outputVelocities(final double[][] avgVelComps, final double[][] avgVelCoords, final String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (int i = 0; i < avgVelComps.length; i++) {
				bw.write(avgVelCoords[i][0] +","+ avgVelCoords[i][1] +","+ avgVelComps[i][0] +","+ avgVelComps[i][1]);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
