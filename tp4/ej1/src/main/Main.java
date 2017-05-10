package main;


import methods.*;
import simulator.Oscillator;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        double m = 70;
        double deltaT;
        double initialDeltaT = 0.00005;
        double maxDeltaT = 0.001;
        int iterations = 31;

        for (int i = 0; i < iterations; i++) {
            deltaT = initialDeltaT + ((maxDeltaT - initialDeltaT)/iterations * i);
            Method verlet = new Verlet(deltaT);
            Method beeman = new Beeman(deltaT);
            Method gear = new Gear(deltaT);
            Method analitic = new Analitic(Oscillator.k, Oscillator.g);

            Oscillator verletOscillator = new Oscillator(verlet, m, deltaT);
            Oscillator beemanOscillator = new Oscillator(beeman, m, deltaT);
            Oscillator gearOscillator = new Oscillator(gear, m, deltaT);
            Oscillator analiticOscillator = new Oscillator(analitic, m, deltaT);

            verletOscillator.setPrevState();
            beemanOscillator.setPrevState();
            gearOscillator.initializeR();

            List<Oscillator> l = new LinkedList<>();
            l.add(verletOscillator);
            l.add(beemanOscillator);
            l.add(gearOscillator);
            Oscillator.run(analiticOscillator, l, deltaT);
        }
    }
}
