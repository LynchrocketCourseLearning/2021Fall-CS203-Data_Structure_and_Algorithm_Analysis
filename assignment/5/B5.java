package dsaa;

import java.io.*;
import java.util.*;

public class B5 {
    static class queue {
        private int front;
        private int rear;
        private int[] val;
        private int size;

        public queue(int Maxsize) {
            rear = front = -1;
            val = new int[Maxsize];
            size = 0;
        }

        public void enQueue(int num) {
            val[++rear] = num;
            size++;
        }

        public int deQueue() {
            if (isEmpty()) throw new IllegalArgumentException("Queue is EMPTY");
            else {
                int tmp = val[++front];
                size--;
                if (size == 0) rear = front = -1;
                return tmp;
            }
        }

        public boolean isEmpty() {
            return (front == rear);
        }

        public int size() {
            return this.size;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();

        queue pl = new queue(p);
        queue ql = new queue(q);
        for (int i = 0; i < p; i++) {
            pl.enQueue(in.nextInt());
        }
        for (int i = 0; i < q; i++) {
            ql.enQueue(in.nextInt());
        }

        long[] pair = new long[n + 1];
        long t = 0;
        int out1, out2;
        while (!pl.isEmpty() && !ql.isEmpty()) {
            do {
                out1 = pl.deQueue();
            } while (pair[out1] != 0 && !pl.isEmpty());
            do {
                out2 = ql.deQueue();
            } while (pair[out2] != 0 && !ql.isEmpty());
            t++;
            if (pair[out1] == 0) pair[out1] = t;
            if (pair[out2] == 0) pair[out2] = t;
        }

        while (!pl.isEmpty()) {
            out1 = pl.deQueue();
            if (pair[out1] == 0) {
                t++;
                pair[out1] = t;
            }
        }
        while (!ql.isEmpty()) {
            out2 = ql.deQueue();
            if (pair[out2] == 0) {
                t++;
                pair[out2] = t;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (pair[i] != 0) out.print(pair[i] + " ");
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
