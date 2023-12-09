package dsaa;

import java.io.*;
import java.util.*;

public class ct {
    static boolean che = false;
    static int rear = 0;

    static class node {
        int val;
        int isVisited;
        ArrayList<node> parent;
        ArrayList<node> nei;

        public node(int val) {
            this.val = val;
            this.isVisited = 0;
            this.parent = new ArrayList<>();
            this.nei = new ArrayList<>();
        }
    }

    public static void dfs(node[] q, node a, node tmp) {
        for (node t : a.nei) {
            int len = t.parent.size();
            for (int j = 0; j < len; j++) {
                if (t.parent.contains(a) && a.parent.contains(t)) {
                    che = true;
                    break;
                }
            }
            if (t != tmp) {
                if (t.isVisited == 0) {
                    t.isVisited = 1;
                    q[rear++] = t;
                    dfs(q, t, a);
                } else if (t.isVisited == 1) {
                    for (node t1 : t.nei) {
                        if (t1.isVisited == 0) {
                            t1.isVisited = 1;
                            q[rear++] = t1;
                            dfs(q, t1, t);
                        } else if (t1.isVisited == 1) {
                            che = true;
                            break;
                        }
                    }
                } else if (t.isVisited == -1) {
                    for (node t2 : t.nei) {
                        if (t2.isVisited == 1) {
                            che = true;
                            break;
                        }
                    }
                }

            }
        }
        rear--;
        a.isVisited = -1;
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();
        node[] road = new node[n + 1];
        for (int i = 0; i <= n; i++) {
            road[i] = new node(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int a = Math.min(u, v);
            int b = Math.max(u, v);
            if (road[a].parent.contains(road[b])) {
                che = true;
                break;
            }
            road[a].nei.add(road[b]);
            road[a].parent.add(road[b]);
            road[b].nei.add(road[a]);
        }

        node[] q = new node[n + 1];
        q[rear++] = road[1];
        road[1].isVisited = 1;
        dfs(q, road[1], road[0]);

        for (int i = 1; i <= n; i++) {
            if (road[i].isVisited == 0) {
                rear = 0;
                q[rear++] = road[i];
                road[i].isVisited = 1;
                dfs(q, road[i], road[0]);
            }
        }

        if (che) out.print("Bad");
        else out.print("Good");

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
