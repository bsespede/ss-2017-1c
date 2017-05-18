package io;

import main.Main;
import simulation.particle.Particle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;


public class FileProcessor {
    /**
     * Writes all Particles' properties to a file with the specified path.
     *
     * @param particles A collection with the particles.
     * @param path  The path of the output file.
     * @return {@code true} If the write was completed successfully, {@code false} on error.
     * @throws IOException If the write stream can't be closed.
     */
    public static boolean writeOutputParticlesFile(Collection<Particle> particles, String path){
        boolean success = true;
        FileWriter w = null;
        try {
            w = new FileWriter(path, true);
            w.write(String.valueOf(particles.size() + 7));
            w.write(System.getProperty("line.separator"));
            w.write(System.getProperty("line.separator"));
            for (Particle p : particles) {
                try{
                    if(p.getId() == 0){
                        writeParticle(p, w, 255 , 0, 0);
                    }else{
                        writeParticle(p, w, 0 , 255, 0);
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (IOException e) {
            success = false;
        } finally {
            if (w != null) try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    private static void writeParticle(Particle p, FileWriter w, int r, int g, int b) throws IOException {
        w.write(String.valueOf(p.getId()) + " ");
        w.write(String.valueOf(p.getPosition().x) + " ");
        w.write(String.valueOf(p.getPosition().y) + " ");
        w.write(String.valueOf(0) + " ");
        w.write(String.valueOf(p.getRadius()) + " ");
        w.write(String.valueOf(r) + " ");
        w.write(String.valueOf(g) + " ");
        w.write(String.valueOf(b) + " ");
        w.write(System.getProperty("line.separator"));
    }

    public static void printBorders(final double L, String path) {
        FileWriter w = null;
        try {
            try {
                w = new FileWriter(path, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                writeBorder(0, 0 , 0.01, w, 255, 255, 255);
                writeBorder(Main.W, 0, 0.01, w, 255, 255, 255);
                writeBorder((Main.W - Main.D)/2, Main.L , 0.01, w, 255, 255, 255);
                writeBorder((Main.W - Main.D)/2 + Main.D, Main.L, 0.01, w, 255, 255, 255);
                writeBorder(0, Main.L, 0.01, w, 255, 255, 255);
                writeBorder(Main.W, Main.L, 0.01, w, 255, 255, 255);
                writeBorder(Main.W / 2, Main.L + Main.BOTTOM_DISTANCE, 0.01, w, 255, 255, 255);

            }
            catch (IOException e){
                e.printStackTrace();
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBorder(double x, double y, double radius, FileWriter w, int r, int g, int b) throws IOException {
        w.write(String.valueOf(-1) + " ");
        w.write(String.valueOf((double)Math.round(x * 10d) / 10d) + " ");
        w.write(String.valueOf((double)Math.round(y * 10d) / 10d) + " ");
        w.write(String.valueOf(0) + " ");
        w.write(String.valueOf(radius) + " ");
        w.write(String.valueOf(r) + " ");
        w.write(String.valueOf(g) + " ");
        w.write(String.valueOf(b) + " ");
        w.write(System.getProperty("line.separator"));
    }
}
