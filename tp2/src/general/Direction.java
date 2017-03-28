package general;


public enum Direction {
    UR(-1, 0),
    R (0, 1),
    BR(1, 0),
    BL(1, -1),
    L (0, -1),
    UL(-1, -1);

    private int dirx;
    private int diry;

    Direction(int dirx, int diry) {
        this.dirx = dirx;
        this.diry = diry;
    }


    public int getDirx() {
        return dirx;
    }

    public int getDiry() {
        return diry;
    }

    public static Direction reverseX(Direction dir) {
        Direction newDir = dir;
        if(dir == Direction.UR){
            newDir = Direction.BR;
        }else if (dir == Direction.BR){
            newDir = Direction.UR;
        }else if (dir == Direction.UL) {
            newDir = Direction.BL;
        }else if (dir == Direction.BL) {
            newDir = Direction.UL;
        }
        return newDir;
    }

    public static Direction reverseY(Direction dir) {
        Direction newDir = dir;
        if(dir == Direction.UR){
            newDir = Direction.UL;
        }else if (dir == Direction.UL){
            newDir = Direction.UR;
        }else if (dir == Direction.R) {
            newDir = Direction.L;
        }else if (dir == Direction.L) {
            newDir = Direction.R;
        }else if (dir == Direction.BL) {
            newDir = Direction.BR;
        }else if (dir == Direction.BR) {
            newDir = Direction.BL;
        }
       return newDir;
    }

    public static Direction turnLeft(Direction dir){
        Direction newDir = dir;
        if(dir == Direction.UR){
            newDir = Direction.UL;
        }else if (dir == Direction.UL){
            newDir = Direction.L;
        }else if (dir == Direction.L) {
            newDir = Direction.BL;
        }else if (dir == Direction.BL) {
            newDir = Direction.BR;
        }else if (dir == Direction.BR) {
            newDir = Direction.R;
        }else if (dir == Direction.R) {
            newDir = Direction.UR;
        }
        return newDir;
    }

    public static Direction turnRight(Direction dir){
        Direction newDir = dir;
        if(dir == Direction.UR){
            newDir = Direction.R;
        }else if (dir == Direction.R){
            newDir = Direction.BR;
        }else if (dir == Direction.BR) {
            newDir = Direction.BL;
        }else if (dir == Direction.BL) {
            newDir = Direction.L;
        }else if (dir == Direction.L) {
            newDir = Direction.UL;
        }else if (dir == Direction.UL) {
            newDir = Direction.UR;
        }
        return newDir;
    }

}



