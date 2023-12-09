package dsaa;

import java.io.*;
import java.util.*;

public class C4 {
    public static class node {
        private int weight;
        public node next = null;
        public node pre = null;

        public node(int weight) {
            this.weight = weight;
        }
    }


    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();

        node[] l = new node[n];
        for (int i = 0; i < n; i++) {
            int weight = in.nextInt();
            l[i] = new node(weight);
        }
//        for (int i = 1; i <= n; i++) {
//            out.print(l[i].weight + " ");
//        }

        int a, b;
        for (int i = 0; i < p; i++) {
            a = in.nextInt();
            b = in.nextInt();
            node cur = l[a-1];
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = l[b-1];
            l[b-1].pre = cur;
        }

        long[] res = new long[n];
        for (int i = 0; i < p; i++) {
            if (l[i].pre == null) {
                node cur = l[i];
                long sum = 0;
                while (cur.next != null) {
                    sum += cur.weight;
                    cur = cur.next;
                }
                sum += cur.weight;
                if (sum > q) {
                    cur = l[i];
                    for (int j = 0; j < q-1; j++) {
                        cur = cur.next;
                    }
                    res[i] = cur.weight;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (res[i] != 0) out.print(res[i] + " ");
        }

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
