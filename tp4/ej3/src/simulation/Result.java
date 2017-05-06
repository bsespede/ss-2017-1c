package simulation;

public class Result {

	private final boolean passedMarsOrbit;
	private final double launchTime;
	private final double relativeSpeed;
	private final double minDistance;
	private final double travelTime;
	
	public Result(boolean passedMarsOrbit, double launchTime, double relativeSpeed, double minDistance, double travelTime) {
		this.passedMarsOrbit = passedMarsOrbit;
		this.launchTime = launchTime;
		this.relativeSpeed = relativeSpeed;
		this.minDistance = minDistance;
		this.travelTime = travelTime;
	}
	
	public boolean hasPassedMarsOrbit() {
		return passedMarsOrbit;
	}

	public double getLaunchTime() {
		return launchTime;
	}

	public double getRelativeSpeed() {
		return relativeSpeed;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public double getTravelTime() {
		return travelTime;
	}
	
}
