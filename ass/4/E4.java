package dsaa;

import java.io.*;
import java.util.*;

public class E4 {
    public static class node {
        private int val;
        public node next = null;
        public node pre = null;

        public node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();


        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node head = new node(0);
            node cur = head;
            for (int j = 0; j < n; j++) {
                cur.next = new node(in.nextInt());
                cur.next.pre = cur;
                cur = cur.next;
            }
            node tail = new node(100001);
            cur.next = tail;
            cur.next.pre = cur;

            cur = head.next;
            node[] note = new node[n];
            int cnt = 0;
            while (cur.next != tail && cur.next != null) {
                boolean che = true;
                while (cur.val > cur.next.val) {
                    if (che) {
                        note[cnt] = cur.pre;
                        cnt++;
                        che = false;
                    }
                    cur = cur.next;
                    if (cur.next == tail) {
                        break;
                    }
                }
                if (!che) {
                    note[cnt - 1].next = cur.next;
                    note[cnt - 1].next.pre = note[cnt - 1];
                }
                cur = cur.next;
            }

            node p1 = head;
            node p2 = head;
            node toDel = head;
            for (int j = 0; j < n; j++) {
                if (note[j] == null) continue;
                p1 = note[j];
                p2 = note[j].next;
                boolean che = false;
                while (p1.val > p2.val) {
                    p1.pre.next = p1.next;
                    p1.next.pre = p1.pre;
                    toDel = p1 = p2;
                    p2 = p2.next;
                    che = true;
                }
                if (che) {
                    note[cnt] = toDel.pre;
                    cnt++;
                    toDel.pre.next = toDel.next;
                    toDel.next.pre = toDel.pre;
                }
                if (head.next == tail) {
                    break;
                }
            }

            cur = head.next;
            while (cur != tail) {
                out.print(cur.val + " ");
                cur = cur.next;
            }
            out.println("");
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