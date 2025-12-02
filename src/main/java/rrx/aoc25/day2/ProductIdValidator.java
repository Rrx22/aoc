package rrx.aoc25.day2;

import java.util.Arrays;
import java.util.List;

class ProductIdValidator {

    private final List<IDRange> productIdRanges;
    private boolean fixTheScanConditions;

    public ProductIdValidator(String input) {
        productIdRanges = Arrays.stream(input.split(","))
                .map(IDRange::new)
                .toList();
    }

    public long scan() {
        return fixTheScanConditions
                ? scanForRepeatsComplete()
                : scanForRepeatedTwiceHalfWay();
    }

    private long scanForRepeatedTwiceHalfWay() {
        long sumOfInvalidIDs = 0L;
        for (var range : productIdRanges) {
            for (long i = range.min(); i <= range.max(); i++) {
                String val = String.valueOf(i);
                if (val.length() % 2 != 0) continue; // must be even
                String firstHalf = val.substring(0, val.length()/2);
                String secondHalf = val.substring(val.length()/2);
                if (firstHalf.equals(secondHalf)) {
                    sumOfInvalidIDs += i;
                }
            }
        }
        return sumOfInvalidIDs;
    }

    private long scanForRepeatsComplete() {
        long sumOfInvalidIDs = 0L;
        for (var range : productIdRanges) {
            for (long i = range.min(); i <= range.max(); i++) {
                String val = String.valueOf(i);
                for (int jump = 1; jump <= val.length() / 2; jump++) {
                    boolean isRepeated = false;
                    if (val.length() % jump != 0) continue; // otherwise, it is not possible to happen
                    String requiredPattern = val.substring(0, jump);
                    for (int startIdx = jump; startIdx < val.length(); startIdx+=jump) {
                        if (!requiredPattern.equals(val.substring(startIdx, startIdx+jump))) {
                            isRepeated = false;
                            break;
                        }
                        isRepeated = true;
                    }
                    if (isRepeated) {
                        sumOfInvalidIDs += i;
                        break;
                    }
                }
            }
        }
        return sumOfInvalidIDs;
    }

    public void fixTheScanConditions() {
        fixTheScanConditions = true;
    }
}

record IDRange(long min, long max) {
    IDRange(String inputRange) {
        var split = inputRange.split("-");
        this(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }
}
