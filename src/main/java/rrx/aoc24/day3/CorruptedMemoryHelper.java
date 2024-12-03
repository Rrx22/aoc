package rrx.aoc24.day3;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CorruptedMemoryHelper {

    private boolean isInstructionsEnabled;
    private static final Pattern regex = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
    private boolean doMul = true;

    long scan(List<String> file) {

        long sum = 0L;

        for (String line : file) {
            sum = processUncorruptedMulInstructions(line, sum);
        }

        return sum;

    }

    private long processUncorruptedMulInstructions(String line, long sum) {
        Matcher matcher = regex.matcher(line);
        while (matcher.find()) {
            String match = matcher.group();

            if (match.contains("do")) {
                checkInstructions(match);
                continue;
            }

            if (doMul) {
                String[] nums = matcher.group()
                        .substring(4)
                        .replace(")", "")
                        .split(",");
                sum += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
            }
        }

        return sum;
    }

    private void checkInstructions(String match) {
        if (!isInstructionsEnabled || match.equals("do()")) {
            doMul = true;
        } else if (match.equals("don't()")) {
            doMul = false;
        }
    }

    void enableInstructions() {
        isInstructionsEnabled = true;
    }
}
