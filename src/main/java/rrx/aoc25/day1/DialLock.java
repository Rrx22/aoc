package rrx.aoc25.day1;

import java.util.List;

public class DialLock {


    private final List<String> dialTurns;

    public DialLock(List<String> dialTurns) {
        this.dialTurns = dialTurns;
    }

    public long turnDials() {
        long password = 0;
        int dial = 50;

        for (var turn : dialTurns) {
            char dir = turn.charAt(0);
            int clicks = Integer.parseInt(turn.substring(1));

            int step = dir == 'L' ? -1 : 1;

            for (int i = 0; i < clicks; i++) {
                dial += step;

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
