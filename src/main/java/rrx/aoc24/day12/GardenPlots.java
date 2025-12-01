package rrx.aoc24.day12;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;
import rrx.utils.Pair;

class GardenPlots {

    static void main() {
        var gardenMap = FileUtil.readToGrid("24/d12p1");
        Pair<Long, Long> quotations = GardenFenceCalculator.calculateFencePrice(gardenMap);
        ChristmasAssert.test(quotations.first(), 1465968L);
        ChristmasAssert.test(quotations.second(), 897702L);
    }
}
