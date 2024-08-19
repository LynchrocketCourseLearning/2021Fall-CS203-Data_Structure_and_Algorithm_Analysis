import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();

        int maxa = 1, mina = h, maxb = 1, minb = h;
        int suma = 0, sumb = 0;
        int inter = 0;

        for (int i = 0; i < n - 1; i++) {
            inter = sc.nextInt();
            suma += inter;
            if (mina > inter) mina = inter;
            if (maxa < inter) maxa = inter;
        }

        for (int i = 0; i < n - 1; i++) {
            inter = sc.nextInt();
            sumb += inter;
            if (minb > inter) minb = inter;
            if (maxb < inter) maxb = inter;
        }

        if (n == 2) System.out.print("IMPOSSIBLE");
        else {
            int sumdiff = suma - sumb;
            int count = h - 1;
            boolean che = false;

            //An max, Bn min
            if (sumdiff > mina - maxb && maxa - minb <= count) {
                count = maxa - minb;
                che = true;
            }
            //An min, Bn max
            if (sumdiff > maxa - minb && mina - maxb <= count) {
                count = 1 - h;
                che = true;
            }

            //An, Bn are max
            if (sumdiff > mina - minb && maxa - h <= count) {
                count = maxa - h;
                che = true;
            }
            //An, Bn are min
            if (sumdiff > maxa - maxb && 1 - minb <= count) {
                count = 1 - minb;
                che = true;
            }

            //An, Bn are not extremum
            if (maxa + mina - sumdiff - maxb - minb + 1 >= 1 - h && maxa + mina - sumdiff - maxb - minb + 1 <= count) {
                count = maxa + mina - sumdiff - maxb - minb + 1;
                che = true;
            }

            if (che) System.out.print(count);
            else System.out.print("IMPOSSIBLE");
        }
    }

}
