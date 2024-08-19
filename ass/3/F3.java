package dsaa;

import java.io.*;
import java.util.*;

public class F3 {
    static class plant {
        private long height = 0;
        private long strength = 0;
        private long diff = 0;

        public plant(long height, long strength) {
            this.height = height;
            this.strength = strength;
            this.diff = height - strength;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();

        plant[] list = new plant[n];
        for (int i = 0; i < n; i++) {
            long height = in.nextLong();
            long strength = in.nextLong();
            list[i] = new plant(height, strength);
        }

        if (q == 0) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += list[i].strength;
            }
            out.print(sum);
        } else if (p == 0) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (q > 0 && list[i].diff >= 0) {
                    sum += list[i].height;
                    q--;
                } else {
                    sum += list[i].strength;
                }
            }
            out.print(sum);
        } else {
            sort(list, 0, n - 1);

//            for (int i = 0; i < n; i++) {
//                out.print(list[i].diff + " ");
//            }
//            out.println("");

            long basic = 0;
            long che = 0;
            //basic
            for (int i = 0; i < n; i++) {
                if (q > 0 && list[i].diff >= 0) {
                    basic += list[i].height;
                    che++;
                    q--;
                } else {
                    basic += list[i].strength;
                }
            }

            long sum = 0;
            long max = basic;
            for (int i = 0; i < n; i++) {
                if (list[i].diff >= 0 && che > 0) {//
                    sum = basic - list[i].height + (long) (list[i].height * Math.pow(2, p));
                    che--;
                    if (che == 0) {
                        basic = basic - list[i].height + list[i].strength;
                    }
                } else if (che <= 0) {//
                    sum = basic - list[i].strength + (long) (list[i].height * Math.pow(2, p));
                }
                if (sum > max) max = sum;
            }
            out.print(max);

        }

        out.close();
    }

    //h-s
    public static void sort(plant[] a, int low, int high) {
        int mid = low + (high - low) / 2;
        if (low < high) {
            sort(a, low, mid);
            sort(a, mid + 1, high);

            merge(a, low, mid, high);
        }
    }

    public static void merge(plant[] a, int low, int mid, int high) {
        plant[] temp = new plant[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (a[i].diff > a[j].diff) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= high) temp[k++] = a[j++];

        for (int x = 0; x < temp.length; x++) a[x + low] = temp[x];
    }

    static class QReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class QWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                return;
            }
        }

        public void println(Object object) {
            try {
                writer.write(object.toString());
                writer.write("\n");
            } catch (IOException e) {
                return;
            }
        }

        @Override
        public void close() {
            try {
                writer.close();
            } catch (IOException e) {
                return;
            }
        }
    }

}
/*
Fs的收益看h-s的比较
对每一个植物只能最多用一次Fs

对h - s进行排序，找到k个h-s>0
if k<q, 对全体k施加一次Fs；
    还剩q-k个Fs，对于剩余的h-s<0，
    此时需要Fh，若出现（9，10）能满足收益，

if k>=q,




 */