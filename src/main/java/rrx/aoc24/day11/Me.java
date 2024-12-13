package rrx.aoc24.day11;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Me {

    private Me() {
    }

    public static long blinkPart2(int blinks, String pebbles) {
        var initialPebbles = Arrays.stream(pebbles.split(" "))
                .map(Long::parseLong)
                .toList();

        long totalStones = 0;
        for (long count : memoizationBlink(blinks, initialPebbles)) {
            totalStones += count;
        }
        return totalStones;
    }

    private static Collection<Long> memoizationBlink(int blinks, List<Long> initialPebbles) {
        Map<Long, Long> currPebbles = new HashMap<>();
        for (long stone : initialPebbles) {
            currPebbles.put(stone, currPebbles.getOrDefault(stone, 0L) + 1);
        }

        // Track seen states for cycle detection
        Map<Map<Long, Long>, Integer> seenStates = new HashMap<>();
        int cycleStart = -1;
        int cycleLength = -1;

        for (int blink = 0; blink < blinks; blink++) {
            // Check if we've seen this state before
            if (seenStates.containsKey(currPebbles)) {
                cycleStart = seenStates.get(currPebbles);
                cycleLength = blink - cycleStart;
                break;
            }

            // Save the current state
            seenStates.put(new HashMap<>(currPebbles), blink);

            // Prepare for the next state
            Map<Long, Long> nextStones = new HashMap<>();
            for (Map.Entry<Long, Long> entry : currPebbles.entrySet()) {
                determineAfterBlink(entry, nextStones);
            }

            currPebbles = nextStones;
        }

        // If a cycle was detected, skip remaining blinks
        if (cycleLength > 0) {
            int remainingBlinks = (blinks - cycleStart) % cycleLength;
            for (int i = 0; i < remainingBlinks; i++) {
                Map<Long, Long> nextPebbles = new HashMap<>();
                for (Map.Entry<Long, Long> entry : currPebbles.entrySet()) {
                    determineAfterBlink(entry, nextPebbles);
                }
                currPebbles = nextPebbles;
            }
        }
        return currPebbles.values();
    }

    private static void determineAfterBlink(Map.Entry<Long, Long> entry, Map<Long, Long> nextPebbles) {
        long pebble = entry.getKey();
        long count = entry.getValue();

        if (pebble == 0) {
            nextPebbles.put(1L, nextPebbles.getOrDefault(1L, 0L) + count);
        } else if (String.valueOf(pebble).length() % 2 == 0) {
            long first = Long.parseLong(String.valueOf(pebble).substring(0, String.valueOf(pebble).length() / 2));
            long second = Long.parseLong(String.valueOf(pebble).substring(String.valueOf(pebble).length() / 2));
            nextPebbles.put(first, nextPebbles.getOrDefault(first, 0L) + count);
            nextPebbles.put(second, nextPebbles.getOrDefault(second, 0L) + count);
        } else {
            long n = pebble * 2024;
            nextPebbles.put(n, nextPebbles.getOrDefault(n, 0L) + count);
        }
    }

    // brute force part 1
    public static long blink(int blinks, String pebbles) {
        StringBuilder newPebbles = new StringBuilder(pebbles);

        for (int i = 0; i < blinks; i++) {
            String beforeBlink = newPebbles.toString();
            String afterBlink = blink(beforeBlink.split(" "));
            newPebbles.setLength(0);
            newPebbles.append(afterBlink);
        }

        return newPebbles.toString()
                .split(" ")
                .length;
    }

    private static String blink(String[] pebbles) {
        StringBuilder sb = new StringBuilder();
        for (var s : pebbles) {
            if (s.equals("0")) {
                sb.append("1").append(" ");
            } else if (s.length() % 2 == 0) {
                long first = Long.parseLong(s.substring(0, s.length() / 2));
                long second = Long.parseLong(s.substring(s.length() / 2));
                sb.append(first)
                        .append(" ")
                        .append(second)
                        .append(" ");
            } else {
                long n = Long.parseLong(s) * 2024;
                sb.append(n).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
