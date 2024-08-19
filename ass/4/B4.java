package dsaa;

import java.io.*;
import java.util.*;

public class B4 {
    static class node {
        private long val;
        public node next = null;
        public long note = 0;

        public node(long val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();

        node head = new node(-100);
        node cur = head;
        for (int i = 1; i <= n; i++) {
            cur.next = new node(i);
            cur = cur.next;
        }

        cur = head;
        while (cur.next != null){
            int note = 0;
            for (int i = 0; i < n; i++) {
                out.print(cur.next.val + " ");
                cur.next = cur.next.next;
                for (int j = 0; j < m-1; j++) {
                    if (cur.next == null) {
                        note = 1;
                        break;
                    }
                    cur = cur.next;
                    if (cur.next == null) {
                        note = 1;
                        break;
                    }
                    i++;
                }
               if(note==1)break;
            }
            cur = head;
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
