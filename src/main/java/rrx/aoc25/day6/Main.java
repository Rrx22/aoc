package rrx.aoc25.day6;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    public static void main(String[] args) {
        var x = FileUtil.readToGrid("25/d06p1");

        Something y = new Something(x);
        ChristmasAssert.test(y.doSomething(), 0L);

        y.changeItUp();
        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
