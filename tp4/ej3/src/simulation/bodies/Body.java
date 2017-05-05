package simulation.bodies;

public enum Body {

	SPACESHIP(255, 255, 255),
	MARS(0, 255, 0),
	EARTH(0, 0, 255),
	SUN(255, 0, 0);

	private final int red, green, blue;

	Body(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public static Body getBodyFromName(final String name) {
		switch (name) {
		case "EARTH":
			return Body.EARTH;
		case "SUN":
			return Body.SUN;
		case "MARS":
			return Body.MARS;
		default:
			return Body.SPACESHIP;
		}
	}

}
