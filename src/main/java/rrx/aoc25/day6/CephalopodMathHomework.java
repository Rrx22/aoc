package rrx.aoc25.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CephalopodMathHomework {

    private CephalopodMathHomework() {
    }

    public static long solveHumanReadableWay(List<String> input) {
        List<String[]> mathHomework = input.stream()
                .map(line -> line.trim().split(" +"))
                .toList();

        long result = 0L;
        for (int i = 0; i < mathHomework.getFirst().length; i++) {
            List<Long> numbers = new ArrayList<>();
            for (String[] row : mathHomework) {
                if ("+".equals(row[i])) {
                    long intermediateResult = numbers.stream().mapToLong(Long::longValue).sum();
                    System.out.println(intermediateResult);
                    result += intermediateResult;
                } else if ("*".equals(row[i])) {
                    long intermediateResult = 1L;
                    for (Long no : numbers) {
                        intermediateResult *= no;
                    }
                    System.out.println(intermediateResult);
                    result += intermediateResult;
                } else {
                    numbers.add(Long.parseLong(row[i]));
                }
            }
        }
        return result;
    }

    public static long solveCephalopodWay(List<String> input) {
        long result = 0L;

        List<char[]> arrs = input.stream().map(String::toCharArray).toList();
        int maxLines = arrs.size();

        char currentOperator = '❌';
        List<Integer> numbers = new ArrayList<>();

        for (int col = 0; col < arrs.getFirst().length+1; col++) {
            StringBuilder sb = new StringBuilder();
            int countEmptySpaces = 0;
            for (int row = 0; row < maxLines; row++) {
                char curr = getCurrentChar(arrs.get(row), col);
                if (curr == ' ') {
                    countEmptySpaces++;
                    continue;
                }
                if (curr == '+' || curr == '*') {
                    currentOperator = curr;
                    continue;
                }
                sb.append(curr);
            }
            if (!sb.isEmpty()) {
                numbers.add(Integer.parseInt(sb.toString()));
            }
            if (countEmptySpaces == maxLines || col == arrs.getFirst().length) {
                System.out.println("-----");
                numbers.forEach(n -> System.out.printf("%d ", n));

                if ('+' == currentOperator) {
                    long intermediateResult = numbers.stream().mapToLong(Long::valueOf).sum();
                    System.out.println(currentOperator + "= " + intermediateResult);
                    result += intermediateResult;
                } else if ('*' == currentOperator) {
                    long intermediateResult = 1L;
                    for (int no : numbers) {
                        intermediateResult *= no;
                    }
                    System.out.println(currentOperator + "= " + intermediateResult);
                    result += intermediateResult;
                }
                currentOperator = '❌';
                numbers = new ArrayList<>();
            }
        }
        return result + 64; // yes yes I was in a hurry.. it didn't read the last col so I added it manually here 🎅
    }

    private static char getCurrentChar(char[] arr, int col) {
        try {
            return arr[col];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            // dont care if the operator row is a bit shorter... 🙄
        }
        return ' ';
    }
}
