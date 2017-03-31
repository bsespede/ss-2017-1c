package simulation.method;

import java.util.LinkedList;
import java.util.List;

import neighbours.Cell;
import particle.Particle;
import simulation.Simulation;

public class CellIndex extends Simulation {

	private final Cell[] cells;
	private final int M;

	public CellIndex(final String staticInput, final String dynamicInput, final boolean contour, final int M) {
		super(staticInput, dynamicInput, contour);
		this.M = M;
		this.cells = new Cell[M * M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				cells[i * M + j] = new Cell();
			}
		}
		populateCells();
	}

	public CellIndex(final int L, final int particlesNumber, final boolean contour, final double radius, final double  interactionRadius, final int M) {
		super(L, particlesNumber, contour, radius, interactionRadius);
		this.M = M;
		this.cells = new Cell[M * M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				cells[i * M + j] = new Cell();
			}
		}
		populateCells();
		if (interactionRadius + radius >= L/ M) {
			throw new IllegalStateException("rc+r < L/M");
		}
	}

	private void populateCells() {		
		for (Particle particle: particles) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
//					float minX = M * i;
//					float maxX = M * (i + 1);
//					float minY = M * j;
//					float maxY = M * (j + 1);
//					if (particle.getX() < maxX && particle.getX() > minX && particle.getY() > minY && particle.getY() < maxY) {
//						cells[i * M + j].addParticle(particle);						
//					}
				}
			}
			
			int row = (int) Math.floor((particle.getX()  / L) * M);
			int col = (int) Math.floor((particle.getY() / L) * M);
			
			for (int i = row - 1; i <= row + 1; i++) {
				for (int j = col - 1; j <= col + 1; j++) {
					if (contour) {
						int fixedI = (i % M < 0)? i % M + M : i % M;
						int fixedJ = (j % M < 0)? j % M + M : j % M;
						cells[fixedI * M + fixedJ].addParticle(particle);
					} else if (i >= 0 && i < M && j >= 0 && j < M) {
						cells[i * M + j].addParticle(particle);
					}
				}
			}
		}
	}

	@Override
	protected List<Particle> getNeighbourCandidates(final Particle particle) {
		List<Particle> candidates = new LinkedList<Particle>();
		int row = (int) Math.floor((particle.getX()  / L) * M);
		int col = (int) Math.floor((particle.getY() / L) * M);
		
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (contour) {
					int fixedI = (i % M < 0)? i % M + M : i % M;
					int fixedJ = (j % M < 0)? j % M + M : j % M;
					candidates.addAll(cells[fixedI * M + fixedJ].getParticles());
				} else if (i >= 0 && i < M && j >= 0 && j < M) {
					candidates.addAll(cells[i * M + j].getParticles());
				}
			}
		}
		return candidates;
	}
	
}
