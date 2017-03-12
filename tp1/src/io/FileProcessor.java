package io;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import particle.Particle;

/**
 * Class created to parse data for the program to function.
 *
 */
public class FileProcessor {

    /**
     * Parses the input file, which contains two positions (x and y) per line.
     *
     * @param path The path to the file.
     * @return A set of Particles to be considered valid for the simulation, or {@code null}
     * on error.
     * @throws IOException If the Scanner can't be closed.
     */
    public static Set<Particle> processInputFile(String path) throws IOException {
        Scanner s = null;
        Set<Particle> result = new HashSet<>();
        try {
            s = new Scanner(new File(path));
            int count = s.nextInt();
            for(int i = 0; i < count; i++) {
                float x = s.nextFloat();
                float y = s.nextFloat();
                float radius = s.nextFloat();
                float integrationRadius = s.nextFloat();
                result.add(new Particle(x , y , radius , integrationRadius));
            }
        } catch (IOException e) {
            result = null;
        } finally {
            if (s != null) s.close();
        }
        return result;
    }

    /**
     * Writes the neighbours of each Particle to a file with the specified path.
     *
     * @param neighbours A map with the particle as the key and its neighbours as the value.
     * @param path  The path of the output file.
     * @return {@code true} If the write was completed successfully, {@code false} on error.
     * @throws IOException If the write stream can't be closed.
     */
    public static boolean writeOutputFile(Map<Particle,Set<Particle>> neighbours, String path) throws IOException {
        boolean success = true;
        FileWriter w = null;
        Set<Particle> particles = neighbours.keySet();
        try {
            w = new FileWriter(path);
            w.write(String.valueOf(particles.size()));
            //Windows newline != linux newline
            w.write(System.getProperty("line.separator"));
            for (Particle p : particles) {
                //writes the number of neighbours of the particle
                w.write(String.valueOf(neighbours.get(p).size()));
                w.write(System.getProperty("line.separator"));
                for (Particle n : neighbours.get(p)) {
                    w.write(String.valueOf(n.getX()));
                    w.write(" ");
                    w.write(String.valueOf(n.getY()));
                    w.write(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            success = false;
        } finally {
            if (w != null) w.close();
        }
        return success;
    }

}
