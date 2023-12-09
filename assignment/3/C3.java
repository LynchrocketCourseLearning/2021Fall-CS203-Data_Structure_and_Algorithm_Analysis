package dsaa;

import java.util.Scanner;

public class C3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] test1 = new int[n];
            int[] test2 = new int[n];
            for (int j = 0; j < n; j++) {
                test1[j] = sc.nextInt();
                test2[j] = test1[j];
            }
//            for (int j = 0; j < n; j++) System.out.print(test[j]+" ");

            int secnt = select(test1);
            int incnt = insert(test2);
            for (int j = 0; j < n; j++) System.out.printf("%d ", test1[j]);
            System.out.println();
            if (secnt < incnt) System.out.println("Selection Sort wins!");
            else System.out.println("Insertion Sort wins!");
        }
    }

    public static int select(int[] a) {
        int cnt = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                cnt++;
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            cnt++;
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
        return cnt;
    }

    public static int insert(int[] a) {
        int cnt = 0;
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                cnt++;
                if (a[j - 1] > a[j]) {
                    cnt++;
                    int t = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = t;
                } else {
                    break;
                }
            }
        }
        return cnt;
    }


}
