import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long[][] a = new Long[5][2];
        Long[] b;
        int count = 0;
        while (sc.hasNext()) {
            for (int k = 0; k < 5; k++) {
                if (sc.hasNext()) {
                    a[k][0] = sc.nextLong();
                    a[k][1] = sc.nextLong();
                    sc.nextLine();
                    count++;
                }
            }
            for (int i = 0; i < count; i++) {
                b = Magic(a[i][0], a[i][1]);
                a[i][0] = b[0];
                a[i][1] = b[1];
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.printf("%d %d\n", a[i][0], a[i][1]);
        }

    }


    public static boolean isMagic(String s) {
        if (s.length() == 1) return s.equals("0") || s.equals("1") || s.equals("8");
        if (s.length() == 2) return s.equals("11") || s.equals("69") || s.equals("88") || s.equals("96");
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(s.length() - 1 - i);
            if (c1 == c2 && (c2 == '0' || c2 == '1' || c2 == '8')) continue;
            else if ((c1 == '6' && c2 == '9') || (c1 == '9' && c2 == '6')) continue;
            else return false;
        }
        return true;
    }

    public static Long[] Magic(Long L, Long R) {
        long max = L, min = R;
        String s;
        for (long i = L; i < R; ) {
            s = String.valueOf(i);
            StringBuilder inter = new StringBuilder();
            if (s.charAt(0) == '2' || s.charAt(0) == '3' || s.charAt(0) == '4' || s.charAt(0) == '5') {
                inter.append('6');
                for (int j = 1; j < s.substring(1).length(); j++) inter.append("0");
                inter.append('9');
                i = Long.parseLong(inter.toString());
            } else if (s.charAt(0) == '7') {
                inter.append('8');
                for (int j = 1; j < s.substring(1).length(); j++) inter.append("0");
                inter.append('8');
                i = Long.parseLong(inter.toString());
            }

            if (isMagic(String.valueOf(i))) {
                min = i;
                break;
            }

            switch ((int) i % 10) {
                case 0:
                    i++;
                    break;
                case 1:
                    i += 5;
                    break;
                case 6:
                    i += 2;
                    break;
                case 8:
                    i++;
                    break;
                case 9:
                    i++;
                    break;
            }
        }
        for (long i = R; i > L; ) {
            s = String.valueOf(i);
            StringBuilder inter = new StringBuilder();
            if (s.charAt(0) == '7') {
                inter.append('6');
                if (s.length() % 2 == 0) {
                    for (int j = 1; j < (s.length() - 2) / 2; j++) inter.append("9");
                    for (int j = 1; j < (s.length() - 2) / 2; j++) inter.append("6");
                } else {
                    for (int j = 0; j < (s.length() - 3) / 2; j++) inter.append("9");
                    inter.append("8");
                    for (int j = 0; j < (s.length() - 3) / 2; j++) inter.append("6");
                }
                inter.append('9');
                i = Long.parseLong(inter.toString());
            } else if (s.charAt(0) == '2' || s.charAt(0) == '3' || s.charAt(0) == '4' || s.charAt(0) == '5') {
                inter.append('1');
                if (s.length() % 2 == 0) {
                    for (int j = 1; j < (s.length() - 2) / 2; j++) inter.append("9");
                    for (int j = 1; j < (s.length() - 2) / 2; j++) inter.append("6");
                } else {
                    for (int j = 0; j < (s.length() - 3) / 2; j++) inter.append("9");
                    inter.append("8");
                    for (int j = 0; j < (s.length() - 3) / 2; j++) inter.append("6");
                }
                inter.append('1');
                i = Long.parseLong(inter.toString());
            }

            if (isMagic(String.valueOf(i))) {
                max = i;
                break;
            }
            switch ((int) (i % 10)) {
                case 0:
                    i--;
                    break;
                case 1:
                    i--;
                    break;
                case 6:
                    i -= 5;
                    break;
                case 8:
                    i -= 2;
                    break;
                case 9:
                    i--;
                    break;
            }
        }
        return new Long[]{min, max};
    }
}
