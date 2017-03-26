package general;


import java.util.HashSet;
import java.util.Set;

public class Cell {

    private Set<Particle> particles;
    private boolean isSolid;
    private boolean r;

    public Cell(boolean isSolid) {
        this.isSolid = isSolid;
        if(!isSolid){
            particles = new HashSet<>();
        }
        updateR();
    }

    public Set<Particle> getParticles() {
        return particles;
    }

    public void setParticles(Set<Particle> particles) {
        this.particles = particles;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public void updateR(){
       r = Math.random() >= 0.5 ? true : false;
    }
}
