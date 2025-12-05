package rrx.aoc25.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class KitchenInventoryMS {

    private final List<Range> freshIngredientRanges;
    private boolean startDocumentingAllIDs;
    private long freshIngredientCount;

    public KitchenInventoryMS(List<String> input) {
        freshIngredientRanges = new ArrayList<>();
        freshIngredientCount = 0L;
        boolean changeBehaviour = false;
        for (String line : input) {
            if (line.isEmpty()) {
                changeBehaviour = true;
                continue;
            }
            if (!changeBehaviour) {
                freshIngredientRanges.add(new Range(line));
            } else {
                long ingredientID = Long.parseLong(line);
                boolean isFresh = freshIngredientRanges.stream()
                        .anyMatch(range -> range.min <= ingredientID && range.max >= ingredientID);
                if (isFresh) {
                    freshIngredientCount++;
                }
            }
        }
    }

    public long doInventoryManagement() {
        return startDocumentingAllIDs
                ? getFreshIngredientIDCount()
                : getFreshIngredientCount();
    }

    private long getFreshIngredientCount() {
        return freshIngredientCount;
    }

    private long getFreshIngredientIDCount() {
        return mergeRanges(freshIngredientRanges).stream()
                .mapToLong(r -> r.max + 1 - r.min)
                .sum();
    }

    private List<Range> mergeRanges(List<Range> ranges) {
        List<Range> sorted = new ArrayList<>(ranges);
        sorted.sort(Comparator.comparingLong(Range::min));

        List<Range> result = new ArrayList<>();
        Range current = sorted.getFirst();
        for (int i = 1; i < sorted.size(); i++) {
            Range next = sorted.get(i);
            if (next.min <= current.max) { // overlap?
                long min = current.min();
                long max = Math.max(current.max(), next.max());
                current = new Range(min, max);
            } else {
                result.add(current);
                current = next;
            }
        }
        result.add(current);
        return result;
    }


    public void documentAllValidIDs() {
        startDocumentingAllIDs = true;
    }

    record Range(long min, long max) {
        Range(String line) {
            String[] ranges = line.split("-");
            long min = Long.parseLong(ranges[0]);
            long max = Long.parseLong(ranges[1]);
            this(min, max);
        }
    }
}
