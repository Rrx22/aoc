package rrx.aoc24.day8;

import rrx.ChristmasAssert;
import rrx.utils.FileUtil;

class TopSecretEasterBunnyInstallation {

    public static void main(String[] args) {

        FrequencySpy frequencySpy = new FrequencySpy(FileUtil.readToGrid("24/d08p1"));
        ChristmasAssert.test(frequencySpy.mapAntiNodes(), 261L);

        frequencySpy.resetAndEnableCheckingHarmonics();
        ChristmasAssert.test(frequencySpy.mapAntiNodes(), 898L);
    }
}
