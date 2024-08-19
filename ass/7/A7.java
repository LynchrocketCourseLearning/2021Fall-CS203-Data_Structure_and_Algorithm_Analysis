package dsaa;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class A7 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        long num = in.nextLong();

        long[][] tree = new long[n][2];
        for (int i = 0; i < n - 1; i++) {
            int p = in.nextInt();
            int q = in.nextInt();
            long val = in.nextLong();

            int min = Math.min(p, q);
            int max = Math.max(p, q);

            tree[min - 1][1] = 0;
            tree[max - 1][1] = 1;
            tree[max - 1][0] = val + tree[min - 1][0];
        }

        long cnt = 0;
        for (int i = 0; i < n; i++) {
            if (tree[i][0] == num && tree[i][1] == 1) cnt++;
        }

        out.println(cnt);

        out.close();
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
