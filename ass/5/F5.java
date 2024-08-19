package dsaa;

import java.io.*;
import java.util.*;

public class F5 {
    static class node {
        public long val;
        public int rank;
        public node next = null;
        public node pre = null;

        public node(long val, int rank) {
            this.val = val;
            this.rank = rank;
        }
    }

    static class queue {
        public node front;
        public node rear;
        public node head;
        public int size;

        public queue() {
            rear = front = head = new node(-1, -1);
            size = 0;
        }

        public void enQueue(long val, int rank) {
            rear.next = new node(val, rank);
            rear.next.pre = rear;
            rear = rear.next;
            if (front.rank == -1) front = front.next;
            size++;
        }

        public node deFront() {
            if (isEmpty()) throw new IllegalArgumentException("Queue is EMPTY");
            else {
                node tmp = front;
                if (size == 1) {
                    front = rear = head;
                    head.next = null;
                } else {
                    front = front.next;
                    front.pre = null;
                }
                size--;
                return tmp;
            }
        }

        public node deRear() {
            if (isEmpty()) throw new IllegalArgumentException("Queue is EMPTY");
            else {
                node tmp = rear;
                if (size == 1) {
                    front = rear = head;
                    head.next = null;
                } else {
                    rear = rear.pre;
                    rear.next = null;
                }
                size--;
                return tmp;
            }
        }

        public boolean isEmpty() {
            return (size == 0);
        }

        public long size() {
            return this.size;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        long k = in.nextLong();
        int n = in.nextInt();

        queue max = new queue();
        queue min = new queue();
        long len = 1;
        long val;
        int i = 0, j = 0;
        for (j = 0; j < n; j++) {
            val = in.nextLong();
            if (!max.isEmpty()) {
                while (!max.isEmpty() && max.rear.val < val) {
                    max.deRear();
                }
            }
            max.enQueue(val, j);

            if (!min.isEmpty()) {
                while (!min.isEmpty() && min.rear.val > val) {
                    min.deRear();
                }
            }
            min.enQueue(val, j);

            while (!min.isEmpty() && !max.isEmpty() && max.front.val - min.front.val > k) {
                i++;
                if (max.front.rank < i && max.front.val != val && !max.isEmpty()) max.deFront();
                if (min.front.rank < i && min.front.val != val && !min.isEmpty()) min.deFront();
            }

            len = Math.max(len, j - i + 1);
        }


        out.print(len);
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
