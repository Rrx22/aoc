package rrx.aoc24.day7;

import rrx.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BridgeEvaluator {

    private final List<Pair<Long, long[]>> operators;
    private boolean isConcatOperandusEnabled;

    public BridgeEvaluator(List<String> operatorStrings) {
        this.operators = new ArrayList<>();
        for (String s : operatorStrings) {
            var split = s.split(":");
            var first = Long.parseLong(split[0]);
            var second = Arrays.stream(split[1].trim().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            this.operators.add(new Pair<>(first, second));
        }
    }

    public long calibrate() {
        long total = 0L;

        for (var pair : operators) {
            total += evaluateAllPossibilities(pair);
        }

        return total;
    }

    private long evaluateAllPossibilities(Pair<Long, long[]> pair) {
        List<Long> results = new ArrayList<>();
        if (pair.second().length == 0) {
            return 0L;
        }
        evaluate(pair.second(), 1, pair.second()[0], pair.first(), results);
        return results.isEmpty() ? 0L : results.getFirst();
    }

    private void evaluate(long[] nums, int idx, long currValue, long expectedValue, List<Long> results) {
        if (idx == nums.length) {
            if (currValue == expectedValue) results.add(currValue);
            return;
        }

        long nextNumber = nums[idx];
        evaluate(nums, idx + 1, currValue + nextNumber, expectedValue, results);
        evaluate(nums, idx + 1, currValue * nextNumber, expectedValue, results);
        if (isConcatOperandusEnabled)
            evaluate(nums, idx + 1, Long.parseLong(String.valueOf(currValue) + nextNumber), expectedValue, results);
    }

    public void enableConcatOperandus() {
        isConcatOperandusEnabled = true;
    }
}
