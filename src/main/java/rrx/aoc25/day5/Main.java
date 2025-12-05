package rrx.aoc25.day5;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var input = FileUtil.readFile("25/d05p1");

        KitchenInventoryMS kitchenInvMs = new KitchenInventoryMS(input);
        ChristmasAssert.test(kitchenInvMs.doInventoryManagement(), 679L);

        kitchenInvMs.documentAllValidIDs();
        ChristmasAssert.test(kitchenInvMs.doInventoryManagement(), 358155203664116L);
    }
}
