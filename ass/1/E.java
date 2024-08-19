import java.util.*;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] a = new String[5][2];
        String[] b;
        int count = 0;
        while (sc.hasNext()) {
            for (int k = 0; k < 5; k++) {
                if (sc.hasNext()) {
                    a[k][0] = sc.next();
                    a[k][1] = sc.next();
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
            System.out.printf("%s %s\n", a[i][0], a[i][1]);
        }

    }

    public static String[] Magic(String L, String R) {
        String min = "", max = "";
        //find min
        boolean che = false;
        List<String> larr1 = findMagic(L.length());
        for (int i = 0; i <= larr1.size() - 1; i++) {
            if (Long.parseLong(larr1.get(i)) >= Long.parseLong(L)) {
                min = larr1.get(i);
                che = true;
                break;
            }
        }
        if (!che) {
            List<String> larr2 = findMagic(L.length() + 1);
            min = larr2.get(0);
        }

        //find max
        che = false;
        List<String> rarr1 = findMagic(R.length());
        for (int i = rarr1.size() - 1; i >= 0; i--) {
            if (Long.parseLong(rarr1.get(i)) <= Long.parseLong(R)) {
                max = rarr1.get(i);
                che = true;
                break;
            }
        }
        if (!che) {
            List<String> rarr3 = findMagic(R.length() - 1);
            max = rarr3.get(rarr3.size() - 1);
        }

        return new String[]{min, max};
    }

    public static List<String> findMagic(int n) {
        if (n == 1) return Arrays.asList("0", "1", "8");
        if (n == 2) return Arrays.asList("11", "69", "88", "96");

        List<String>[] arr = new List[n + 1];
        arr[1] = Arrays.asList("0", "1", "8");
        arr[2] = Arrays.asList("00", "11", "69", "88", "96");

        for (int i = 3; i <= n; i++) {
            List<String> tmp = new ArrayList<>();
            for (String s : arr[i - 2]) {
                if (i != n) {
                    tmp.add("0" + s + "0");
                }
                tmp.add("1" + s + "1");
                tmp.add("6" + s + "9");
                tmp.add("8" + s + "8");
                tmp.add("9" + s + "6");
            }
            tmp.sort(Comparator.naturalOrder());
            arr[i] = tmp;
        }
        return arr[n];
    }

}
