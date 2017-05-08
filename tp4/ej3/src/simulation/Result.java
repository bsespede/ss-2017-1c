package simulation;

public class Result {

	private final boolean passedMarsOrbit;
	private final double launchDay;
	private final double relativeSpeed;
	private final double minDistance;
	private final double travelDays;
	
	public Result(boolean passedMarsOrbit, double launchDay, double relativeSpeed, double minDistance, double travelDays) {
		this.passedMarsOrbit = passedMarsOrbit;
		this.launchDay = launchDay;
		this.relativeSpeed = relativeSpeed;
		this.minDistance = minDistance;
		this.travelDays = travelDays;
	}
	
	public boolean hasPassedMarsOrbit() {
		return passedMarsOrbit;
	}

	public double getLaunchDay() {
		return launchDay;
	}

	public double getRelativeSpeed() {
		return relativeSpeed;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public double getTravelDays() {
		return travelDays;
	}
	
}
