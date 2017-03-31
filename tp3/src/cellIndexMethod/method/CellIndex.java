package cellIndexMethod.method;


import cellIndexMethod.Cell;
import cellIndexMethod.Simulation;
import particle.Particle;

import java.util.HashSet;
import java.util.Set;

public class CellIndex extends Simulation {

	private final Cell[] cells;
	private final int M;


	public CellIndex(final int L, final Set<Particle> particles, final int M) {
		super(L, particles);
		this.M = M;
		this.cells = new Cell[M * M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				cells[i * M + j] = new Cell();
			}
		}
		populateCells();
	}

	private void populateCells() {		
		for (Particle particle: particles) {
			int row = (int) Math.floor((particle.getX()  / L) * M);
			int col = (int) Math.floor((particle.getY() / L) * M);
			
			for (int i = row - 1; i <= row + 1; i++) {
				for (int j = col - 1; j <= col + 1; j++) {
					cells[i * M + j].addParticle(particle);
				}
			}
		}
	}

	@Override
	protected Set<Particle> getNeighbourCandidates(final Particle particle) {
		Set<Particle> candidates = new HashSet<Particle>();
		int row = (int) Math.floor((particle.getX()  / L) * M);
		int col = (int) Math.floor((particle.getY() / L) * M);
		
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				candidates.addAll(cells[i * M + j].getParticles());
			}
		}
		return candidates;
	}
	
}
