package simulation;


import cellIndexMethod.method.CellIndex;
import general.ParticleCollision;
import general.WallCollision;
import io.FileProcessor;
import general.Collision;
import general.Particle;

import java.io.IOException;
import java.sql.Time;
import java.text.CollationElementIterator;
import java.util.*;

public class Simulator {

    Set<Particle> particles = new HashSet<>();
    double currentTime = 0;
    final int L;
    long totalTime;
    double maxV;

    public Simulator(int n, final int L, double mass, double radius, double fluidPMass, double fluidPRadius, long totalTime, double maxV){
        this.L = L;
        this.totalTime = totalTime;
        this.maxV = maxV;
        particles.add(new Particle(0, mass, 0, 0, 0, 0, radius, 0));
//        generateParticles(n, fluidPMass, fluidPRadius);
        particles.add(new Particle(1, 1, 5 ,5 , -0.8, -0.8, 2, 0));
    }

    private void generateParticles(int n, double fluidPMass, double fluidPRadius) {
        while(particles.size() != n){
            double x = (Math.random()* L - fluidPRadius) - (L - fluidPRadius)/2;
            double y = (Math.random()* L - fluidPRadius) - (L - fluidPRadius)/2;
            Particle p = new Particle(particles.size(), fluidPMass, x, y, Math.random()* 2 * maxV - maxV, Math.random()* 2 * maxV - maxV, fluidPRadius, 0);
            if(!overlaps(p, particles)){
                particles.add(p);
            }
        }
    }

    public void simulate() throws IOException {
        long initialTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while(currentTime - initialTime < totalTime){
            Collision c = calculateNextCollision();
            moveToNextT(c.getT());
            c.resolveCollision();
            FileProcessor.writeOutputParticlesFile(particles, "./output.txt");
            currentTime = System.currentTimeMillis();
        }


    }

    private void moveToNextT(double t) {
        for (Particle p : particles){
            p.move(t);
        }
    }

    private Collision calculateNextCollision() {
        double t = Double.POSITIVE_INFINITY;
        double aux;
        boolean isWallCollision = false;
        Particle collisionP1 = null, collisionP2 = null;
        for(Particle p1: particles){
            for(Particle p2: particles){
                if(!p1.equals(p2)){
                    aux = p1.getParticleCollisionTime(p2);
                    if(aux < t){
                        t = aux;
                        collisionP1 = p1;
                        collisionP2 = p2;
                        isWallCollision = false;
                    }
                }
            }
            aux = p1.getWallCollisionTime(L);
            if(aux < t ){
                t = aux;
                collisionP1 = p1;
                isWallCollision = true;
            }
        }
        Collision c;
        if(isWallCollision){
            c = new WallCollision(collisionP1, t);
        }else{
            c = new ParticleCollision(collisionP1, collisionP2, t);
        }
        return c;
    }

    public void generationPrint() throws IOException {
        FileProcessor.writeOutputParticlesFile(particles, "./generationTest.txt");
    }

    public boolean overlaps(Particle newP, Collection<Particle> particles){
        for(Particle p : particles){
            if(Math.sqrt(Math.pow(p.getX() - newP.getX(),2) + Math.pow(p.getY() - newP.getY(),2)) <= newP.getRadius() + p.getRadius()){
                return true;
            }
        }
        return false;
    }
}
