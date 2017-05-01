package math;

public class Vector3d {

	public double x;
	public double y;
	public double z;
	
	public Vector3d (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3d scale(double scale) {
		return new Vector3d(x * scale, y * scale, z * scale);
	}
	
	public Vector3d add(final Vector3d v2) {
		return new Vector3d(x + v2.x, y + v2.y, z + v2.z);
	}
	
	public Vector3d substract(Vector3d v2) {
		return new Vector3d(x - v2.x, y - v2.y, z - v2.z);
	}
	
	public Vector3d normalize() {
		double module = module();
		return new Vector3d(x / module, y / module, z / module);
	}
	
	public double module() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public double dot(final Vector3d v2) {
		return x * v2.x + y * v2.y + z * v2.z;
	}
	
	public double distance(final Vector3d v2) {
		double distX = x - v2.x;
		double distY = y - v2.y;
		double distZ = z - v2.z;
		return Math.sqrt(distX * distX + distY * distY + distZ * distZ);
	}
	
}
