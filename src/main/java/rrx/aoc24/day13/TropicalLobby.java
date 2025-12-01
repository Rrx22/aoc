package rrx.aoc24.day13;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class TropicalLobby {

    static void main() {
        var clawMachines = FileUtil.readFile("24/d13p1");
        MathBeatsMachine mathBeatsMachine = new MathBeatsMachine(clawMachines, 0L);
        ChristmasAssert.test(mathBeatsMachine.analyse(), 31065L);

        mathBeatsMachine = new MathBeatsMachine(clawMachines, 10000000000000L);
        ChristmasAssert.test(mathBeatsMachine.analyse(), 93866170395343L);
    }
}
