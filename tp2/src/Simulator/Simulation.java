package Simulator;

import java.util.LinkedList;
import java.util.List;

import general.Direction;
import general.Node;
import general.Particle;
import io.FileProcessor;

public class Simulation {

	private final List<Particle> particles;
    private final Node[][] nodes;

    public Simulation(final int height, final int width, final int l) {
    	this.particles = new LinkedList<>();
    	this.nodes = new Node[width + 1][height + 1];
    	
        final int wallMinY = (int) (Math.floor(height / 2.0 - l / 2.0));
        final int wallMaxY = (int) (Math.ceil(height / 2.0 + l / 2.0));
        final int wallX = (int) (Math.ceil(width * 0.8));
        
        for(int i = 0 ; i < nodes.length ; i++){
            for (int j = 0; j < nodes[0].length; j++) {
                if(i == wallX && j >= wallMinY && j <= wallMaxY){
                    nodes[i][j] = new Node(true);
                }else{
                	nodes[i][j] = new Node(false);
                }
            }
        }
    }

    public void simulate(final int steps) {    	
        for (int step = 0; step < steps; step++) {
            moveParticles();
            resolveCollisions();
            if(step % 4 == 0){
            	generateParticles();
            }
            FileProcessor.outputState(this, "./outputs/output"+ step + ".txt");
        }
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

    public void resolveCollisions() {
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
            	nodes[i][j].resolveCollision();
            }
        }
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
