package dsaa;

import java.io.*;
import java.util.*;

public class E7 {
    static class Node {
        int val;
        int level;
        boolean isVisited;
        ArrayList<Node> parent;
        ArrayList<Node> children;
        int passCnt;

        public Node(int num) {
            this.val = num;
            this.level = 0;
            this.isVisited = false;
            this.parent = new ArrayList<Node>();
            this.children = new ArrayList<Node>();
            this.passCnt = 0;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        Node[] tree = new Node[n + 1];
        tree[1] = new Node(1);
        for (int i = 1; i <= n - 1; i++) {
            int tmp1 = in.nextInt();
            int tmp2 = in.nextInt();
            if (tree[tmp1] == null) tree[tmp1] = new Node(tmp1);
            if (tree[tmp2] == null) tree[tmp2] = new Node(tmp2);
            tree[tmp1].children.add(tree[tmp2]);
            tree[tmp2].children.add(tree[tmp1]);
        }

        int rear = 0, front = 0, cnt = 0;
        Node[] p = new Node[n + 1];
        p[rear++] = tree[1];
        tree[1].isVisited = true;
        tree[1].level = 0;
        while (rear != front) {
            Node tmp = p[front++];
            int len = tmp.children.size();
            for (int i = 0; i < len; i++) {
                if (tmp.children == null
                        || tmp.children.size() == 1
                        && tmp.children.get(0).isVisited) {
                    cnt++;
                }
                if (!tmp.children.get(i).isVisited) {
                    p[rear++] = tmp.children.get(i);
                    tmp.children.get(i).isVisited = true;
                    tmp.children.get(i).level = tmp.level + 1;
                } else {
                    tmp.parent.add(tmp.children.get(i));
                }
            }
        }

        int[] leaf = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            leaf[i] = in.nextInt();
        }

        boolean che = true;

        //first node
        Node tmp1 = tree[leaf[0]];
        int[] route1 = new int[tmp1.level + 1];
        int le = route1.length;
        for (int i = 0; i < le; i++) {
            route1[i] = tmp1.val;
            if (tmp1 == tree[1]) break;
            tmp1.passCnt += 1;
            tmp1 = tmp1.parent.get(0);
            if (tmp1.passCnt == 2) {
                che = false;
                break;
            }
        }
        for (int i = le - 1; i >= 0; i--) {
            out.print(route1[i] + " ");
        }

        //middle nodes
        for (int i = 1; i < cnt; i++) {
            if (!che) break;

            Node next = tree[leaf[i]];
            Node pre = tree[leaf[i - 1]];
            pre = pre.parent.get(0);

            int[] route2 = new int[next.level + 1];//正序直接输出，逆序先存后输出
            int tran = 0;

            if (pre.level > next.level) {
                int diff = pre.level - next.level;
                for (int j = 0; j < diff; j++) {
                    out.print(pre.val + " ");
                    if (pre == tree[1]) break;
                    pre = pre.parent.get(0);
                }
            }

            if (pre.level <= next.level) {
                int diff = next.level - pre.level;
                for (int j = 0; j < diff; j++) {
                    route2[j] = next.val;
                    if (next != tree[1]) next.passCnt += 1;
                    if (next.passCnt == 2) {
                        che = false;
                        break;
                    }
                    next = next.parent.get(0);
                    tran++;
                }
            }

            while (next != pre) {
                out.print(pre.val + " ");
                if (pre != tree[1]) pre = pre.parent.get(0);
                route2[tran++] = next.val;
                if (next != tree[1]) next.passCnt += 1;
                if (next.passCnt == 2) {
                    che = false;
                    break;
                }
                next = next.parent.get(0);
            }
            out.print(pre.val + " ");
            for (int j = route2.length - 1; j >= 0; j--) {
                if (route2[j] == 0) continue;
                out.print(route2[j] + " ");
            }
        }

        Node tmp2 = tree[leaf[leaf.length - 1]];
        while (tmp2 != tree[1]) {
            tmp2 = tmp2.parent.get(0);
            out.print(tmp2.val + " ");
        }

        if (che) out.close();
        else System.out.print(-1);
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
