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
        Integer size= new Integer(cells[0].length * cells.length);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path +".output"))) {
			bw.write(size.toString() );
			bw.newLine();
			bw.newLine();
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[0].length; j++) {
                    Integer id = new Integer((i * cells.length) + j);
                    bw.write(id.toString() + " ");
					bw.write(i*5 + " ");
                    bw.write(j*5 + " ");
                    bw.write(0 + " ");
					if(!cells[i][j].isSolid()){
                        if(cells[i][j].size() == 0){
                            bw.write(0.001+ " ");
                            bw.write(0 + " ");
                            bw.write(0 + " ");
                            bw.write(0 + " ");
                        }else{
                            Double r = new Double(cells[i][j].size()/6);
                            bw.write( r.toString()+ " ");
                            bw.write(255 + " ");
                            bw.write(255 + " ");
                            bw.write(255 + " ");
                        }

					}else{
                        bw.write(1 + " ");
                        bw.write(255 + " ");
                        bw.write(0 + " ");
                        bw.write(0 + " ");
                    }
                    bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
