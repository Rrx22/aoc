package rrx.aoc25.day23;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var x = FileUtil.readToGrid("25/d23p1");

        Something y = new Something(x);
        ChristmasAssert.test(y.doSomething(), 0L);

        y.changeItUp();
        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
