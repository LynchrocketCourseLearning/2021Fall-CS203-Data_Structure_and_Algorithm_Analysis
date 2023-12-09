package dsaa;

import java.io.*;
import java.util.StringTokenizer;

//有多少个节点只有一个儿子
public class D7 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String pre = in.next();
            String post = in.next();

            int len1 = pre.length();
            int len2 = post.length();
            long cnt = 0;

            for (int j = 0; j <= len1 - 2; j++) {
                for (int k = 1; k <= len2 - 1; k++) {
                    if (pre.charAt(j) == post.charAt(k) && pre.charAt(j + 1) == post.charAt(k - 1)) {
                        cnt++;
                    }
                }
            }
            long res = 1;
            int base = 2;
            while (cnt != 0) {
                if (cnt % 2 == 1) res *= base;
                base *= base;
                cnt /= 2;
            }
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
