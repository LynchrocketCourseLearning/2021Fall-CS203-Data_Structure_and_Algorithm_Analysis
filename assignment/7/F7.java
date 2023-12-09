package dsaa;

import java.io.*;
import java.util.*;

public class F7 {
    static class Node {
        int val;
        int level;
        ArrayList<Node> children;
        boolean hasPeople;
        boolean isVisited;

        public Node(int num) {
            this.val = num;
            this.level = 0;
            this.children = new ArrayList<>();
            this.hasPeople = false;
            this.isVisited = false;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int k = in.nextInt();

            Node[] tree = new Node[n + 1];
            tree[1] = new Node(1);
            for (int j = 1; j < tree.length - 1; j++) {
                int tmp1 = in.nextInt();
                int tmp2 = in.nextInt();
                if (tree[tmp1] == null) tree[tmp1] = new Node(tmp1);
                if (tree[tmp2] == null) tree[tmp2] = new Node(tmp2);
                tree[tmp1].children.add(tree[tmp2]);
                tree[tmp2].children.add(tree[tmp1]);
            }

            Node tran = tree[in.nextInt()];
            tran.hasPeople = true;
            for (int j = 0; j < k - 1; j++) {
                int friend = in.nextInt();
                tree[friend].hasPeople = true;
            }

            Node[] p = new Node[n + 1];
            int rear1 = 0, front1 = 0;
            p[rear1++] = tran;
            tran.isVisited = true;
            while (rear1 != front1) {
                Node tmp = p[front1++];
                int len = tmp.children.size();
                for (int j = 0; j < len; j++) {
                    if (!tmp.children.get(j).isVisited) {
                        p[rear1++] = tmp.children.get(j);
                        tmp.children.get(j).isVisited = true;
                        if (tmp.children.get(j).hasPeople) {
                            tran = tmp.children.get(j);
                        }
                    }
                }
            }

            for (int j = 1; j < tree.length; j++) {
                tree[j].isVisited = false;
            }

            int rear2 = 0, front2 = 0;
            tran.isVisited = true;
            tran.level = 0;
            p[rear2++] = tran;
            while (rear2 != front2) {
                Node tmp = p[front2++];
                int len = tmp.children.size();
                for (int j = 0; j < len; j++) {
                    if (!tmp.children.get(j).isVisited) {
                        p[rear2++] = tmp.children.get(j);
                        tmp.children.get(j).level = tmp.level + 1;
                        tmp.children.get(j).isVisited = true;
                        if (tmp.children.get(j).hasPeople) {
                            tran = tmp.children.get(j);
                        }
                    }
                }
            }

            if (k == 1) {
                out.println(0);
                continue;
            }
            long res = (1 + tran.level) / 2;
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
