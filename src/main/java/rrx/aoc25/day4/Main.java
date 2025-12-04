package rrx.aoc25.day4;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var x = FileUtil.readToGrid("25/d04p1");

        PrintingDepartment y = new PrintingDepartment(x);
        ChristmasAssert.test(y.clearOutRolls(), 8972L);
    }
}
