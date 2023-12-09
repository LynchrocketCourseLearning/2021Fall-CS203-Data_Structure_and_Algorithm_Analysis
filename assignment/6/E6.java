package dsaa;

import java.io.*;
import java.util.*;

public class E6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        String s = in.next();
        int len = s.length();
        int evenLen = 0, oddLen = 0, longerLen = 0;
        int r = 0, l = 0;
        for (int i = 0; i < len; i++) {
            //odd
            r = l = i;
            while (l >= 0 && r <= len-1 && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            oddLen = r - l - 1;

            //even
            r = i+1;
            l = i;
            while (l >= 0 && r <= len-1 && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            evenLen = r - l - 1;

            longerLen = Math.max(longerLen, Math.max(evenLen, oddLen));
        }
        out.print(longerLen);

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
