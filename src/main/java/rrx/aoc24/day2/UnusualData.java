package rrx.aoc24.day2;

import rrx.ChristmasAssert;
import rrx.FileUtil;

public class UnusualData {

    public static void main(String[] args) {
        var unusualReports = FileUtil.readToIntArr("24/d02p1", " ");
        var unusualDataAnalyser = new UnusualDataAnalyzer();

        ChristmasAssert.test(unusualDataAnalyser.analyse(unusualReports), 299L);

        unusualDataAnalyser.enableProblemDampener();
        ChristmasAssert.test(unusualDataAnalyser.analyse(unusualReports), 364L);
    }
}
