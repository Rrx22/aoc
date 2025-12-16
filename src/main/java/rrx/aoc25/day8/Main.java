package rrx.aoc25.day8;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var x = FileUtil.readFile("25/d08p1");

        XmasLighting y = new XmasLighting(x);
        ChristmasAssert.test(y.hangTheLights(), 181584L);

        y.tryHarder();
        ChristmasAssert.test(y.hangTheLights(), 8465902405L);
    }
}
