package rrx.aoc24.day4;

import rrx.Direction;

public class LiteralXmasFinder implements XmasFinder {

    private final char[][] grid;
    private final char[][] resultGrid;

    LiteralXmasFinder(char[][] grid, char[][] resultGrid) {
        this.grid = grid;
        this.resultGrid = resultGrid;
    }

    @Override
    public boolean skip(char c) {
        return c != 'X';
    }

    @Override
    public long findXmas(int i, int j) {
        long count = 0L;

        boolean checkRight = j + 3 < grid[i].length;
        boolean checkLeft = j - 3 >= 0;
        boolean checkUp = i - 3 >= 0;
        boolean checkDown = i + 3 < grid.length;

        if (checkRight && check(Direction.RIGHT, i, j)) count++;
        if (checkLeft && check(Direction.LEFT, i, j)) count++;
        if (checkUp && check(Direction.UP, i, j)) count++;
        if (checkDown && check(Direction.DOWN, i, j)) count++;
        if (checkUp && checkLeft && check(Direction.UP_LEFT, i, j)) count++;
        if (checkUp && checkRight && check(Direction.UP_RIGHT, i, j)) count++;
        if (checkDown && checkLeft && check(Direction.DOWN_LEFT, i, j)) count++;
        if (checkDown && checkRight && check(Direction.DOWN_RIGHT, i, j)) count++;
        return count;
    }


    private boolean check(Direction d, int i, int j) {
        boolean found = grid[i + d.y][j + d.x] == 'M'
                && grid[i + d.y * 2][j + d.x * 2] == 'A'
                && grid[i + d.y * 3][j + d.x * 3] == 'S';
        if (found) {
            resultGrid[i][j] = 'X';
            resultGrid[i + d.y][j + d.x] = 'M';
            resultGrid[i + d.y * 2][j + d.x * 2] = 'A';
            resultGrid[i + d.y * 3][j + d.x * 3] = 'S';
        }
        return found;
    }
}
