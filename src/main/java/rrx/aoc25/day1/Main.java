package rrx.aoc25.day1;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {
    public static void main(String[] args) {
        var x = FileUtil.readToGrid("25/d01p1");

        Challenge y = new Challenge(x);
        ChristmasAssert.test(y.doSomething(), 0L);

//        y.changeItUp();
//        ChristmasAssert.test(y.doSomething(), 0L);
    }
}
