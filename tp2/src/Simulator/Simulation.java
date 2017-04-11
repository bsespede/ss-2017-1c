package Simulator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import general.VelocityConstants;
import io.FileProcessor;

public class Simulation {

	private int[][][] nodes;
	private final int[][] obstacles;
	private final int width, height, L, grainSize, velocityTimeStep;
	private final String output;

	public Simulation(final int width, final int height, final int L, final int grainSize, final int velocityTimeStep, final String output) {
		//Arreglo 3D con posiciones y estados de la celda segun la dir
		this.nodes = new int[width][height][6];
		this.obstacles = new int[width][height];
		this.width = width;
		this.height = height;
		this.L = L;
		this.grainSize = grainSize;
		this.velocityTimeStep = velocityTimeStep;
		this.output = output;

		// Pongo mis obstaculos
		final int wallMinY = (int) (Math.floor(height / 2.0 - L / 2.0));
		final int wallX = (int) (Math.ceil(width * 0.2));

		for(int i = 0 ; i < width ; i++){
			for (int j = 0; j < height; j++) {
				if (i == wallX && j >= wallMinY && j < wallMinY + L) {
					obstacles[i][j] = 1;            		
				} else if (j == 0 || j == height - 1) {
					obstacles[i][j] = 1;
				}
			}
		}  
		
		//Make dirs for output
		File files = new File("./output/" + output + "/");
		files.mkdirs();
	}

	public void simulate(final int steps) {
		System.out.println("[INFO] Comienza la simulacion");
		Map<Integer, Double> collisionVelocity = new LinkedHashMap<>();
		int collisionCounter = 0;
		final long startTime = System.currentTimeMillis();
		for (int t = 0; t < steps; t++) {
			collisionCounter += solveCollisions();
			moveParticles();
			if (t % 4 == 0) {
				generateParticles();
			}
			// Cada tanto imprimo el t para ver que no se trabo
			// Y calculo las velocidades de los subdominios
			if (t % velocityTimeStep == 0) {
				System.out.println("[INFO] En el paso " + t);
				double[][] velocities = calculateVelocities();
				double velocityMagnitude = avgVelocityMagnitude(velocities);
				collisionVelocity.put(collisionCounter, velocityMagnitude);
				FileProcessor.outputSimulation(obstacles, velocities, grainSize,  "./output/" + output + "/" + output + "-" + t +".txt");
			}   
		}
		FileProcessor.outputSimulation(collisionVelocity,  "./output/" + output + "/" + output + "-cv.txt");
		final double simulationTime = (System.currentTimeMillis() - startTime) / 1000d;
		System.out.println("[INFO] Re = "+ calculateReynoldNumber());
		System.out.println("[INFO] Q = "+ calculateFlow());
		System.out.println("[INFO] Ha finalizado la simulacion en "+ simulationTime + "segundos");
	}

	private double calculateReynoldNumber() {
		double density = calculateDensity();
		System.out.println("D: "+density);
		double viscosity = calculateViscosity(density);
		System.out.println("V: "+viscosity);
		double g = calculateGFactor(density);
		System.out.println("G: "+g);
		return g * L * avgVelocityMagnitude() / viscosity;
	}

	private double avgVelocityMagnitude() {
		return avgVelocityMagnitude(calculateVelocities());
	}
	
	private double avgVelocityMagnitude(double[][] velocities) {
		double totalMagnitude = 0;
		for (int i = 0; i < velocities.length; i++) {
			totalMagnitude += velocities[i][2];
		}
		return totalMagnitude / velocities.length;
	}

	private double calculateGFactor(double density) {
		return (0.5d) * ((1 - 2 * density) / (1 - density));
	}

	private double calculateViscosity(double density) {
		double negDensityCubed = Math.pow(1 - density, 3);
		return (1 / 12d) * (1 / (density * negDensityCubed)) - (1 / 8d);		
	}

