package rrx.aoc24.day13;

import rrx.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

class MathBeatsMachine {

    private final List<ClawMachine> clawMachines;

    public MathBeatsMachine(List<String> input, long addition) {
        clawMachines = parseClawMachines(input, addition);
    }

    public long analyse() {
        long totalCost = 0L;
        for (var clawMachine : clawMachines) {
            long cost = useSubstitution(clawMachine.btnA, clawMachine.btnB, clawMachine.prize);
            if (cost > 0L) {
//            long result = analyseClawMachine(clawMachine); // too slow
//            if (result.isFeasible) {
//                long cost = result.cost;
                System.out.println(clawMachine + " cost: " + cost);
                totalCost += cost;
            } else {
                System.out.println(clawMachine + " not feasible");
            }
        }
        return totalCost;
    }

    public long useSubstitution(ButtonConf btnA, ButtonConf btnB, ButtonConf prize) {
        long numerator = prize.x * btnA.y - prize.y * btnA.x;
        long b = numerator / (btnB.x * btnA.y - btnB.y * btnA.x);
        long remX = prize.x - b * btnB.x;
        long l = btnA.x == 0 ? prize.y : remX;
        long r = btnA.x == 0 ? btnA.y : btnA.x;
        long a = l / r;
        return (a * btnA.y + b * btnB.y == prize.y && l % r == 0) ? 3 * a + b : 0;

    }

    private long analyseClawMachine(ClawMachine clawMachine) {
        long aPresses = 0L;
        long bPresses = Long.MAX_VALUE;
        long cheapestWin = Long.MAX_VALUE;
        boolean isFeasible = false;


        if (!clawMachine.winningIsFeasible()) {
            return 0L;
        }

        while (bPresses > 0L) {
            double possibleBPresses = doDiaphantineEquation(aPresses, clawMachine);
            if (possibleBPresses < 0) break;
            bPresses = (long) possibleBPresses;
            if (possibleBPresses != (long) possibleBPresses || !clawMachine.solutionWorks(aPresses, bPresses)) {
                aPresses++;
                continue;
            }
            long totalCost = aPresses * 3 + bPresses;
            if (totalCost < cheapestWin) {
                cheapestWin = totalCost;
            }
            isFeasible = true;
            aPresses++;
        }
        return isFeasible ? cheapestWin : 0L;
    }

    private double doDiaphantineEquation(long aPresses, ClawMachine clawMachine) {
        return (double) (clawMachine.prize.x - clawMachine.btnA.x * aPresses) / clawMachine.btnB.x;
    }

    private List<ClawMachine> parseClawMachines(List<String> input, long addition) {
        List<ClawMachine> list = new ArrayList<>();
        ButtonConf btnA = null, btnB = null, prize;
        for (var line : input) {
            if (line.isEmpty()) continue;
            int idxX = line.indexOf('X');
            int idxY = line.indexOf('Y');
            int idxComma = line.indexOf(',');
            long x = Long.parseLong(line.substring(idxX + 2, idxComma));
            long y = Long.parseLong(line.substring(idxY + 2));
            if (line.startsWith("Button A")) btnA = new ButtonConf(x, y);
            if (line.startsWith("Button B")) btnB = new ButtonConf(x, y);
            if (line.startsWith("Prize")) {
                x += addition;
                y += addition;
                prize = new ButtonConf(x, y);
                list.add(new ClawMachine(btnA, btnB, prize));
            }
        }
        return list;
    }

    record Result(long aPresses, long bPresses, long cost, boolean isFeasible) {

    }

    record ClawMachine(ButtonConf btnA, ButtonConf btnB, ButtonConf prize) {
        public boolean solutionWorks(long aPresses, long bPresses) {
            long xLocation = aPresses * btnA.x + bPresses * btnB.x;
            long yLocation = aPresses * btnA.y + bPresses * btnB.y;
            return prize.x == xLocation && prize.y == yLocation;
        }

        public boolean winningIsFeasible() {
            long gcdX = MathUtil.gcd(btnA.x, btnB.x);
            long gcdY = MathUtil.gcd(btnA.y, btnB.y);
            return prize.x % gcdX == 0 && prize.y % gcdY == 0;
        }
    }

    record ButtonConf(long x, long y) {
    }
}
