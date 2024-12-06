package rrx.aoc24.day6;

import rrx.ChristmasException;
import rrx.utils.Direction;

class Guard {
    public Direction direction;
    public int x;
    public int y;
    public int travelCount = 1;

    public Guard clone() {
        Guard clone = new Guard();
        clone.direction = this.direction;
        clone.x = this.x;
        clone.y = this.y;
        clone.travelCount = this.travelCount;
        return clone;
    }

    public void turnRight() {
        direction = switch (direction) {
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
            case UP -> Direction.RIGHT;
            default -> throw new ChristmasException("No No No!");
        };
    }

    public void move(char c) {
        x = nextX();
        y = nextY();
        if (c == '.') travelCount++;
    }

    public int nextX() {
        return nextX(1);
    }

    public int nextX(int factor) {
        return x + direction.x * factor;
    }

    public int nextY() {
        return nextY(1);
    }

    public int nextY(int factor) {
        return y + direction.y * factor;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + direction;
    }
}