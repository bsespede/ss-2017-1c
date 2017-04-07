package simulation;


import cellIndexMethod.method.CellIndex;
import io.FileProcessor;
import general.Collision;
import general.Particle;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Simulator {

    Set<Particle> particles = new HashSet<>();
    double currentTime = 0;
    final int L;
    double totalTime;
    double maxV;

    public Simulator(int n, final int L, double mass, double radius, double fluidPMass, double fluidPRadius, double totalTime, double maxV){
        this.L = L;
        this.totalTime = totalTime;
        this.maxV = maxV;
        particles.add(new Particle(0, mass, 0, 0, 0, 0, radius, 0));
        generateParticles(n, fluidPMass, fluidPRadius);
    }

    private void generateParticles(int n, double fluidPMass, double fluidPRadius) {
        while(particles.size() != n){
            double x = (Math.random()* L - fluidPRadius) - (L - fluidPRadius)/2;
            double y = (Math.random()* L - fluidPRadius) - (L - fluidPRadius)/2;
            Particle p = new Particle(particles.size(), fluidPMass, x, y, Math.random()* 2 * maxV - maxV, Math.random()* 2 * maxV - maxV, fluidPRadius, 0);
            particles.add(p);
            Map<Particle,Set<Particle>> neighbours = new CellIndex(L, particles, 1).getNeighbours();
            if(neighbours.get(p).size() > 0){
                particles.remove(p);
            }
        }
    }

    public void simulate(){
        Collision c = calculateNextCollision();
        moveToNextT(c.getT());
    }

    private void moveToNextT(double t) {
        for (Particle p : particles){
            p.move(t);
        }
    }

    private Collision calculateNextCollision() {
        double t = Double.POSITIVE_INFINITY;
        double aux;
        Particle collisionP1, collisionP2;
        for(Particle p1: particles){
            for(Particle p2: particles){
                if(!p1.equals(p2)){
                    aux = p1.getParticleCollisionTime(p2);
                    if(aux < t){
                        t = aux;
                        collisionP1 = p1;
                        collisionP2 = p2;
                    }
                }
            }
            aux = p1.getWallCollisionTime(L);
            t = aux < t ? aux : t;
        }
        return t;
    }

    public void generationPrint() throws IOException {
        FileProcessor.writeOutputParticlesFile(particles, "./generationTest.txt");
    }
}
