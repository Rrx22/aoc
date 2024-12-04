package rrx.aoc24.day4;

import rrx.ChristmasException;

public class CorrectXmasFinder implements XmasFinder {

    private final char[][] grid;

    CorrectXmasFinder(char[][] grid) {
        this.grid = grid;
    }

    @Override
    public int edgeSkipper() {
        return 1;
    }

    @Override
    public boolean notInteresting(int i, int j) {
        return grid[i][j] != 'A';
    }

    @Override
    public long findXmas(int i, int j) {
        try {
            validate(grid[i - 1][j - 1], grid[i + 1][j + 1]);
            validate(grid[i + 1][j - 1], grid[i - 1][j + 1]);
        } catch (ChristmasException e) {
            return 0L;
        }
        return 1L;
    }

    void validate(char first, char second) {
        if ((first != 'S' || second != 'M') && (first != 'M' || second != 'S')) {
            throw new ChristmasException("This is not X-mas!");
        }
    }
}
