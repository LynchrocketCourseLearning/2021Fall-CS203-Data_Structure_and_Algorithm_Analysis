package dsaa;

import java.io.*;
import java.util.*;

public class F6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        char[] cipher = new char[26];
        for (int i = 0; i < 26; i++) {
            cipher[i] = in.next().charAt(0);
        }

        String s = in.next();
        int len = s.length();
        char[] dec = new char[len];
        for (int i = 0; i < len; i++) {
            dec[i] = cipher[s.charAt(i) - 97];
        }

        int[][] dfa = new int[len][26];
        int k = 0;
        for (int i = 0; i < len; i++) {
            dfa[i][s.charAt(i) - 97] = i + 1;
            if (i > 0) {
                for (int j = 0; j < 26; j++) {
                    if (s.charAt(i) - 97 == j) continue;
                    dfa[i][j] = dfa[k][j];
                }
                k = dfa[k][s.charAt(i) - 97];
            }
        }

        int c = 0;
        for (int i = len / 2; i < len; i++) {
            c = dfa[c][dec[i] - 97];
        }
        out.print(len - c);

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
