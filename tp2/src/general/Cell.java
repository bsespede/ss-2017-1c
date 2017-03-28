package general;


import java.util.HashSet;
import java.util.Set;

public class Cell {

    private Set<Particle> particles;
    private boolean isSolid;
    boolean isWall;
    boolean isEdge;

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
