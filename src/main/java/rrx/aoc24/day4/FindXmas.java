package rrx.aoc24.day4;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;
import rrx.utils.PrintUtil;

class FindXmas {

    public static void main(String[] args) {
        var grid = FileUtil.readToGrid("24/d04p1");
        ChristmasAssert.test(findXmas(0, grid), 2530L);
        ChristmasAssert.test(findXmas(1, grid), 1921L);
    }

    private static long findXmas(int iter, char[][] grid) {
        var resultGrid = initResultGrid(grid);
        var xmasFinder = (iter == 0)
                ? new LiteralXmasFinder(grid, resultGrid)
                : new CorrectXmasFinder(grid, resultGrid);

        long count = 0L;
        for (int i = iter; i < grid.length - iter; i++) {
            for (int j = iter; j < grid[i].length - iter; j++) {
                if (xmasFinder.skip(grid[i][j])) {
                    continue;
                }
                count += xmasFinder.findXmas(i, j);
            }
        }

        PrintUtil.grid(resultGrid);
        return count;
    }

    private static char[][] initResultGrid(char[][] grid) {
        var resultGrid = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                resultGrid[i][j] = '.';
            }
        }
        return resultGrid;
    }
}
