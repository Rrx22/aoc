package rrx.aoc24.day11;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {

    public static void main(String[] args) {
        String plutonianPebbles = FileUtil.readFile("24/d11p1").getFirst();

        ChristmasAssert.test(Me.blinkPart2(25, plutonianPebbles), 183484L);
        ChristmasAssert.test(Me.blinkPart2(75, plutonianPebbles), 0L);
    }
}
