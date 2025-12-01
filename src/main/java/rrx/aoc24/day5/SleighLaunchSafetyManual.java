package rrx.aoc24.day5;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class SleighLaunchSafetyManual {

    static void main() {
        var sleighLaunchSafetyManual = FileUtil.readFile("24/d05p1");
        PageOrderingRuleComputer computer = new PageOrderingRuleComputer(sleighLaunchSafetyManual);

        ChristmasAssert.test(computer.organiseUpdates(), 5639L);
        ChristmasAssert.test(computer.reorderIncorrectlyOrderedUpdates(), 5273L);
    }
}
