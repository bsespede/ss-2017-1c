package io;

import java.io.FileWriter;
import java.io.IOException;

import general.Particle;

public class FileProcessor {
    /**
     * Writes all Particles' properties to a file with the specified path.
     *
     * @param path  The path of the output file.
     * @return {@code true} If the write was completed successfully, {@code false} on error.
     * @throws IOException If the write stream can't be closed.
     */
    public static boolean writeOutputParticlesFile(Particle particle, String path){
        boolean success = true;
        FileWriter w = null;
        try {
            w = new FileWriter(path, true);
            w.write(String.valueOf(245));
            w.write(System.getProperty("line.separator"));
            w.write(System.getProperty("line.separator"));
            printBorders(10, path);
            writeParticle(particle, w, 255, 0, 0);
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
        w.write(String.valueOf(p.getX() + " "));
        w.write(String.valueOf(0) + " ");
        w.write(String.valueOf(0) + " ");
        w.write(String.valueOf(1) + " ");
        w.write(String.valueOf(r) + " ");
        w.write(String.valueOf(g) + " ");
        w.write(String.valueOf(b) + " ");
        w.write(System.getProperty("line.separator"));
    }

    public static void printBorders(final double L, String path) {
        FileWriter w = null;
        try {
            w = new FileWriter(path, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (double i = -L; i <= L; i += L/30) {
            try{
                writeBorder(i, L, 0.1, w, 255, 255, 255);
                writeBorder(L, i, 0.1, w, 255, 255, 255);
                writeBorder(i, -L, 0.1, w, 255, 255, 255);
                writeBorder(-L, i, 0.1, w, 255, 255, 255);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
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
