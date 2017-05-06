package simulation;

public class Result {

	final double launchTime;
	final double relativeSpeed;
	final double minDistance;
	final double travelTime;
	
	public Result(double launchTime, double relativeSpeed, double minDistance, double travelTime) {
		this.launchTime = launchTime;
		this.relativeSpeed = relativeSpeed;
		this.minDistance = minDistance;
		this.travelTime = travelTime;
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
