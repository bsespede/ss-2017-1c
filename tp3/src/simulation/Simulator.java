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
    final double L;
    long totalTime;
    double maxV;
    String path= "./output150.txt";
    String pathTemp= "./outputTemp.txt";
    double timeForJump = 2;
    double k = 1.38064852 * Math.pow(10,-23);
    Particle mainParticle;

    public Simulator(int n, final double L, double mass, double radius, double fluidPMass, double fluidPRadius, long totalTime, double maxV){
        this.L = L;
        this.totalTime = totalTime;
        this.maxV = maxV;
        mainParticle = new Particle(0, mass, 0, 0, 0, 0, radius, 0);
        particles.add(mainParticle);
        generateParticles(n, fluidPMass, fluidPRadius);
    }

    private void generateParticles(int n, double fluidPMass, double fluidPRadius) {
        while(particles.size() != n){
            double x = (Math.random()* L - fluidPRadius) * 2 - (L - fluidPRadius);
            double y = (Math.random()* L - fluidPRadius) * 2 - (L - fluidPRadius);
            Particle p = new Particle(particles.size(), fluidPMass, x, y, Math.random()* 2 * maxV - maxV, Math.random()* 2 * maxV - maxV, fluidPRadius, 0);
            if(!overlaps(p, particles)){
                particles.add(p);
            }
        }
    }

    public void simulate(){
        long simulationInitTime = System.currentTimeMillis();
        long simulationCurrTime = System.currentTimeMillis();
        Collision c = calculateNextCollision();
        while(simulationCurrTime - simulationInitTime < totalTime){
            if(c.getT() >= timeForJump){
                moveToNextT(timeForJump);
                c.setT(c.getT() - timeForJump);
                FileProcessor.writeOutputParticlesFile(particles, path);
                FileProcessor.printBorders(L, pathTemp);
                FileProcessor.writeParticlePosition(mainParticle,pathTemp);
            }else{
                double timeSinceLastJump = 0;
                while(c.getT() + timeSinceLastJump < timeForJump){
                    moveToNextT(c.getT());
                    c.resolveCollision();
                    timeSinceLastJump += c.getT();
                    c = calculateNextCollision();
                }
                moveToNextT(timeForJump - timeSinceLastJump);
                c.setT(c.getT() - (timeForJump - timeSinceLastJump));
                FileProcessor.writeOutputParticlesFile(particles, path);
                FileProcessor.printBorders(L, pathTemp);
                FileProcessor.writeParticlePosition(mainParticle,pathTemp);

            }
            simulationCurrTime = System.currentTimeMillis();
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

    private double getTemperature(Collection<Particle> ps) {
        double ret = 0;
        for (Particle p : ps) {
            double squaredV = Math.pow(p.getVx(), 2) + Math.pow(p.getVy(), 2);
            ret += (squaredV * p.getMass() / 1000) / k;
        }
        return ret;
    }
}
