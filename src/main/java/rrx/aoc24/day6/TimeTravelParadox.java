package rrx.aoc24.day6;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class TimeTravelParadox {

    public static void main(String[] args) {
        StealthProcessor stealthProcessor = new StealthProcessor(FileUtil.readToGrid("24/d06p1"));
        ChristmasAssert.test(stealthProcessor.sneakPastGuard(), 4602L);

        stealthProcessor = new StealthProcessor(FileUtil.readToGrid("24/d06p1"));
        ChristmasAssert.test(stealthProcessor.makeGuardsLifeHell(), 1703L);
    }
}
