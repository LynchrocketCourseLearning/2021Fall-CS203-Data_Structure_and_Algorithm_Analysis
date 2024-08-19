package dsaa;

import java.io.*;
import java.util.*;

public class B6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        String str = in.next();
        int len = str.length();

        int[][] dp = new int[len][26];

        int j = 0;
        dp[0][str.charAt(0)-97] = 1;
        for (int i = 1; i < len; i++) {
            for (int k = 0; k < 26; k++) {
                if (str.charAt(i) - 97 == k) dp[i][k] = i + 1;
                else dp[i][k] = dp[j][k];
            }
            j = dp[j][str.charAt(i) - 97];
        }

        for (int i = 0; i < len; i++) {
            for (int k = 0; k < 26; k++) {
                out.print(dp[i][k]+" ");
            }
            out.println("");
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
