package dsaa;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class D9 {
    static class node {
        long val;
        boolean isVisited = false;
        ArrayList<node> nei = new ArrayList<>();

        public node(long val) {
            this.val = val;
        }

        public node() {
            this.val = 0;
        }
    }

    static long dfs(node[][] mat, int i, int j, int x, int y, long max, long res) {
        if (!(mat[i + 1][j].isVisited || mat[i - 1][j].isVisited || mat[i][j + 1].isVisited || mat[i][j - 1].isVisited ||
                mat[i + 1][j + 1].isVisited || mat[i + 1][j - 1].isVisited || mat[i - 1][j + 1].isVisited || mat[i - 1][j - 1].isVisited)) {
            if (j + 1 < y) {
                mat[i][j].isVisited = true;
                max += mat[i][j].val;
                res = dfs(mat, i, j + 1, x, y, max, res);
                max -= mat[i][j].val;
                mat[i][j].isVisited = false;
                res = dfs(mat, i, j + 1, x, y, max, res);
            } else if (i + 1 < x) {
                mat[i][j].isVisited = true;
                max += mat[i][j].val;
                res = dfs(mat, i + 1, 1, x, y, max, res);
                max -= mat[i][j].val;
                mat[i][j].isVisited = false;
                res = dfs(mat, i + 1, 1, x, y, max, res);
            } else {
                max += mat[i][j].val;
                if (res < max) res = max;
                max -= mat[i][j].val;
                return res;
            }
        } else {
            if (j + 1 < y) {
                res = dfs(mat, i, j + 1, x, y, max, res);
            } else if (i + 1 < x) {
                res = dfs(mat, i + 1, 1, x, y, max, res);
            } else {
                if (res < max) res = max;
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            node[][] mat = new node[N + 2][M + 2];
            for (int j = 0; j <= N + 1; j++) {
                for (int k = 0; k <= M + 1; k++) {
                    if (j == 0 || j == N + 1 || k == 0 || k == M + 1) {
                        mat[j][k] = new node();
                        continue;
                    }
                    mat[j][k] = new node(in.nextLong());
                }
            }
            long res = dfs(mat, 1, 1, N + 1, M + 1, 0, 0);
            out.println(res);
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
