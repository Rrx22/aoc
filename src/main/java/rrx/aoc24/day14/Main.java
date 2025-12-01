package rrx.aoc24.day14;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {

    static void main() throws InterruptedException {
        RAT rat = new RAT();
        rat.init(FileUtil.readFile("24/d14p1"), 103, 101);
        ChristmasAssert.test(rat.moveRobots(100), 226179492L);
        ChristmasAssert.test(rat.moveRobots(Long.MAX_VALUE), 7402L);
    }
}
