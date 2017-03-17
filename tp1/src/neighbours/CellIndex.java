package neighbours;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import particle.Particle;

public class CellIndex implements Neighbours {

	private final Cell[] cells;
	private final boolean contour;
	private final float L;
	private final int M;

	public CellIndex(final Set<Particle> particles, final boolean contour, final int M) {
		this.contour = contour;
		this.L = particles.size();
		this.M = M;
		this.cells = new Cell[M * M];
		for (int i = 0; i < M * M; i++) {
			cells[i] = new Cell();
		}
		for (Particle particle: particles) {
			addParticle(particle);
		}
	}

	public void addParticle(final Particle particle) {
		int minRow = (int) (((particle.getX() - particle.getRadius()) / L) * M);
		int minCol = (int) (((particle.getY() - particle.getRadius()) / L) * M);
		int maxRow = (int) (((particle.getX() + particle.getRadius()) / L) * M);
		int maxCol = (int) (((particle.getY() + particle.getRadius()) / L) * M);

		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				if (contour) {
					int fixedI = (i < 0) ? M - 1 : (i > M) ? 0 : i;
					int fixedJ = (j < 0) ? M - 1 : (j > M) ? 0 : j;
					cells[fixedI * M + fixedJ].addParticle(particle);
				} else {
					if (i >= 0 && i < M && j >= 0 && j < M) {
						cells[i * M + j].addParticle(particle);
					}
				}
			}
		}
	}

	public Map<Particle, Set<Particle>> getNeighbours() {
		final Map<Particle, Set<Particle>> neighbourMap = new HashMap<Particle, Set<Particle>>();

		for (Cell cell: cells) {
			for (Particle particle: cell.getParticles()) {
				Set<Particle> neighbours = neighbourMap.get(particle);
				
				if (neighbours == null) {
					neighbours = new HashSet<Particle>();
					neighbourMap.put(particle, neighbours);
				}

				// Test neighbour cells
				int minRow = (int) (((particle.getX() - (particle.getInteractionRadius() + particle.getRadius())) / L) * M)	;
				int minCol = (int) (((particle.getY() - (particle.getInteractionRadius() + particle.getRadius())) / L) * M);
				int maxRow = (int) (((particle.getX() + (particle.getInteractionRadius() + particle.getRadius())) / L) * M);
				int maxCol = (int) (((particle.getY() + (particle.getInteractionRadius() + particle.getRadius())) / L) * M);				
				
				for (int i = minRow; i <= maxRow; i++) {
					for (int j = minCol; j <= maxCol; j++) {
						if (contour) {
							int fixedI = (i < 0) ? M - 1 : (i >= M) ? 0 : i;
							int fixedJ = (j < 0) ? M - 1 : (j >= M) ? 0 : j;
							testParticleAgainstCell(particle, fixedI, fixedJ, neighbours);
						} else {
							if (i >=0 && i < M && j >= 0 && j < M) {
								testParticleAgainstCell(particle, i, j, neighbours);
							}
						}
					}
				}
			}
		}

		return neighbourMap;
	}

	private void testParticleAgainstCell(Particle particle, int i, int j, Set<Particle> neighbours) {
		Cell testCell = cells[i * M + j];
		
		for (Particle testParticle: testCell.getParticles()) {
			if (particle.isNeighbour(testParticle) && !particle.equals(testParticle)) {
				neighbours.add(testParticle);
			}
		}
		
	}

}
