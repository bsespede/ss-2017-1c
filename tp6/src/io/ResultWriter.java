package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import simulation.Result;

public class ResultWriter {

	public static void writeResult(final String path, final Result result) {
		try {
			BufferedWriter dischargesWriter = new BufferedWriter(new FileWriter(path + "discharges.dat"));
			final Map<Double, Integer> discharges = result.getDischarges();
			for (Double time: discharges.keySet()) {
				dischargesWriter.write(String.format("%d %d\n", time, discharges.get(time)));
			}
			dischargesWriter.flush();
			dischargesWriter.close();
			
			BufferedWriter flowWriter = new BufferedWriter(new FileWriter(path + "flow.dat"));
			final Map<Double, Double> flow = result.getFlow();
			for (Double time: flow.keySet()) {
				flowWriter.write(String.format("%d %d\n", time, flow.get(time)));
			}
			flowWriter.flush();
			flowWriter.close();
			
			BufferedWriter evacuationTimeWriter = new BufferedWriter(new FileWriter(path + "evacuation.dat"));
			final double evactuationTime = result.getEvacuationTime();
			evacuationTimeWriter.write(String.format("%d\n", evactuationTime));
			evacuationTimeWriter.flush();
			evacuationTimeWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeEvacTimes(final String path, final Map<Double, Double> evacTimes) {
		BufferedWriter evacuationTimeWriter;
		try {
			evacuationTimeWriter = new BufferedWriter(new FileWriter(path + "total-evacuations.dat"));
			for (Double time: evacTimes.keySet()) {
				evacuationTimeWriter.write(String.format("%d %d\n", time, evacTimes.get(time)));
			}
			evacuationTimeWriter.flush();
			evacuationTimeWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
