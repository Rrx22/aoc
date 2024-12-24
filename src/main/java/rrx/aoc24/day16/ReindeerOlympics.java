package rrx.aoc24.day16;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class ReindeerOlympics {

    public static void main(String[] args) {
        MazeBolter mazeBolter = new MazeBolter(FileUtil.readToGrid("24/d16p1"));
        ChristmasAssert.test(mazeBolter.solveMaze(), 593L);
    }
}
