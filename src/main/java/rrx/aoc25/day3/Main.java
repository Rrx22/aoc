package rrx.aoc25.day3;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var x = FileUtil.readFile("25/d03p1");

        ElevatorLobby y = new ElevatorLobby(x);
        ChristmasAssert.test(y.doSomething(), 17412L);

        y.startConsideringStiction();
        ChristmasAssert.test(y.doSomething(), 172681562473501L);
    }
}
