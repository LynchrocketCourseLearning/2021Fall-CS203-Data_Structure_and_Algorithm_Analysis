package dsaa;

import java.io.*;
import java.util.*;

public class A6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        String str = in.next();
        int len = str.length();
        long cnt = 0;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String s = str.substring(i, j);
                boolean che = false;
                for (String t : list) {
                    if (t.equals(s)) {
                        che = true;
                        break;
                    }
                }
                if(!che){
                    list.add(s);
                    cnt++;
                }
            }
        }
        out.print(cnt);

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
