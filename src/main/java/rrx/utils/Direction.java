package rrx.utils;

public enum Direction {
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0),
    UP(0, -1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(-1, 1),
    UP_RIGHT(1, -1),
    UP_LEFT(-1, -1);

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHorizontal() {
        return this == LEFT || this == RIGHT;
    }
    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public static Direction[] valuesRLDU() {
        return new Direction[] {RIGHT, DOWN, LEFT, UP};
    }

    public boolean isDiagonal() {
        return this == DOWN_RIGHT || this == DOWN_LEFT || this == UP_RIGHT || this == UP_LEFT;
    }
}
