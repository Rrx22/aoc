package rrx.aoc25.day2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class ProductIdValidator {

    private final List<IDRange> productIdRanges;
    private boolean fixTheScanConditions;

    public ProductIdValidator(String input) {
        productIdRanges = Arrays.stream(input.split(","))
                .map(IDRange::new)
                .toList();
    }

    public long scan() {
        long sumOfInvalidIDs = 0L;
        String p1regex = "^(.+?)\\1$";
        String p2regex = "^(.+?)\\1+$";
        Pattern regex = Pattern.compile(fixTheScanConditions ? p2regex : p1regex);

        for (var range : productIdRanges) {
            for (long i = range.min(); i <= range.max(); i++) {
                if (regex.matcher(String.valueOf(i)).matches()) {
                    sumOfInvalidIDs += i;
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
