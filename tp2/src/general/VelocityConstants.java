package general;


public enum VelocityConstants {
    R(1, 0),
    UR(Math.cos(Math.PI / 3), Math.sin(Math.PI / 3)),
	UL(Math.cos(2 * Math.PI / 3), Math.sin(2 * Math.PI / 3)),
	L(-1, 0),
	BL(Math.cos(4 * Math.PI / 3), Math.sin(4 * Math.PI / 3)),
	BR(Math.cos(5 * Math.PI / 3), Math.sin(5 * Math.PI / 3));

    final private double x;
    final private double y;

    VelocityConstants(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}