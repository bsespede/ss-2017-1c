package simulation;

public class Result {

	final boolean spaceshipCollidedMars;	
	final double launchTime;
	final double relativeSpeed;
	final double minDistance;
	final double travelTime;
	
	public Result(boolean spaceshipCollidedMars, double launchTime, double relativeSpeed, double minDistance, double travelTime) {
		this.spaceshipCollidedMars = spaceshipCollidedMars;
		this.launchTime = launchTime;
		this.relativeSpeed = relativeSpeed;
		this.minDistance = minDistance;
		this.travelTime = travelTime;
	}

	public boolean spaceshipCollidedMars() {
		return spaceshipCollidedMars;
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
