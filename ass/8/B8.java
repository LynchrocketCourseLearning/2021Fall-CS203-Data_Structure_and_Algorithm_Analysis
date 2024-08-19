package dsaa;

import java.io.*;
import java.util.StringTokenizer;

public class B8 {
    static class heap {
        long[] val;
        int size;

        public heap(int maxSize) {
            val = new long[maxSize];
            size = 0;
        }

        public int parent(int index) {
            return (index - 1) / 2;
        }

        public int left(int index) {
            return (2 * index + 1);
        }

        public void add(long val) {
            this.val[size] = val;
            size++;
        }

        public long upMax(int index) {
            long cnt = 0;
            while (index > 0 && this.val[index] > this.val[parent(index)]) {
                long tmp = this.val[parent(index)];
                this.val[parent(index)] = this.val[index];
                this.val[index] = tmp;
                cnt++;
                index = parent(index);
            }
            return cnt;
        }

        public long upMin(int index) {
            long cnt = 0;
            while (index > 0 && this.val[index] < this.val[parent(index)]) {
                long tmp = this.val[parent(index)];
                this.val[parent(index)] = this.val[index];
                this.val[index] = tmp;
                cnt++;
                index = parent(index);
            }
            return cnt;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt();
        heap max = new heap(N);
        heap min = new heap(N);
        long cntMax = 0, cntMin = 0;
        for (int i = 0; i < N; i++) {
            long val = in.nextLong();
            max.add(val);
            cntMax += max.upMax(max.size - 1);
            min.add(val);
            cntMin += min.upMin(max.size - 1);
        }
        if (cntMax == 0) {
            out.print("Max");
        } else if (cntMin == 0) {
            out.print("Min");
        } else {
            out.print("Neither");
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
