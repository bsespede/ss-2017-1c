package general;


import java.util.HashSet;
import java.util.Set;

public class Cell {

    private Set<Particle> particles;
    private boolean isSolid;
    private boolean isWall;
    private boolean isEdge;
    private long particlesFlowed = 0;

    public Cell(boolean isSolid, boolean isWall, boolean isEdge) {
        this.isSolid = isSolid;
        this.isWall = isWall;
        this.isEdge = isEdge;
        if(!isSolid){
            particles = new HashSet<>();
        }
    }

    public Set<Particle> getParticles() {
        return particles;
    }


    public void setParticles(Set<Particle> particles) {
        this.particles = particles;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public void setEdge(boolean edge) {
        isEdge = edge;
    }

    public long getParticlesFlowed() {
        return particlesFlowed;
    }

    public void setParticlesFlowed(long particlesFlowed) {
        this.particlesFlowed = particlesFlowed;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean isEdge() {
        return isEdge;
    }

	public int size() {
		return particles.size();
	}
}
