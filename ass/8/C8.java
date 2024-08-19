package dsaa;

import java.io.*;
import java.util.*;

public class C8 {
    static class node {
        long val;
        int a;
        int b;

        public node(long val, int a, int b) {
            this.val = val;
            this.a = a;
            this.b = b;
        }
    }

    static class heap {
        node[] val;
        int size;

        public heap(int maxSize) {
            val = new node[maxSize + 1];
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

        public void add(long val, int a, int b) {
            this.val[++size] = new node(val, a, b);
            up(size);
        }

        public node deleteMin() {
            node tmp = this.val[1];
            swap(1, size);
            this.val[size] = null;
            size--;
            sink(1);
            return tmp;
        }

        public void swap(int i, int j) {
            node tmp = this.val[i];
            this.val[i] = this.val[j];
            this.val[j] = tmp;
        }

        public void up(int k) {
            while (k > 1
                    && this.val[k].val < this.val[parent(k)].val) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        public void sink(int k) {
            while (left(k) <= this.size) {
                int index = left(k);
                if (right(k) <= this.size
                        && this.val[index].val > this.val[right(k)].val) {
                    index = right(k);
                }
                if (this.val[index].val > this.val[k].val) {
                    break;
                }
                swap(index, k);
                k = index;
            }
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();

        heap A = new heap(N);
        heap B = new heap(M);
        for (int i = 0; i < N; i++) {
            A.add(in.nextLong(), i, 0);
        }
        for (int i = 0; i < M; i++) {
            B.add(in.nextLong(), 0, i);
        }

        heap C = new heap(M + N);

        node[] a = new node[N];
        node[] b = new node[M];
        for (int i = 0; i < M; i++) {
            b[i] = B.deleteMin();
        }
        for (int i = 0; i < N; i++) {
            a[i] = A.deleteMin();
            C.add(a[i].val * b[0].val, i, 0);
        }

        node[] res = new node[K];
        res[0] = C.deleteMin();
        int cnt = 1;
        for (int i = 1; i < 500000; i++) {
            if (cnt == K) break;
            node tmp = res[cnt - 1];
            if (tmp.b + 1 < b.length) C.add(a[tmp.a].val * b[tmp.b + 1].val, tmp.a, tmp.b + 1);
            res[cnt++] = C.deleteMin();
        }

        for (int i = 0; i < K; i++) {
            out.print(res[i].val + " ");
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
