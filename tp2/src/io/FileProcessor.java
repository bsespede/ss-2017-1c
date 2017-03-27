package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import general.Cell;
import general.Particle;

/**
 * Created by julian on 23/03/17.
 */
public class FileProcessor {

	public static void outputState(Cell[][] cells, Set<Particle> particles, String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[0].length; j++) {
					if(!cells[i][j].isSolid()){
						bw.write(cells[i][j].size() +" ");
					}
					bw.write("0 ");
				}
				bw.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
