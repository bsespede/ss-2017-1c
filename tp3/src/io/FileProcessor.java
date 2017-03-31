package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import particle.Particle;

public class FileProcessor {
    /**
     * Writes all Particles' properties to a file with the specified path.
     *
     * @param particles A collection with the particles.
     * @param path  The path of the output file.
     * @return {@code true} If the write was completed successfully, {@code false} on error.
     * @throws IOException If the write stream can't be closed.
     */
    public static boolean writeOutputParticlesFile(Collection<Particle> particles, String path) throws IOException {
        boolean success = true;
        FileWriter w = null;
        try {
            w = new FileWriter(path);
            w.write(String.valueOf(particles.size()));
            w.write(System.getProperty("line.separator"));
            w.write(System.getProperty("line.separator"));
            for (Particle p : particles) {
                writeParticle(p, w, 0 , 0, 255);
            }
        } catch (IOException e) {
            success = false;
        } finally {
            if (w != null) w.close();
        }
        return success;
    }

    private static void writeParticle(Particle p, FileWriter w, int r, int g, int b) throws IOException {
        w.write(String.valueOf(p.getId()) + " ");
        w.write(String.valueOf(p.getX() + " "));
        w.write(String.valueOf(p.getY()) + " ");
        w.write(String.valueOf(0) + " ");
        w.write(String.valueOf(p.getRadius()) + " ");
        w.write(String.valueOf(r) + " ");
        w.write(String.valueOf(g) + " ");
        w.write(String.valueOf(b) + " ");
        w.write(System.getProperty("line.separator"));
    }
}
