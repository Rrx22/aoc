package rrx.aoc24.day10;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class LavaIslandHikingGuide {

    static void main() {
        var topographicMap = FileUtil.readToGrid("24/d10p1");

        TrailBlazer trailBlazer = new TrailBlazer()
                .withGrid(topographicMap)
                .withPopulatedTrailHeads()
                .withPopulateRoutes();
        ChristmasAssert.test(trailBlazer.totalSets(), 717L);
        ChristmasAssert.test(trailBlazer.totalTrails(), 1686L);
    }
}
