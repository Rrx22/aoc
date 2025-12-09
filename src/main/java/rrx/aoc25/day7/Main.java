package rrx.aoc25.day7;

import rrx.utils.FileUtil;
import rrx.ChristmasAssert;

class Main {
    static void main() {

        TeleportationDevice teleportationDevice = new TeleportationDevice(FileUtil.readToGrid("25/d07p1"));
        ChristmasAssert.test(teleportationDevice.fixTachyonManifolds(), 1658L);

        teleportationDevice = new TeleportationDevice(FileUtil.readToGrid("25/d07p1"));
        ChristmasAssert.test(teleportationDevice.fixQuantumTachyonManifolds(), 53916299384254L);
    }
}
