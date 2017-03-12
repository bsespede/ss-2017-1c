package main;

import general.Particle;
import io.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Julian on 3/11/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Set<Particle> s = new HashSet<>(FileProcessor.processInputFile("./input.txt"));

        Map<Particle,Set<Particle>> m = new HashMap<>(); //TODO get neighbours

        FileProcessor.writeOutputFile(m,"./output.txt");

    }




}
