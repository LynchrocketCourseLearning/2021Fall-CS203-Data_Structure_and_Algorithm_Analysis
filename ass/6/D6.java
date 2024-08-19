package dsaa;

import java.io.*;
import java.util.*;

public class D6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        String s = in.next();
        String t = in.next();

        if (s.length() != t.length()) out.print("No");
        else {
            boolean che = false;
            s = s + s;
            int slen = s.length();
            int tlen = t.length();

            int[] next = new int[tlen + 1];
            next[1] = 0;
            int j = 0;
            for (int i = 2; i <= tlen; i++) {
                while (j > 0 && t.charAt(j) != t.charAt(i - 1)) {
                    j = next[j];
                }
                if (t.charAt(j) == t.charAt(i - 1)) {
                    j++;
                }
                next[i] = j;
            }

            j = 0;
            for (int i = 0; i < slen; i++) {
                while (j > 0 && t.charAt(j) != s.charAt(i)) {
                    j = next[j];
                }
                if (t.charAt(j) == s.charAt(i)) {
                    j++;
                }
                if (j == tlen - 1) {che=true;break;}
            }

            if (che) {
                out.print("Yes");
            } else {
                out.print("No");
            }
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
