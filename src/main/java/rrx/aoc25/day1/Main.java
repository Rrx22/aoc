package rrx.aoc25.day1;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {
    static void main() {

        DialLock dialLock = new DialLock(FileUtil.readFile("25/d01p1"));

        long password = dialLock.turnDials();
        ChristmasAssert.test(
                password < 6668L
                        && password != 6142L
                        && password != 6568L
                , password);

    }
}
