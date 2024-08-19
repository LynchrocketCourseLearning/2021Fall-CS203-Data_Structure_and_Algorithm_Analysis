package dsaa;

import java.io.*;
import java.util.*;

public class E5 {
    static class deque {
        public int front;
        public int rear;
        public int[] val = new int[20];
        public int size;

        public deque() {
            rear = 9;
            front = 10;
            size = 0;
        }

        public void Rin(int num) {
            val[++rear] = num;
            size++;
        }

        public void Lin(int num) {
            val[--front] = num;
            size++;
        }

        public void lapp(deque b) {
            size += b.size;
            while (!b.isEmpty()) {
                val[++rear] = b.Lout();
            }
        }

        public void rapp(deque b) {
            size += b.size;
            while (!b.isEmpty()) {
                val[++rear] = b.Rout();
            }
        }

        public int Lout() {
            if (isEmpty()) throw new IllegalArgumentException("Queue is EMPTY");
            else {
                int tmp = val[front++];
                size--;
                if (size == 0) {
                    rear = 9;
                    front = 10;
                    val = new int[20];
                }
                return tmp;
            }
        }

        public int Rout() {
            if (isEmpty()) throw new IllegalArgumentException("Queue is EMPTY");
            else {
                int tmp = val[rear--];
                size--;
                if (size == 0) {
                    rear = 9;
                    front = 10;
                    val = new int[20];
                }
                return tmp;
            }
        }

        public boolean isEmpty() {
            return (size == 0);
        }

        public int size() {
            return this.size;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        while (in.hasNext()) {
            int n = in.nextInt();
            int q = in.nextInt();
            deque[] list = new deque[n];
            for (int i = 0; i < n; i++) {
                list[i] = new deque();
            }
            for (int i = 0; i < q; i++) {
                int op = in.nextInt();
                switch (op) {
                    case 1:
                        int u1 = in.nextInt();
                        int w1 = in.nextInt();
                        int val = in.nextInt();
                        if (w1 == 0) list[u1 - 1].Lin(val);
                        else if (w1 == 1) list[u1 - 1].Rin(val);
                        break;
                    case 2:
                        int u2 = in.nextInt();
                        int w2 = in.nextInt();
                        if (list[u2 - 1].isEmpty()) out.println(-1);
                        else {
                            if (w2 == 0) out.println(list[u2 - 1].Lout());
                            else if (w2 == 1) out.println(list[u2 - 1].Rout());
                        }
                        break;
                    case 3:
                        int u3 = in.nextInt();
                        int v3 = in.nextInt();
                        int w3 = in.nextInt();
                        if (w3 == 0) {
                            list[u3 - 1].lapp(list[v3 - 1]);
                            list[v3 - 1] = new deque();
                        } else if (w3 == 1) {
                            list[u3 - 1].rapp(list[v3 - 1]);
                            list[v3 - 1] = new deque();
                        }
                        break;
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
