package rrx.aoc24.day4;

import rrx.utils.Direction;

public class LiteralXmasFinder implements XmasFinder {

    private final char[][] grid;

    LiteralXmasFinder(char[][] grid) {
        this.grid = grid;
    }

    @Override
    public int edgeSkipper() {
        return 0;
    }

    @Override
    public boolean notInteresting(int i, int j) {
        return grid[i][j] != 'X';
    }

    @Override
    public long findXmas(int i, int j) {
        long count = 0L;

        boolean checkRight = j + 3 < grid[i].length;
        boolean checkLeft = j - 3 >= 0;
        boolean checkUp = i - 3 >= 0;
        boolean checkDown = i + 3 < grid.length;

        count += check(Direction.RIGHT, i, j, checkRight);
        count += check(Direction.LEFT, i, j, checkLeft);
        count += check(Direction.UP, i, j, checkUp);
        count += check(Direction.DOWN, i, j, checkDown);
        count += check(Direction.UP_LEFT, i, j, checkUp && checkLeft);
        count += check(Direction.UP_RIGHT, i, j, checkUp && checkRight);
        count += check(Direction.DOWN_LEFT, i, j, checkDown && checkLeft);
        count += check(Direction.DOWN_RIGHT, i, j, checkDown && checkRight);
        return count;
    }

    private long check(Direction d, int i, int j, boolean condition) {
        return condition && grid[i + d.y][j + d.x] == 'M' && grid[i + d.y * 2][j + d.x * 2] == 'A' && grid[i + d.y * 3][j + d.x * 3] == 'S'
                ? 1L : 0L;
    }
}
