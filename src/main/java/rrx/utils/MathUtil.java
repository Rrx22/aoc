package rrx.utils;

import rrx.ChristmasException;

public class MathUtil {

    private MathUtil() {
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * aka straight-line distance
     * <a href="https://en.wikipedia.org/wiki/Euclidean_distance">See wikipedia</a>
     */
    public static double euclideanDistance(long[] p, long[] q) {
        if (p.length != q.length) {
            throw new ChristmasException("wtf are you doing");
        }
        double sum = 0;
        for (int i = 0; i < p.length; i++) {
            sum += Math.pow(p[i] - q[i], 2);
        }
        return Math.sqrt(sum);
    }

}
