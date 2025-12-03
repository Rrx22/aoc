package rrx.aoc25.day3;

import java.util.List;

class ElevatorLobby {

    private final List<String> joltages;
    private boolean considerStiction;

    public ElevatorLobby(List<String> joltages) {
        this.joltages = joltages;
    }

    public long doSomething() {
        return considerStiction
                ? fixElevator()
                : fixEscalator();
    }

    private long fixEscalator() {
        long highJoltageSum = 0L;

        for (var joltage : joltages) {
            char[] highJoltage = new char[]{'1', '1'};
            char[] arr = joltage.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char num = arr[i];


                if (i != arr.length-1 && num > highJoltage[0]) {
                    highJoltage[0] = num;
                    highJoltage[1] = arr[i+1];
                } else if (num > highJoltage[1]) {
                    highJoltage[1] = num;
                }
            }
            highJoltageSum += Long.parseLong(String.valueOf(highJoltage));
        }

        return highJoltageSum;
    }

    private long fixElevator() {
        long highJoltageSum = 0L;

        for (String joltage : joltages) {
            char[] arr = joltage.toCharArray();
            int maxDigits = 12;

            char[] highJoltage = new char[maxDigits];
            int currUpdateIdx = 0;
            int nextStart = 0;

            while (maxDigits > 0) {
                int lastIdxForStartPicking = arr.length - maxDigits;
                char best = '0';
                int bestIdx = nextStart;

                for (int i = nextStart; i <= lastIdxForStartPicking; i++) {
                    if (arr[i] > best) {
                        best = arr[i];
                        bestIdx = i;
                    }
                }

                highJoltage[currUpdateIdx++] = best;
                maxDigits--;
                nextStart = bestIdx + 1;
            }

            highJoltageSum += Long.parseLong(new String(highJoltage));
        }

        return highJoltageSum;
    }

    public void startConsideringStiction() {
        considerStiction = true;
    }
}
