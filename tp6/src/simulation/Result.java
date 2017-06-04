package simulation;

import java.util.Map;

public class Result {

	private final Map<Double, Integer> discharges;
	private final Map<Double, Double> flow;
	private final Map<Double, Double> kineticEnergy;
	private final Map<Double, Double> movementEfficiency;
	private final double evacuationTime;
	
	public Result(final Map<Double, Integer> discharges, final Map<Double, Double> flow, final double evacuationTime, final Map<Double, Double> kineticEnergy, final Map<Double, Double> movementEfficiency) {
		this.discharges = discharges;
		this.flow = flow;
		this.evacuationTime = evacuationTime;
		this.kineticEnergy = kineticEnergy;
		this.movementEfficiency = movementEfficiency;
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

	public Map<Double, Double> getKineticEnergy() {
		return kineticEnergy;
	}

	public Map<Double, Double> getMovementEfficiency() {
		return movementEfficiency;
	}
	
}
