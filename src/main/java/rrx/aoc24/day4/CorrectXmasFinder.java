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
            new Pair(grid[i - 1][j - 1], grid[i + 1][j + 1]).validate();
            new Pair(grid[i + 1][j - 1], grid[i - 1][j + 1]).validate();
        } catch (ChristmasException e) {
            return 0L;
        }
        return 1L;
    }

    private record Pair(char first, char second) {
        void validate() {
            if (first == second || (first != 'S' || second != 'M') && (first != 'M' || second != 'S')) {
                throw new ChristmasException("This is not X-mas!");
            }
        }
    }
}
