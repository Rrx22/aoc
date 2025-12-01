package rrx.aoc25.day1;

import java.util.List;

record DialLock(List<String> dialTurns) {

    public long turnDials() {
        long password = 0;
        int dial = 50;
        for (var turn : dialTurns) {
            int clicks = Integer.parseInt(turn.substring(1));
            password += clicks / 100;

            for (int i = 0; i < clicks % 100; i++) {
                dial += (turn.charAt(0) == 'L') ? -1 : 1;
                if (dial < 0) dial = 99;
                else if (dial > 99) dial = 0;
                if (dial == 0) {
                    password++;
                }
            }
        }
        return password;
    }
}
