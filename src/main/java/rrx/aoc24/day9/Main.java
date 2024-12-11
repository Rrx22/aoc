package rrx.aoc24.day9;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class Main {

    public static void main(String[] args) {
        var disk = FileUtil.readFile("24/d09p1").getFirst();

        ChristmasAssert.test(DiskTool.doSomething(disk), 6398343689624L);

        DiskTool.changeItUp();
        ChristmasAssert.test(DiskTool.doSomething(disk), 6427437134372L);
    }
}
