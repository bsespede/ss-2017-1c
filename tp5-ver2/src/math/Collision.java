package math;

public class Collision {

	//returns the line's point which is at minimum distance from the sphere's center
	public static Vector2d distanceLinePoint(final Vector2d point, final Vector2d linePoint1, final Vector2d linePoint2) {

		double a = point.x - linePoint1.x;
		double b = point.y - linePoint1.y;
		double c = linePoint2.x - linePoint1.x;
		double d = linePoint2.y - linePoint1.y;

		double dot = a * c + b * d;
		double lenSq = c * c + d * d;
		double param = -1;
		
		if (lenSq != 0) { //in case of 0 length line
			param = dot / lenSq;
		}

		if (param < 0) {
			return new Vector2d(linePoint1.x, linePoint1.y);
		} else if (param > 1) {
			return new Vector2d(linePoint2.x, linePoint2.y);
		} else {
			return new Vector2d(linePoint1.x + param * c, linePoint1.y + param * d);
		}
	}
}
