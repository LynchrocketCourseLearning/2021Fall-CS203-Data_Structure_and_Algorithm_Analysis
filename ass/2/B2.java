package dsaa;

import java.util.Scanner;

public class B2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        long[] l = new long[T];

        for (int i = 0; i < T; i++) l[i] = sc.nextLong();

        for (int i = 0; i < T; i++) {
            long ans =  l[i] * (l[i] + 1) * (l[i] + 2) / 6;
            System.out.println(ans);
        }

    }
}
