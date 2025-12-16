package rrx.aoc25.day9;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var input = FileUtil.readFile("25/d09p1");

        MovieTheater y = new MovieTheater(input);
        ChristmasAssert.test(y.findLargestRedTileRectangle(), 4733727792L);

//        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
