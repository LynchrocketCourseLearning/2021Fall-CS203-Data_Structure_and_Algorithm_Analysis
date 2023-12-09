package dsaa;

import java.io.*;
import java.util.*;

public class E9 {
    static class node {
        int loc;
        boolean isVisited;
        ArrayList<node> nei;

        public node() {
            this.loc = 0;
            this.isVisited = false;
            this.nei = new ArrayList<>();
        }

        public node(int loc) {
            this.loc = loc;
            this.isVisited = false;
            this.nei = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();
        node[] road = new node[n + 1];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            if (road[u] == null) road[u] = new node(u);
            if (road[v] == null) road[v] = new node(v);
            if (w == 1) {
                road[u].nei.add(road[v]);
            } else {
                node a = new node();
                a.nei.add(road[v]);
                road[u].nei.add(a);
            }
        }

        int rear = 0, front = 0;
        node[] p = new node[2 * (m + n)];
        p[rear++] = road[1];
        road[1].isVisited = true;

        long res = 1, cnt = 0, level = 1;
        boolean che = false;

        while (rear != front) {
            node tmp = p[front++];
            for (node t:tmp.nei) {
                if(t==road[n]){
                    out.println(res);
                    che=true;
                    break;
                }else if(!t.isVisited){
                    p[rear++]=t;
                    t.isVisited=true;
                    cnt++;
                }
            }
            if(che)break;
            level--;
            if(level==0){
                res++;
                level=cnt;
                cnt=0;
            }
        }

        if (!che) out.println("-1");

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
