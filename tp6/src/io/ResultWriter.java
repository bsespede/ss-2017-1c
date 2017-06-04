package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import simulation.Result;

public class ResultWriter {

	public static void writeResult(final String path, final Result result) {
		try {
			BufferedWriter dischargesWriter = new BufferedWriter(new FileWriter(path + "discharges.dat"));
			final Map<Double, Integer> discharges = result.getDischarges();
			for (Double time: discharges.keySet()) {
				dischargesWriter.write(String.format("%d %.2f\n", discharges.get(time), time));
			}
			dischargesWriter.flush();
			dischargesWriter.close();
			
			BufferedWriter flowWriter = new BufferedWriter(new FileWriter(path + "flow.dat"));
			final Map<Double, Double> flow = result.getFlow();
			for (Double time: flow.keySet()) {
				flowWriter.write(String.format("%.2f %.2f\n", time, flow.get(time)));
			}
			flowWriter.flush();
			flowWriter.close();
			
			BufferedWriter energyWriter = new BufferedWriter(new FileWriter(path + "energy.dat"));
			final Map<Double, Double> energy = result.getKineticEnergy();
			for (Double time: energy.keySet()) {
				energyWriter.write(String.format("%.2f %.2f\n", time, energy.get(time)));
			}
			energyWriter.flush();
			energyWriter.close();
			
			BufferedWriter efficiencyWriter = new BufferedWriter(new FileWriter(path + "efficiency.dat"));
			final Map<Double, Double> efficiency = result.getMovementEfficiency();
			for (Double time: efficiency.keySet()) {
				efficiencyWriter.write(String.format("%.2f %.2f\n", time, efficiency.get(time)));
			}
			efficiencyWriter.flush();
			efficiencyWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeEvacTimes(final String path, final Map<Double, List<Double>> evacTimes) {
		BufferedWriter evacuationTimeWriter;
		try {
			evacuationTimeWriter = new BufferedWriter(new FileWriter(path + "total-evacuations.dat"));
			for (Double speed: evacTimes.keySet()) {
				for (Double evacTime: evacTimes.get(speed)) {
					evacuationTimeWriter.write(String.format("%.2f %.2f\n", speed, evacTime));
				}
			}
			evacuationTimeWriter.flush();
			evacuationTimeWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
