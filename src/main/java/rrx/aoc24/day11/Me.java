package rrx.aoc24.day11;

class Me {

    private Me() {
    }

    public static long blinkPart2(int blinks, String pebbles) {
        // todo
        //  - make it recursive. analyze each stone recursively, even after each split / change.
        //  - keep track of changes and make use of memoization to not loop over known loops over and over again
        //    - this means knowing where it will end up at blink 75
        return blinks + pebbles.length();
    }

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
