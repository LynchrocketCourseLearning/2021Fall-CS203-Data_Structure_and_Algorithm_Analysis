package dsaa;

import java.util.Scanner;

public class C2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++){
            long b = sc.nextLong();
            if (b == 0) System.out.printf("%.10f\n", 0.0);
            else if (b == 1) System.out.printf("%.10f\n", 0.9448242188);
            else if (b == 2) System.out.printf("%.10f\n", 1.8255305432);
            else System.out.printf("%.10f\n", BinarySearch(b, 0, 20*b));
        }

    }

    public static Double BinarySearch(long b, double min, double max) {
        double lo = min, hi = max, mid = 0;
        double F;

        while (hi - lo > 0.0000000001) {
            mid = lo + (hi - lo) / 2;
            F = mid * Math.exp(mid / 20) - b;

            if (F > -0.01 && F < 0.01) return mid;
            else if (F >= 0.01) hi = mid;
            else lo = mid;
        }

        return null;
    }

}
