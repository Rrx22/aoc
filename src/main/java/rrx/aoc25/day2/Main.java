package rrx.aoc25.day2;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {
    static void main() {
        var x = FileUtil.readToGrid("25/d02p1");

        Something y = new Something(x);
        ChristmasAssert.test(y.doSomething(), 0L);

        y.changeItUp();
        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
