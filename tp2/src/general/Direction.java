package general;


public enum Direction {
    UR(1, 1),
    R (1, 0),
    BR(1, -1),
    BL(0, -1),
    L (-1, 0),
    UL(0, 1);

    final private int x;
    final private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction reverse() {
        switch (this) {
	        case UR:
	        	return Direction.BL;
	        case BR:
	        	return Direction.UL;
	        case BL:
	        	return Direction.UR;
	        case UL:
	        	return Direction.BR;
	        case R:
	        	return Direction.L;
	        default:
	        	return Direction.R;
        }
    }

    public Direction clockwise(){
    	switch (this) {
	        case UR:
	        	return Direction.R;
	        case BR:
	        	return Direction.BL;
	        case BL:
	        	return Direction.L;
	        case UL:
	        	return Direction.UR;
	        case R:
	        	return Direction.BR;
	        default:
	        	return Direction.UL;
	    }
    }

    public Direction counterClockwise(){
    	switch (this) {
	        case UR:
	        	return Direction.UL;
	        case BR:
	        	return Direction.R;
	        case BL:
	        	return Direction.BR;
	        case UL:
	        	return Direction.L;
	        case R:
	        	return Direction.UR;
	        default:
	        	return Direction.BL;
	    }
	}

}