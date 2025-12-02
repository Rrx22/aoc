package rrx.aoc25.day2;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {
    static void main() {
        var rangeInput = FileUtil.readFile("25/d02p1").getFirst();

        ProductIdValidator y = new ProductIdValidator(rangeInput);
        ChristmasAssert.test(y.scan(), 28846518423L);

        y.fixTheScanConditions();
        ChristmasAssert.test(y.scan(), 31578210022L);
    }
}
