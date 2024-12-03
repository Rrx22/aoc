package rrx.aoc24.day3;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CorruptedMemoryHelper {

    private boolean isInstructionsEnabled;
    private boolean doMul = true;
    private static final Pattern regex = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");

    long scan(List<String> file) {
        return file.stream()
                .mapToLong(this::processUncorruptedMulInstructions)
                .sum();
    }

    private long processUncorruptedMulInstructions(String line) {
        long sum = 0L;
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
                sum += Long.parseLong(nums[0]) * Long.parseLong(nums[1]);
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
