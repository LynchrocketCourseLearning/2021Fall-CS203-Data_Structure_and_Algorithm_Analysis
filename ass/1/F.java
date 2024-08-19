import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        String[] l = new String[t];
        for (int i = 0; i < t; i++) {
            l[i] = sc.nextLine();
        }

        for (int i = 0; i < t; i++) {
            int che = 0;
            String[] s = l[i].split("\\+");
            for (int j = 0; j < s.length; j++) {
                if (s[j].charAt(0) == '0') che++;
            }
            if (che == s.length) System.out.println("yes");
            else System.out.println("no");
        }
    }
}
