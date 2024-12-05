package rrx.aoc24.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PageOrderingRuleComputer {

    private final Set<int[]> pageNumberRules;
    private final List<int[]> allUpdates;
    private final List<int[]> incorrectlyOrderedUpdates;

    public PageOrderingRuleComputer(List<String> sleighLaunchSafetyManual) {
        pageNumberRules = new HashSet<>();
        allUpdates = new ArrayList<>();
        incorrectlyOrderedUpdates = new ArrayList<>();

        String separator = "\\|";
        for (String line : sleighLaunchSafetyManual) {
            if (line.isEmpty()) {
                separator = ",";
                continue;
            }

            var nums = Arrays.stream(line.split(separator))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (separator.equals(",")) {
                allUpdates.add(nums);
            } else {
                pageNumberRules.add(nums);
            }
        }
    }

    public long organiseUpdates() {
        long result = 0L;
        for (var update : allUpdates) {
            result += validateUpdate(update);
        }
        return result;
    }

    private long validateUpdate(int[] update) {
        for (int i = 0; i < update.length; i++) {
            if (validatePageNo(i, update) != -1) {
                incorrectlyOrderedUpdates.add(update);
                return 0L;
            }
        }
        return extractValidValue(update);
    }

    private int validatePageNo(int idx, int[] update) {
        for (var rule : pageNumberRules) {
            int i = checkRule(idx, update, rule);
            if (i != -1) return i;
        }
        return -1;
    }

    private int checkRule(int idx, int[] update, int[] rule) {
        if (update[idx] == rule[0]) {
            for (int i = 0; i < idx; i++) {
                if (update[i] == rule[1]) {
                    return i;
                }
            }
        } else if (update[idx] == rule[1]) {
            for (int i = update.length-1; i > idx; i--) {
                if (update[i] == rule[0]) {
                    return i;
                }
            }
        }
        return -1;
    }

    public long reorderIncorrectlyOrderedUpdates() {
        long result = 0L;
        for (var update : incorrectlyOrderedUpdates) {
            var reorderedList = reorder(Arrays.stream(update).boxed().toList());
            result += extractValidValue(listToArray(reorderedList));
        }
        return result;
    }

    private List<Integer> reorder(List<Integer> update) {
        List<Integer> mutableList = new ArrayList<>(update);
        for (int i = 0; i < update.size(); i++) {
            int toIdx = validatePageNo(i, listToArray(update));
            if (toIdx != -1) {
                Integer value = mutableList.remove(i);
                mutableList.add(toIdx, value);
                return reorder(mutableList);
            }
        }
        return mutableList;
    }

    private int extractValidValue(int[] update) {
        int middleIdx = update.length / 2;
        return update[middleIdx];
    }

    private int[] listToArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
