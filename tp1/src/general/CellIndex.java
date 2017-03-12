package general;

import java.util.HashSet;
import java.util.Set;

public class CellIndex {
	
	private final Cell[] cells;
	private final boolean contour;
	private final float L;
	private final int M;
	
	public CellIndex(final boolean contour, final float L, final int M) {
		this.contour = contour;
		this.L = L;
		this.M = M;
		this.cells = new Cell[M * M];
		for (int i = 0; i < M * M; i++) {
			cells[i] = new Cell();
		}
	}
	
	public void addParticle(final Particle particle) {
		final int minRow = (int) (L / (particle.getX() - particle.getRadius()));
		final int minCol = (int) (L / (particle.getY() - particle.getRadius()));
		final int maxRow = (int) (L / (particle.getX() + particle.getRadius()));
		final int maxCol = (int) (L / (particle.getY() + particle.getRadius()));
		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				cells[i * M + j].addParticle(particle);
			}
		}
	}
	
	public int[] getNeighboursIndex(final Particle particle) {
		//TODO: calculate test cells
		return null;
		
	}
	
	public Set<Particle> getNeighbours(final Particle particle) {
		final Set<Particle> neighbours = new HashSet<Particle>();
		
		for (int index: getNeighboursIndex(particle)) {
			neighbours.addAll(cells[index].getValidNeighbours(particle));
		}
		
		return neighbours;
	}

}
