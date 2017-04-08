package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Simulator.Simulation;
import general.Node;

public class FileProcessor {

	public static void outputState(final Simulation simulation, final String path) {        
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			final Node[][] nodes = simulation.getNodes();
			bw.write(simulation.getNodesNumber() + "");
			bw.newLine();
			bw.newLine();
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodes[0].length; j++) {
					// id de la celda
                    bw.write(i + j * nodes.length + " ");
                    // posicion x, y
					bw.write(i * 20 + " ");
					bw.write(j * 20 + " ");
					if(!nodes[i][j].isSolid()) {
						// tamaño particula, cantidad de rojo
						bw.write(0 + " ");
                        bw.write(0 + " ");
					} else {
                        bw.write(20 + " ");
                        bw.write(255 + " ");
                    }
					// vector velocidad
					final double[] vector = nodes[i][j].getVelocityVector();
					//bw.write((int)((nodes[i][j].getParticles().size()> 1)? 255:0) + " ");
					bw.write(vector[0] + " ");
					bw.write(vector[1] + " ");
					bw.write(vector[2] + " ");
                    bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void outputData(final List<Integer> collisionHistory, final List<Double> flowHistory, final float finalTime, final String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write(finalTime + "");
			bw.newLine();
			for (int i = 0; i < collisionHistory.size(); i++) {
				bw.write(i + 1 +" "+ collisionHistory.get(i) +" "+ flowHistory.get(i));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
