package main;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import io.OutputWriter;
import simulation.Simulation;
import simulation.integrator.BeemanIntegrator;
import simulation.integrator.Integrator;

public class Main {
	
	private static final double HOUR = 3600;
	private static final double DAY = HOUR * 24;
	private static final double WEEK = DAY * 7;
	private static final double MONTH = DAY * 30;
	private static final double YEAR = DAY * 365;
	
	private final static double MAX_TIME = YEAR * 10;
	private final static double INTERVAL = 1000;
	private final static int INTERVAL_OUTPUT = (int) WEEK / 2;
	private final static Integrator INTEGRATOR = new BeemanIntegrator();

	public static void main(String[] args) {		
		long time = System.currentTimeMillis();
		int minMes = Integer.MAX_VALUE;
		double minDist = Double.MAX_VALUE, minTime = Double.MAX_VALUE, minVelocidad = Double.MAX_VALUE;
		Map<Double, Double> minDists = new LinkedHashMap<Double, Double>();
		double[] result;
		for (double v = 0; v <= 300; v += 25) {
			double minDist2 = Double.MAX_VALUE;
			for (int i = 0; i < MAX_TIME / MONTH; i++) {
				System.out.println("[INFO] Corriendo simulación mes: " + i);
				final Simulation simulation = new Simulation("DistanceMes-" + i, INTEGRATOR, INTERVAL, INTERVAL_OUTPUT, MAX_TIME, 180, i * MONTH, v * 1000);    
				result = simulation.simulate(); 
				if (result[0] < minDist) {
					minDist = result[0];
					minTime = result[1];
					minVelocidad= result[2];
					minMes = i;
				}
				if (result[0] < minDist2) {
					minDist2 = result[0];
				}
			}
			minDists.put(v, minDist2);
		}
		try {
			OutputWriter.writeVelocities("./speeds", minDists);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[INFO] Min distancia (km): " + minDist / 1000 + " Duracion(horas): " + (minTime - minMes * MONTH) / HOUR + " Mes de salida: " + minMes + " Velocidad en dicho momento: " + minVelocidad / 1000);
		time = System.currentTimeMillis() - time;        	
		System.out.println("[INFO] Tiempo de simulacion: " + time);
	}

}