package simulation;

import java.util.Map;

import io.ResultWriter;

public class Result {

	private final Map<Double, Integer> discharges;
	private final Map<Double, Double> flow;
	private final double evacuationTime;
	
	public Result(final Map<Double, Integer> discharges, final Map<Double, Double> flow, double evacuationTime) {
		this.discharges = discharges;
		this.flow = flow;
		this.evacuationTime = evacuationTime;
	}

	public Map<Double, Integer> getDischarges() {
		return discharges;
	}

	public Map<Double, Double> getFlow() {
		return flow;
	}

	public double getEvacuationTime() {
		return evacuationTime;
	}

	public void writeResults(final int runId) {
		ResultWriter.writeResult("../"+ runId +"-", this);
	}
	
}
