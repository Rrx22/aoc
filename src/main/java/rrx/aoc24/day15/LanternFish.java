package rrx.aoc24.day15;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class LanternFish {

    public static void main(String[] args) {
        var input = FileUtil.readFile("24/d15p1");
//        var input = FileUtil.readFile("24/d16p1");

//        WarehouseManager warehouseManager = new WarehouseManager(input);
//        ChristmasAssert.test(warehouseManager.moveRobot(), 1360570L);

        EnlargedWarehouseManager enlargedWarehouseManager = new EnlargedWarehouseManager(input);
        ChristmasAssert.test(enlargedWarehouseManager.moveRobot(), 1381446L);
    }
}