	private double calculateDensity() {
		double totalDensity = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				totalDensity += sum(nodes[i][j]);
			}
		}
		return (totalDensity / (width * height)) / 6;
	}

	private double calculateFlow() {
		return avgVelocityMagnitude() * (Math.PI * L * L / 4);
	}

	private int solveCollisions() {
		int collisionCount = 0;
		// Chequeo colisiones excepto en paredes y obstaculos
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (obstacles[i][j] == 0) {
					int[] curCell = nodes[i][j];
					// Chequear si hay colisiones
					int sum = sum(curCell);
					// Caso 3 particulas colisionan
					if (sum == 3) {
						if (curCell[0] == curCell[2] && curCell[2] == curCell[4]) {
							invertCell(i, j);
							collisionCount++;
						} else if (curCell[1] == curCell[3] && curCell[3] == curCell[5]) {
							invertCell(i, j);
							collisionCount++;
						}
						// Caso 2 particulas colisionan
					} else if (sum == 2) {
						// Me fijo si la de enfrente es la otra ocupada
						int p1 = first(curCell);
						if (p1 < 3 && curCell[p1 + 3] == 1) {
							// Giro ambas clockwise o counterclockwise de manera random
							collisionCount++;
							int[] newCell = new int[6];
							if (Math.random() > 0.5) {
								// Clockwise
								newCell[0] = curCell[5];
								newCell[1] = curCell[0];
								newCell[2] = curCell[1];
								newCell[3] = curCell[2];
								newCell[4] = curCell[3];
								newCell[5] = curCell[4];
							} else {
								// CounterClockwise
								newCell[0] = curCell[1];
								newCell[1] = curCell[2];
								newCell[2] = curCell[3];
								newCell[3] = curCell[4];
								newCell[4] = curCell[5];
								newCell[5] = curCell[0];
							}
							nodes[i][j] = newCell;
						}
					}
				} else {
					// Colisiones con obstaculos/paredes
					nodes[i][j] = reverseCell(i, j);
				}
			}
		}
		return collisionCount;
	}

	private void moveParticles() {
		// Creo un nuevo tablero con las particulas despues de moverse
		int[][][] newNodes = new int[width][height][6];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Agarro la celda donde estoy parado y miro vecinos
				int[] curCell = nodes[i][j];
				int neighbourX, neighbourY;    				
				// Propago en dir R-1
				neighbourY = j;
				if (i != width - 1) {
					neighbourX = i + 1;
				} else {
					neighbourX = 0;
				}
				newNodes[neighbourX][neighbourY][0] = curCell[0];
				// Propago en dir UR-2
				if (j != height - 1) {
					neighbourY = j + 1;
					if (j % 2 == 0) {
						if (i != width - 1) {
							neighbourX = i + 1;
						} else {
							neighbourX = 0;
						}
					} else {
						neighbourX = i;
					}
					newNodes[neighbourX][neighbourY][1] = curCell[1];
				}
				// Propago en dir UL-3
				if (j != height - 1) {
					neighbourY = j + 1;
					if (j % 2 == 1) {
						if (i != 0) {
							neighbourX = i - 1;
						} else {
							neighbourX = width - 1;
						}
					} else {
						neighbourX = i;
					}
					newNodes[neighbourX][neighbourY][2] = curCell[2];
				}				
				// Propago en dir L-4
				if (i != 0) {
					neighbourY = j;
					neighbourX = i - 1;    					
					newNodes[neighbourX][neighbourY][3] = curCell[3];
				}
				// Propago en dir BL-5
				if (j != 0) {
					neighbourY = j - 1;
					if (j % 2 == 1) {
						if (i != 0) {
							neighbourX = i - 1;
						} else {
							neighbourX = width - 1;
						}
					} else {
						neighbourX = i;
					}
					newNodes[neighbourX][neighbourY][4] = curCell[4];
				}
				// Propago en dir BR-6
				if (j != 0) {
					neighbourY = j - 1;
					if (j % 2 == 0) {
						if (i != width - 1) {
							neighbourX = i + 1;
						} else {
							neighbourX = 0;
						}
					} else {
						neighbourX = i;
					}
					newNodes[neighbourX][neighbourY][5] = curCell[5];
				}
			}
		}
		// Updateo el tablero
		nodes = newNodes;
	}

	private double[][] calculateVelocities() {
		// Para calcular velocidad media uso coarse-graining
		int grainX = width / grainSize;
		int grainY = height / grainSize;

		double[][] velocities = new double[grainX * grainY][3];

		// Iterar sobre todo el dominio calculando los promedios
		int curVal = 0;
		for (int i = 0; i < grainX; i++) {
			// Calculo limites en x
			int xLower = i * grainSize;
			int xUpper = (i + 1) * grainSize;
			for (int j = 0; j < grainY; j++) {
				// Lo mismo, limites en y
				int yLower = j * grainSize;
				int yUpper = (j + 1) * grainSize;

				// Calculo la cantidad de particulas moviendose en cada subdominio
				int[] np = sumCells(xLower, xUpper, yLower, yUpper);

				// Calculo vel promedio
				double velXR = np[0] * VelocityConstants.R.getX();
				double velXUR = np[1] * VelocityConstants.UR.getX();
				double velXUL = np[2] * VelocityConstants.UL.getX();
				double velXL = np[3] * VelocityConstants.L.getX();
				double velXBL = np[4] * VelocityConstants.BL.getX();
				double velXBR = np[5] * VelocityConstants.BR.getX();

				double velYR = np[0] * VelocityConstants.R.getY();
				double velYUR = np[1] * VelocityConstants.UR.getY();
				double velYUL = np[2] * VelocityConstants.UL.getY();
				double velYL = np[3] * VelocityConstants.L.getY();
				double velYBL = np[4] * VelocityConstants.BL.getY();
				double velYBR = np[5] * VelocityConstants.BR.getY();

				double mean = 1d / (grainSize * grainSize);

				velocities[curVal][0] = mean * (velXR + velXUR + velXUL + velXL + velXBL + velXBR);
				velocities[curVal][1] = mean * (velYR + velYUR + velYUL + velYL + velYBL + velYBR);
				velocities[curVal][2] = Math.sqrt(velocities[curVal][0] * velocities[curVal][0] + velocities[curVal][1] * velocities[curVal][1]);

				curVal++;    			
			}
		}

		return velocities; 
	}

	private int[] sumCells(int xLower, int xUpper, int yLower, int yUpper) {
		int[] np = new int[6];
		for (int i = xLower; i < xUpper; i++) {
			for (int j = yLower; j < yUpper; j++) {
				np[0] += nodes[i][j][0];
				np[1] += nodes[i][j][1];
				np[2] += nodes[i][j][2];
				np[3] += nodes[i][j][3];
				np[4] += nodes[i][j][4];
				np[5] += nodes[i][j][5];
			}
		}
		return np;
	}

	private int first(int[] node) {
		for (int i = 0; i < 6; i++) {
			if (node[i] == 1) {
				return i;
			}
		}
		System.out.println("WHAT");
		return -1;
	}

	private void invertCell(int i, int j) {
		for (int k = 0; k < 6; k++) {
			if (nodes[i][j][k] == 1) {
				nodes[i][j][k] = 0;				
			} else {
				nodes[i][j][k] = 1;
			}
		}
	}

	private int sum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public void generateParticles(){
		// Meto particulas menos en las puntas que hay pared
		for (int i = 1; i < height - 1; i++) {         	
			nodes[0][i][1] = 1;
			nodes[0][i][0] = 1;
			nodes[0][i][5] = 1;
		}
	}

	public int[] reverseCell(int i, int j) {
		return new int[]{nodes[i][j][3], nodes[i][j][4], nodes[i][j][5], nodes[i][j][0], nodes[i][j][1], nodes[i][j][2]};
	}

}
