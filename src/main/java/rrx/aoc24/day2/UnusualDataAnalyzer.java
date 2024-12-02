package rrx.aoc24.day2;

public class UnusualDataAnalyzer {

    private boolean isProblemDampenerEnabled;

    long analyse(int[][] reports) {
        long safeReports = 0;

        for (int[] report : reports) {
            boolean safe = isSafe(report);
            if (isProblemDampenerEnabled && !safe) {
                safe = dampenProblems(report);
            }
            if (safe) safeReports++;
        }
        return safeReports;
    }

    private boolean dampenProblems(int[] report) {
        for (int i = 0; i < report.length; i++) {
            int[] newArr = new int[report.length - 1];
            int index = 0;
            for (int j = 0; j < report.length; j++) {
                if (j != i) {
                    newArr[index++] = report[j];
                }
            }
            if (isSafe(newArr)) return true;
        }
        return false;
    }

    private boolean isSafe(int[] report) {
        boolean isIncreasing = report[0] < report[1];
        for (int j = 1; j < report.length; j++) {
            if (doValueCheck(report[j], report[j - 1]) ||
                    doConsistencyCheck(report[j], report[j - 1], isIncreasing)) {
                return false;
            }
        }
        return true;
    }

    private boolean doValueCheck(int curr, int prev) {
        int diff = Math.abs(curr - prev);
        return diff < 1 || diff > 3;
    }

    private boolean doConsistencyCheck(int curr, int prev, boolean isIncreasing) {
        if (curr == prev) return false;
        return isIncreasing == curr < prev;
    }

    void enableProblemDampener() {
        isProblemDampenerEnabled = true;
    }
}
