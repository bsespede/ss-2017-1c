package Simulator;

import general.Cell;
import general.Direction;
import general.Particle;
import io.FileProcessor;

import java.util.HashSet;
import java.util.Set;

public class Simulation {

    private Cell[][] cells;
    private Set<Particle> particles = new HashSet<>();
    private long iterations;

    public Simulation(int height, int width, int l) {
        cells = new Cell[height][width];
        int lx = (int) (Math.random() * (height - l - 1 ) )+ height % 5;
        int ly = (int) (Math.random() * width) + width % 5;
        for(int i = 0 ; i < height ; i++){
            for (int j = 0; j < width; j++) {
                if(i == 0 || i == height - 1){
                    cells[i][j] = new Cell(true);
                }else{
                    if(j == ly && ( i >= lx && i < lx + l)){
                        cells[i][j] = new Cell(true);
                    }else{
                        cells[i][j] = new Cell(false);
                    }
                }
            }
        }
    }

    public void simulate(int n){
        for (int i = 0; i < n; i++) {
            moveParticles();
            checkCollisions();
            if(i % 4 == 0){
                addParticles();
            }
            FileProcessor.outputState(cells, particles,"./output.txt");
        }
    }

    public void addParticles(){
        for (int i = 1; i < cells[0].length; i++) {
            if(!cells[0][i].isSolid()){
                cells[0][i].getParticles().add(new Particle( 0 , i, particles.size(), Direction.UR));
                cells[0][i].getParticles().add(new Particle( 0 , i, particles.size(), Direction.R));
                cells[0][i].getParticles().add(new Particle( 0 , i, particles.size(), Direction.BR));
            }
        }
    }

    public void checkCollisions(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if(cells[i][j].getParticles().size() != 0){
                    Particle.resolveCollision(cells[i][j].getParticles().toArray(new Particle[cells[i][j].getParticles().size()]));
                }
            }
        }
    }

    public void moveParticles(){
        for(Particle p : particles){
            cells[p.getX()][p.getY()].getParticles().remove(p);
            if(p.getX() + p.getDir().getDirx() <= 0 || p.getX() + p.getDir().getDirx() >= cells.length){
                particles.remove(p);
                return;
            }
            if(cells[p.getX() + p.getDir().getDirx()][p.getY() + p.getDir().getDiry()].isSolid()){
                if(cells[p.getX()][p.getY() + p.getDir().getDiry()].isSolid() && cells[p.getX() + p.getDir().getDirx()][p.getY()].isSolid()){
                    p.invertX();
                    p.invertY();
                }else if (cells[p.getX() + p.getDir().getDirx()][p.getY()].isSolid()) {
                    p.invertX();
                }else if(cells[p.getX()][p.getY() + p.getDir().getDiry()].isSolid()){
                    p.invertY();
                }else{
                    p.invertX();
                    p.invertY();
                }
            }else{
                p.setX(p.getX() + p.getDir().getDirx());
                p.setY(p.getY() + p.getDir().getDiry());
            }
            cells[p.getX()][p.getY()].getParticles().add(p);
        }
    }

    public void printTestCells(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(cells[i][j].isSolid()){
                    System.out.print(" 1 ");
                }else{
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }
    }
}
