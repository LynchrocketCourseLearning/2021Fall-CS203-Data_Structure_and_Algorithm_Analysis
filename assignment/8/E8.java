package dsaa;

import java.io.*;
import java.util.*;

public class E8 {
    static class Minheap {
        long[] val;
        int size;

        public Minheap(int maxSize) {
            val = new long[maxSize + 1];
            size = 0;
        }

        public int parent(int index) {
            return (index / 2);
        }

        public int left(int index) {
            return (2 * index);
        }

        public int right(int index) {
            return (2 * index + 1);
        }

        public void add(long val) {
            this.val[++size] = val;
            up(size);
        }

        public long deleteMin() {
            long tmp = this.val[1];
            swap(1, size);
            this.val[size] = 0;
            size--;
            sink(1);
            return tmp;
        }

        public void swap(int i, int j) {
            long tmp = this.val[i];
            this.val[i] = this.val[j];
            this.val[j] = tmp;
        }

        public void up(int k) {
            while (k > 1
                    && this.val[k] < this.val[parent(k)]) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        public void sink(int k) {
            while (left(k) <= this.size) {
                int index = left(k);
                if (right(k) <= this.size
                        && this.val[index] > this.val[right(k)]) {
                    index = right(k);
                }
                if (this.val[index] > this.val[k]) {
                    break;
                }
                swap(index, k);
                k = index;
            }
        }

        public long showTop() {
            return this.val[1];
        }
    }

    static class Maxheap {
        long[] val;
        int size;

        public Maxheap(int maxSize) {
            val = new long[maxSize + 1];
            size = 0;
        }

        public int parent(int index) {
            return (index / 2);
        }

        public int left(int index) {
            return (2 * index);
        }

        public int right(int index) {
            return (2 * index + 1);
        }

        public void add(long val) {
            this.val[++size] = val;
            up(size);
        }

        public long deleteMax() {
            long tmp = this.val[1];
            swap(1, size);
            this.val[size] = 0;
            size--;
            sink(1);
            return tmp;
        }

        public void swap(int i, int j) {
            long tmp = this.val[i];
            this.val[i] = this.val[j];
            this.val[j] = tmp;
        }

        public void up(int k) {
            while (k > 1
                    && this.val[k] > this.val[parent(k)]) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        public void sink(int k) {
            while (left(k) <= this.size) {
                int index = left(k);
                if (right(k) <= this.size
                        && this.val[index] < this.val[right(k)]) {
                    index = right(k);
                }
                if (this.val[index] < this.val[k]) {
                    break;
                }
                swap(index, k);
                k = index;
            }
        }

        public long showTop() {
            return this.val[1];
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt();

        long A1 = in.nextLong();
        Maxheap mah = new Maxheap(N);
        mah.add(A1);
        Minheap mih = new Minheap(N);
        out.print(A1 + " ");

        for (int i = 2; i <= N; i++) {
            long Ai = in.nextLong();
            if (Ai > mah.showTop()) {
                if (mih.size + 1 == mah.size) mih.add(Ai);
                else {
                    if (mih.showTop() < Ai) {
                        mah.add(mih.deleteMin());
                        mih.add(Ai);
                    } else {
                        mah.add(Ai);
                    }
                }
            } else {
                if (mih.size + 1 <= mah.size) {
                    mih.add(mah.deleteMax());
                }
                mah.add(Ai);
            }
            long res = mah.showTop();
            out.print(res + " ");
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
