package rrx.aoc25.day1;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {
    public static void main(String[] args) {
        var x = FileUtil.readToGrid("25/d01p1");

        Something y = new Something(x);
        ChristmasAssert.test(y.doSomething(), 0L);

        y.changeItUp();
        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
