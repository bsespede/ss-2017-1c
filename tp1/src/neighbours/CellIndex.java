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
		final int minRow = (int) ((particle.getX() - particle.getRadius() / L)) * M;
		final int minCol = (int) ((particle.getY() - particle.getRadius() / L)) * M;
		final int maxRow = (int) ((particle.getX() + particle.getRadius() / L)) * M;
		final int maxCol = (int) ((particle.getY() + particle.getRadius() / L)) * M;

		// Add the particle only to the cells where the border of the particle lies for efficiency
		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				if (i == minRow && j <= maxCol) {
					cells[i * M + j].addParticle(particle);					
				} else if (i == maxRow && j <= maxCol) {
					cells[i * M + j].addParticle(particle);						
				} else if (j == minCol && i < maxRow && i > minRow) {
					cells[i * M + j].addParticle(particle);
				} else if (j == maxCol && i < maxRow && i > minRow) {
					cells[i * M + j].addParticle(particle);
				}
			}
		}
	}

	public Map<Particle, Set<Particle>> getNeighbours() {
		final Map<Particle, Set<Particle>> neighbourMap = new HashMap<Particle, Set<Particle>>();

		for (Cell cell: cells) {
			for (Particle particle: cell.getParticles()) {
				final Set<Particle> neighbours = new HashSet<Particle>();

				// First add all the particles in the same cell, they are neighbours for sure because rc < L/M
				for (Particle particleInCell: cell.getParticles()) {
					if (!particleInCell.equals(particle)) {
						neighbours.add(particleInCell);
					}
				}

				// Test neighbour cells
				final int minRow = (int) ((particle.getX() - particle.getIntegrationRadius() / L)) * M - 1;
				final int minCol = (int) ((particle.getY() - particle.getIntegrationRadius() / L)) * M - 1;
				final int maxRow = (int) ((particle.getX() + particle.getIntegrationRadius() / L)) * M + 1;
				final int maxCol = (int) ((particle.getY() + particle.getIntegrationRadius() / L)) * M + 1;

				if (contour) {
					for (int i = minRow; i <= maxRow; i++) {
						for (int j = minCol; j <= maxCol; j++) {
							// Only test on neighbour cells
							if (i == minRow && j <= maxCol) {
								cells[(i % M) * M + (j % M)].addParticle(particle);					
							} else if (i == maxRow && j <= maxCol) {
								cells[(i % M) * M + (j % M)].addParticle(particle);						
							} else if (j == minCol && i < maxRow && i > minRow) {
								cells[(i % M) * M + (j % M)].addParticle(particle);
							} else if (j == maxCol && i < maxRow && i > minRow) {
								cells[(i % M) * M + (j % M)].addParticle(particle);
							}
						}
					}
				} else {
					for (int i = minRow; i <= maxRow; i++) {
						for (int j = minCol; j <= maxCol; j++) {
							if (i >= 0 && j >= 0 && i < M && j < M){	
								// Only test on neighbour cells
								if (i == minRow && j <= maxCol) {
									neighbours.addAll(cells[i * M + j].getValidNeighbours(particle));				
								} else if (i == maxRow && j <= maxCol) {
									neighbours.addAll(cells[i * M + j].getValidNeighbours(particle));					
								} else if (j == minCol && i < maxRow && i > minRow) {
									neighbours.addAll(cells[i * M + j].getValidNeighbours(particle));
								} else if (j == maxCol && i < maxRow && i > minRow) {
									neighbours.addAll(cells[i * M + j].getValidNeighbours(particle));
								}
							}
						}
					}
				}				

				// Add the set to the map				
				neighbourMap.put(particle, neighbours);
			}
		}

		return neighbourMap;
	}

}
