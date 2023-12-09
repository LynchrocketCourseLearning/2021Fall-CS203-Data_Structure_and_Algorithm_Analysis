package dsaa;
import java.io.*;
import java.util.*;

public class A10 {
    static class node {
        int num;
        long cost = 10000000010L;
        ArrayList<node> neighbour = new ArrayList<>();
        ArrayList<Long> dist = new ArrayList<>();

        public node(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        node[] cities = new node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            cities[i] = new node(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            long w = in.nextLong();
            cities[u].neighbour.add(cities[v]);
            cities[u].dist.add(w);
        }
        node root = new node(1);
        root.cost = 0;
        node[] roads = new node[m + 2];
        roads[1] = root;
        int cnt = 1;
        while (roads[1] != null) {
            long cost = root.cost;
            long len = cities[root.num].neighbour.size();
            for (int j = 0; j < len; j++) {
                int x = cities[root.num].neighbour.get(j).num;
                long y = cost + cities[root.num].dist.get(j);
                if (cities[roads[1].num].cost < y) continue;
                node a = new node(0);
                roads[++cnt] = a;
                int temp = cnt;
                while (temp / 2 > 0) {
                    if (roads[temp / 2].cost > y) {
                        temp = temp / 2;
                        a.cost = roads[temp].cost;
                        a.num = roads[temp].num;
                    } else break;
                }
                roads[temp].cost = y;
                roads[temp].num = x;
            }
            if (cities[roads[1].num].cost > roads[1].cost) {
                cities[roads[1].num].cost = roads[1].cost;
            } else {
                roads[1].num = roads[cnt].num;
                roads[1].cost = roads[cnt].cost;
                roads[cnt--] = null;
                topmin(roads, cnt);
            }

        }
        if (cities[n].cost == 10000000010L) out.println(-1);
        else out.println(cities[n].cost);
        out.close();
    }

    public static void topmin(node[] a, int length) {
        while (true) {
            int i = 1;
            if (i * 2 <= length) {
                if (i * 2 < length) {
                    if (a[i * 2].cost < a[i * 2 + 1].cost) {
                        if (a[i].cost > a[i * 2].cost) {
                            int x = a[i].num;
                            long dis = a[i].cost;
                            a[i].cost = a[i * 2].cost;
                            a[i].num = a[i * 2].num;
                            a[i * 2].cost = dis;
                            a[i * 2].num = x;
                            i = i * 2;
                        } else break;
                    } else {
                        if (a[i].cost > a[i * 2 + 1].cost) {
                            int x = a[i].num;
                            long dis = a[i].cost;
                            a[i].cost = a[i * 2 + 1].cost;
                            a[i].num = a[i * 2 + 1].num;
                            a[i * 2 + 1].cost = dis;
                            a[i * 2 + 1].num = x;
                            i = i * 2 + 1;
                        } else break;
                    }
                } else {
                    if (a[i].cost > a[i * 2].cost) {
                        int x = a[i].num;
                        long dis = a[i].cost;
                        a[i].cost = a[i * 2].cost;
                        a[i].num = a[i * 2].num;
                        a[i * 2].cost = dis;
                        a[i * 2].num = x;
                        i = i * 2;
                    } else break;
                }
            } else break;
        }
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
