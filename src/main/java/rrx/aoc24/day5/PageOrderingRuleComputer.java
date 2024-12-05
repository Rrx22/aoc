package rrx.aoc24.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PageOrderingRuleComputer {

    private Set<int[]> pageNumberRules;
    private List<int[]> allUpdates;
    private List<int[]> incorrectlyOrderedUpdates;

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

    private int validatePageNo(int idx, List<Integer> update) {
        int[] arr = update.stream().mapToInt(Integer::intValue).toArray();
        return validatePageNo(idx, arr);
    }

    private int validatePageNo(int idx, int[] update) {
        for (var rule : pageNumberRules) {
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
        }
        return -1;
    }

    public long reorderIncorrectlyOrderedUpdates() {
        long result = 0L;

        for (var update : incorrectlyOrderedUpdates) {
            var reorderedList = reorder(Arrays.stream(update).boxed().toList());
            result += extractValidValue(reorderedList);
        }

        return result;
    }

    private List<Integer> reorder(List<Integer> update) {
        List<Integer> mutableList = new ArrayList<>(update);
        for (int i = 0; i < update.size(); i++) {
            int toIdx = validatePageNo(i, update);
            if (toIdx != -1) {
                Integer value = mutableList.remove(i);
                mutableList.add(toIdx, value);
                return reorder(mutableList);
            }
        }

        return mutableList;
    }

    private int extractValidValue(List<Integer> update) {
        int[] arr = update.stream().mapToInt(Integer::intValue).toArray();
        return extractValidValue(arr);
    }

    private int extractValidValue(int[] update) {
        int middleIdx = update.length / 2;
        return update[middleIdx];
    }

}
