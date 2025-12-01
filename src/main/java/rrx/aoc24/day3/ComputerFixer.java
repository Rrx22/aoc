package rrx.aoc24.day3;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class ComputerFixer {

    static void main() {
        var corruptedFile = FileUtil.readFile("24/d03p1");
        var corruptedMemoryHelper = new CorruptedMemoryHelper();

        ChristmasAssert.test(corruptedMemoryHelper.scan(corruptedFile), 166630675L);

        corruptedMemoryHelper.enableInstructions();
        ChristmasAssert.test(corruptedMemoryHelper.scan(corruptedFile), 93465710L);
    }
}
