package simulation;

public class Result {

	final boolean spaceshipCollidedMars;	
	final double launchDay;
	final double relativeSpeed;
	final double minDistance;
	final double travelTime;
	
	public Result(boolean spaceshipCollidedMars, double launchDay, double relativeSpeed, double minDistance, double travelTime) {
		this.spaceshipCollidedMars = spaceshipCollidedMars;
		this.launchDay = launchDay;
		this.relativeSpeed = relativeSpeed;
		this.minDistance = minDistance;
		this.travelTime = travelTime;
	}

	public boolean isSpaceshipCollidedMars() {
		return spaceshipCollidedMars;
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

	public double getTravelTime() {
		return travelTime;
	}
	
}
