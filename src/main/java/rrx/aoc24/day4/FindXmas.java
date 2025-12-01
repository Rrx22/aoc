package rrx.aoc24.day4;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class FindXmas {

    static void main() {
        var grid = FileUtil.readToGrid("24/d04p1");
        int height = grid.length;
        int width = grid[0].length;

        ChristmasAssert.test(findXmas(new LiteralXmasFinder(grid), height, width), 2530L);
        ChristmasAssert.test(findXmas(new CorrectXmasFinder(grid), height, width), 1921L);
    }

    private static long findXmas(XmasFinder xmasFinder, int height, int width) {
        int skip = xmasFinder.edgeSkipper();

        long count = 0L;
        for (int i = skip; i < height - skip; i++) {
            for (int j = skip; j < width - skip; j++) {
                if (xmasFinder.notInteresting(i, j)) {
                    continue;
                }
                count += xmasFinder.findXmas(i, j);
            }
        }
        return count;
    }
}
