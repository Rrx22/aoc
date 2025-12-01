package rrx.aoc24.day7;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class RepairBridge {

    static void main() {
        var operators = FileUtil.readFile("24/d07p1");

        BridgeEvaluator bridgeEvaluator = new BridgeEvaluator(operators);
        ChristmasAssert.test(bridgeEvaluator.calibrate(), 21_572_148_763_543L);

        bridgeEvaluator.enableConcatOperandus();
        ChristmasAssert.test(bridgeEvaluator.calibrate(), 581_941_094_529_163L);
    }
}
