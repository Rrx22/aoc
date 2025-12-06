package rrx.aoc25.day6;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var input = FileUtil.readFile("25/d06p1");
        ChristmasAssert.test(CephalopodMathHomework.solveHumanReadableWay(input), 4878670269096L);
        ChristmasAssert.test(CephalopodMathHomework.solveCephalopodWay(input), 8674740488592L);
    }
}
