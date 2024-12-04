package rrx.aoc24.day4;

public class CorrectXmasFinder implements XmasFinder {

    private final char[][] grid;
    private final char[][] resultGrid;

    CorrectXmasFinder(char[][] grid, char[][] resultGrid) {
        this.grid = grid;
        this.resultGrid = resultGrid;
    }

    @Override
    public boolean skip(char c) {
        return c != 'A';
    }

    @Override
    public long findXmas(int i, int j) {
        Pair pair1 = new Pair(grid[i - 1][j - 1], grid[i + 1][j + 1]);
        if (pair1.invalid()) {
            return 0L;
        }
        Pair pair2 = new Pair(grid[i + 1][j - 1], grid[i - 1][j + 1]);
        if (pair2.invalid()) {
            return 0L;
        }
        resultGrid[i][j] = 'A';
        resultGrid[i - 1][j - 1] = grid[i - 1][j - 1];
        resultGrid[i + 1][j + 1] = grid[i + 1][j + 1];
        resultGrid[i + 1][j - 1] = grid[i + 1][j - 1];
        resultGrid[i - 1][j + 1] = grid[i - 1][j + 1];
        return 1L;
    }

    private record Pair(char first, char second) {
        boolean invalid() {
            return first == second || (first != 'S' || second != 'M') && (first != 'M' || second != 'S');
        }
    }
}
