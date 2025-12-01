package rrx.aoc25.day1;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {
    static void main() {
        DialLock dialLock = new DialLock(FileUtil.readFile("25/d01p1"));
        ChristmasAssert.test(dialLock.turnDials(), 6122L);
    }
}
