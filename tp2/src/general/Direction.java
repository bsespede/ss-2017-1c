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

    public Direction getById(int id){
        return Direction.values()[id % 6];
    }
}



