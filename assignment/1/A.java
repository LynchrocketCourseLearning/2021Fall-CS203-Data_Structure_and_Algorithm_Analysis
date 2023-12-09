import java.util.Scanner;

class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine();
        int[] al = new int[a];
        for (int i = 0; i < a; i++) {
            al[i] = sc.nextInt();
        }
        sc.nextLine();

        int b = sc.nextInt();
        sc.nextLine();
        int[] bl = new int[b];
        for (int j = 0; j < b; j++) {
            bl[j] = sc.nextInt();
        }
        sc.nextLine();


        for (long bc : bl) {
            int count = 0;
            for (long ac : al) {
                if (ac == bc) {
                    count = 1;
                    break;
                }
            }
            if (count == 1) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

    }
}