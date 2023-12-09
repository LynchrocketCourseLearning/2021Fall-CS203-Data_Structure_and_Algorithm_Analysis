package dsaa;

import java.util.Scanner;

public class D2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextLong();

        int count = 0;
        long num = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                num = a[i] + a[j];
                if ((num & (num - 1)) == 0) count++;
            }
        }

        System.out.print(count);

    }
}
