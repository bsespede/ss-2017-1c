package simulation;

import java.util.Map;

public class Result {

	private final Map<Double, Integer> discharges;
	private final Map<Double, Double> meanFlow;
	private final Map<Double, Double> flow;
	private final Map<Double, Double> kineticEnergy;
	private final Map<Double, Double> pressure;
	private final double evacuationTime;
	
	public Result(final Map<Double, Integer> discharges, final Map<Double, Double> meanFlow, final double evacuationTime, final Map<Double, Double> kineticEnergy, final Map<Double, Double> flow, final Map<Double, Double> pressure) {
		this.discharges = discharges;
		this.meanFlow = meanFlow;
		this.evacuationTime = evacuationTime;
		this.kineticEnergy = kineticEnergy;
		this.pressure = pressure;
		this.flow = flow;
	}
	
	public Map<Double, Double> getPressure() {
		return pressure;
	}

	public Map<Double, Integer> getDischarges() {
		return discharges;
	}

	public Map<Double, Double> getMeanFlow() {
		return meanFlow;
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
	
}
