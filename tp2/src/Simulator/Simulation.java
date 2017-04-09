package Simulator;

import general.VelocityConstants;
import io.FileProcessor;

public class Simulation {

	private int[][][] nodes;
	private final int[][] obstacles;
	private final int width, height;
	private final String output;

    public Simulation(final int width, final int height, final int L, final String output) {
    	//Arreglo 3D con posiciones y estados de la celda segun la dir
    	this.nodes = new int[width][height][6];
    	this.obstacles = new int[width][height];
    	this.width = width;
    	this.height = height;
    	this.output = output;
    	
    	// Pongo mis obstaculos
        final int wallMinY = (int) (Math.floor(height / 2.0 - L / 2.0));
        final int wallX = (int) (Math.ceil(width * 0.2));

        for(int i = 0 ; i < width ; i++){
            for (int j = 0; j < height + L; j++) {
            	if (i == wallX && j >= wallMinY && j < wallMinY + L) {
            		obstacles[i][j] = 1;            		
            	} else if (j == 0 || j == height -1) {
            		obstacles[i][j] = 1; 
            	}
            }
        }
        
    }

    public void simulate(final int steps) {
    	System.out.println("[INFO] Comienza la simulacion");
    	for (int t = 0; t < steps; t++) {
    		solveCollisions();
    		moveParticles();
    		double[][][] velocities = calculateVelocities();
    		// Genero nuevas particulas cada 4 ticks
    		if (t % 4 == 0) {
    			generateParticles();
    		}
    		// Cada tanto imprimo el t para ver que no se trabo
    		if (t % 100 == 0) {
    			System.out.println("[INFO] En el paso " + t);
    		}   
    		// Genero output de velocidades
    		FileProcessor.outputVelocities(velocities, "./outputs/" + output + "-" + t +".txt");
    	}
    	// Genero output de obstaculos
    	FileProcessor.outputBoundaries(obstacles, "./outputs/" + output + "-boundaries.txt");
    	System.out.println("[INFO] Ha finalizado la simulacion");
    }

	private void solveCollisions() {
    	// Chequeo colisiones excepto en paredes y obstaculos
		for (int i = 0; i < width; i++) {
			for (int j = 1; j < height - 1; j++) {
				if (obstacles[i][j] == 0) {
					int[] curCell = nodes[i][j];
					// Chequear si hay colisiones
					int sum = sum(curCell);    					
					// Caso 3 particulas colisionan
					if (sum == 3) {
						if (curCell[0] == curCell[2] && curCell[2] == curCell[4]) {
							invertCell(i, j);
						}
					// Caso 2 particulas colisionan
					} else if (sum == 2) {
						// Me fijo si la de enfrente es la otra ocupada
						int p1 = first(curCell);
						if (p1 < 3 && curCell[p1 + 3] == 1) {
							// Giro ambas clockwise o counterclockwise de manera random
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
				}    				
			}
		}    		
		// Colisiones en paredes
		for (int i = 0; i < width; i++) {
			nodes[i][0] = new int[]{nodes[i][0][3], nodes[i][0][4], nodes[i][0][5], nodes[i][0][0], nodes[i][0][1], nodes[i][0][2]};
			nodes[i][height-1] = new int[]{nodes[i][height-1][3], nodes[i][height-1][4], nodes[i][height-1][5], nodes[i][height-1][0], nodes[i][height-1][1], nodes[i][height-1][2]};
		}    		
		// Colisiones con obstaculos
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (obstacles[i][j] == 1) {
					nodes[i][j] = new int[]{nodes[i][0][3], nodes[i][0][4], nodes[i][0][5], nodes[i][0][0], nodes[i][0][1], nodes[i][0][2]};
				}
			}
		}
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
				if (i != width - 1) {
					neighbourY = j;
					neighbourX = i + 1;    					
					newNodes[neighbourX][neighbourY][0] = curCell[0];
				}    				
				// Propago en dir UR-2
				if (j != height - 1) {
					neighbourY = j + 1;
					if (j % 2 == 0) {
						if (i != width - 1) {
							neighbourX = i + 1;
							newNodes[neighbourX][neighbourY][1] = curCell[1];
						}
					} else {
						neighbourX = i;
						newNodes[neighbourX][neighbourY][1] = curCell[1];
					}
				}    				
				// Propago en dir UL-3
				if (j != height - 1) {
					neighbourY = j + 1;
					if (j % 2 == 1) {
						if (i != 0) {
							neighbourX = i - 1;
							newNodes[neighbourX][neighbourY][2] = curCell[2];
						}
					} else {
						neighbourX = i;
						newNodes[neighbourX][neighbourY][2] = curCell[2];
					}
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
							newNodes[neighbourX][neighbourY][4] = curCell[4];
						}
					} else {
						neighbourX = i;
						newNodes[neighbourX][neighbourY][4] = curCell[4];
					}
				} 
				// Propago en dir BR-6
				if (j != 0) {
					neighbourY = j - 1;
					if (j % 2 == 0) {
						if (i != width - 1) {
							neighbourX = i + 1;
							newNodes[neighbourX][neighbourY][5] = curCell[5];
						}
					} else {
						neighbourX = i;
						newNodes[neighbourX][neighbourY][5] = curCell[5];
					}
				} 
			}
		}
		// Updateo el tablero
		nodes = newNodes;
	}
	
	private double[][][] calculateVelocities() {
    	// Para calcular velocidad media uso coarse-graining
    	int grainSize = 8;
    	int grainX = width / grainSize;
    	int grainY = height / grainSize;
    	
    	int[] avgVelXCoords = new int[grainX * grainY];
    	int[] avgVelYCoords = new int[grainX * grainY];
    	double[] avgVelXComps = new double[grainX * grainY];
    	double[] avgVelYComps = new double[grainX * grainY];
    	
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
    			
    			avgVelXComps[curVal] = mean * (velXR + velXUR + velXUL + velXL + velXBL + velXBR);
    			avgVelYComps[curVal] = mean * (velYR + velYUR + velYUL + velYL + velYBL + velYBR);
    			
    			avgVelXCoords[curVal] = i;
    			avgVelYCoords[curVal] = j;
    			
    			curVal++;    			
    		}
    	}

    	double[][][] velocities = new double[curVal][2][2];
    	
    	for (int i = 0; i < curVal; i++) {
    		velocities[i] = new double[2][2];
    		velocities[i][0] = new double[]{avgVelXCoords[i], avgVelXCoords[i]};
    		velocities[i][1] = new double[]{avgVelXComps[i], avgVelYComps[i]};
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
				return 1;
			}
		}
		return 0;
	}

	private void invertCell(int i, int j) {
		for (int k = 0; k < 6; k++) {
			nodes[i][j][k] = ~nodes[i][j][k];
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
    
}
