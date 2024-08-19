package dsaa;

import java.io.*;
import java.util.*;

public class A4 {
    static class Node {
        private long coe;
        private long exp;
        public Node next = null;

        public Node(long coe, long exp) {
            this.coe = coe;
            this.exp = exp;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();

        Node head = new Node(2000000000, 2000000000);
        Node cur = head;
        for (int i = 0; i < n; i++) {
            Node a = new Node(in.nextLong(), in.nextLong());
            cur.next = a;
            cur = cur.next;
        }
        Node tail = new Node(-2000000000, -2000000000);
        cur.next = tail;

        cur = head;
        for (int i = 0; i < m; i++) {
            long coe = in.nextLong();
            long exp = in.nextLong();

            while (cur.next.exp >= exp) {
                cur = cur.next;
            }
            if (cur.exp == exp) {
                cur.coe += coe;
                if (cur.coe == 0) cur = cur.next;
            } else {
                Node a = new Node(coe, exp);
                a.next = cur.next;
                cur.next = a;
//                cur = cur.next;
            }
        }

        long cnt = 0;
        Node che = head.next;
        while (che != tail) {
            cnt++;
            che = che.next;
        }
        out.println(cnt);

        che = head.next;
        while (che != tail) {
            out.print(che.coe);
            out.print(" ");
            out.print(che.exp);
            out.println("");
            che = che.next;
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
