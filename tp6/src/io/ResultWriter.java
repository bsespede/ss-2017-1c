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
				dischargesWriter.write(String.format("%.2f %d\n", time, discharges.get(time)));
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
