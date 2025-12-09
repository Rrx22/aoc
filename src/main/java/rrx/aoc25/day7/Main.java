package rrx.aoc25.day7;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {
        var grid = FileUtil.readToGrid("25/d07p1");

        TeleportationDevice teleportationDevice = new TeleportationDevice(grid);
//        ChristmasAssert.test(teleportationDevice.fixTachyonManifolds(), 21L);
        ChristmasAssert.test(teleportationDevice.fixTachyonManifolds(), 1658L);

//        ChristmasAssert.test(teleportationDevice.fixQuantumTachyonManifolds(), 0L);
    }
}
