package dsaa;

import java.io.*;
import java.util.StringTokenizer;

public class B9 {
    static class queue {
        int size;
        node front;
        node rear;

        public queue() {
            front = rear = null;
            this.size = 0;
        }

        public void enQueue(node val) {
            if (front == null && rear == null) {
                front = rear = val;
            } else {
                rear.next = val;
                rear.next.pre = rear;
                rear = rear.next;
            }
            this.size++;
        }

        public node deQueue() {
            this.size--;
            node tmp = front;
            if (this.size == 0) front = rear = null;
            else front = front.next;
            return tmp;
        }

        public boolean isEmpty() {
            return (this.size == 0);
        }
    }

    static class node {
        int x;
        int y;
        int step;
        node pre;
        node next;

        public node(String val) {
            this.x = val.charAt(0) - 96;
            this.y = val.charAt(1) - 48;
            this.step = 0;
            pre = next = null;
        }

        public node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
            pre = next = null;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int[][] dire = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

        int t = in.nextInt();
        String start, end;
        for (int i = 0; i < t; i++) {
            start = in.next();
            end = in.next();
            if (start.equals(end)) out.println(0);
            else {
                node a = new node(start);
                node b = new node(end);
                boolean[][] che = new boolean[9][9];
                queue q = new queue();
                q.enQueue(a);
                while (!q.isEmpty()) {
                    node tmp = q.deQueue();
                    if (tmp.x == b.x && tmp.y == b.y) out.println(tmp.step);
                    else {
                        for (int j = 0; j < 8; j++) {
                            node ne = new node(tmp.x + dire[j][0], tmp.y + dire[j][1], tmp.step + 1);
                            if (ne.x >= 1 && ne.x <= 8
                                    && ne.y >= 1 && ne.y <= 8
                                    && !che[ne.x][ne.y]) {
                                q.enQueue(ne);
                                che[ne.x][ne.y] = true;
                            }
                        }
                    }
                }
            }
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
