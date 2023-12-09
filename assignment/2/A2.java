package dsaa;

import java.util.Scanner;

public class A2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] al = new int[n];

        for (int i = 0; i < n; i++) al[i] = sc.nextInt();

        int T = sc.nextInt();
        int[] test = new int[T];
        for (int i = 0; i < T; i++) {
            test[i] = sc.nextInt();
        }

        for (int i = 0; i < T; i++) {
            if (BinarySearch(test[i], al)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static boolean BinarySearch(int x, int[] l) {
        int lo = 0, hi = l.length - 1, mid = 0;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;

            if (x == l[mid]) return true;
            else if (x < l[mid]) hi = mid - 1;
            else lo = mid + 1;
        }

        return false;
    }
}
