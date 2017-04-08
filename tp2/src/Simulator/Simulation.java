package Simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import general.Direction;
import general.Node;
import general.Particle;
import io.FileProcessor;

public class Simulation {

	private final List<Particle> particles;
    private final Node[][] nodes;
    private final String outputName;

    public Simulation(final int height, final int width, final int l, final String outputName) {
    	this.particles = new LinkedList<>();
    	this.nodes = new Node[width + 1][height + 1];
    	this.outputName = outputName;
    	final double L = l * height / 100d;
        final int wallMinY = (int) (Math.floor(height / 2.0 - L / 2.0));
        final int wallMaxY = (int) (Math.ceil(height / 2.0 + L / 2.0));
        final int wallX = (int) (Math.ceil(width * 0.2));
        
        for(int i = 0 ; i < nodes.length ; i++){
            for (int j = 0; j < nodes[0].length; j++) {
                if((i == wallX && j >= wallMinY && j <= wallMaxY) || j == 0 || j == nodes[0].length - 1){
                    nodes[i][j] = new Node(true);
                }else{
                	nodes[i][j] = new Node(false);
                }
            }
        }
    }

    public void simulate(final int steps) {
    	System.out.println("Started simulation");
    	try {
			Files.createDirectories(Paths.get("./outputs/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	final List<Integer> collisionHistory = new ArrayList<>(steps);
    	final List<Double> flowHistory = new ArrayList<>(steps);
    	final long time = System.currentTimeMillis();
    	
        for (int step = 0; step < steps; step++) {
        	System.out.println("At step: "+ step);
        	if(step % 4 == 0){
        		generateParticles();
        	}
            moveParticles();
            collisionHistory.add(calculateCollisions());
            flowHistory.add(calculateFlow());
            FileProcessor.outputState(this, "./outputs/"+ outputName +"-"+ step + ".txt");
        }
        
        final float finalTime = (System.currentTimeMillis() - time) / 1000f;
        FileProcessor.outputData(collisionHistory, flowHistory, finalTime, "./outputs/"+ outputName +"-data.txt");
        System.out.println("Finished simulation");
    }

    public void generateParticles(){
        for (int i = 0; i < nodes[0].length; i++) {
            if(!nodes[0][i].isSolid()){         	
                final Particle p1 = new Particle(0, i, Direction.UR);
                final Particle p2 = new Particle(0, i, Direction.R);
                final Particle p3 = new Particle(0, i, Direction.BR);
                nodes[0][i].addParticle(p1);
                nodes[0][i].addParticle(p2);
                nodes[0][i].addParticle(p3);
                particles.add(p1);                
                particles.add(p2);                
                particles.add(p3);
            }
        }
    }

    public int calculateCollisions() {
    	int collisions = 0;
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
            	if (nodes[i][j].resolveCollision()) {
            		collisions++;
            	}
            }
        }
        return collisions;
    }
    
    public double calculateFlow() {
    	double x = 0, y = 0, area = nodes.length * nodes[0].length;
    	for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
            	double[] vector = nodes[i][j].getVelocityVector();
            	x += vector[0]; 
            	y += vector[1];
            }
        }
    	area = nodes.length * nodes[0].length;
    	x /= nodes.length * nodes[0].length;
    	y /= nodes.length * nodes[0].length;
    	return Math.sqrt(x * x + y * y) * area;
    }

    public void moveParticles() {
        List<Particle> toRemove = new LinkedList<>();
        for (Particle particle: particles) {
            Direction direction = particle.getDirection();
            int x = particle.getX(); 
            int y = particle.getY();
            
            nodes[x][y].removeParticle(particle);
            
            if(nodes[x][y].isSolid()){
                direction = direction.reverse();
            }
            
            x += direction.getX();
            y += direction.getY();
                        
            if(x < 0 || x >= nodes.length || y < 0 || y >= nodes[0].length) {
                toRemove.add(particle);
            } else {
            	particle.setX(x);
            	particle.setY(y);
            	particle.setDirection(direction);
            	nodes[x][y].addParticle(particle); 
            }
        }
        particles.removeAll(toRemove);
    }

	public int getNodesNumber() {
		return nodes.length * nodes[0].length;
	}

	public Node[][] getNodes() {
		return nodes;
	}
    
}
