package dsaa;

import java.io.*;
import java.util.*;

public class F4 {
    public static class node {
        public char[] val;
        public int len;
        public int flag;
        public node next;

        public node() {
            this.val = new char[510];
            this.len = 0;
            this.flag = 1;
            this.next = null;
        }
    }

    public static void init(node head, String s) {
        node cur = head;
        int i = 0;
        while (i < s.length()) {
            for (int j = 0; j < 500 && i < s.length(); j++, i++) {
                cur.val[j] = s.charAt(i);
                cur.len++;
            }
            if (i < s.length()) {
                cur.next = new node();
            }
            cur = cur.next;
        }
        cur = head;
        while (cur.next != null) {
            if (cur.len >= 500) split(cur);
            cur = cur.next;
        }
    }

    public static void split(node head) {
        node a = new node();
        a.next = head.next;
        head.next = a;
        for (int i = 250; i < head.val.length; i++) {
            a.val[i - 250] = head.val[i];
            a.len++;
            head.val[i] = 0;
            head.len--;
        }
    }

    public static void insert(node head, char ch, int p) {
        node cur = head;
        while (p > cur.len && cur.next != null) {
            p -= cur.len;
            cur = cur.next;
        }
        if (p >= cur.len) {
            cur.val[cur.len] = ch;
        } else {
            for (int i = cur.len; i >= p; i--) {
                cur.val[i] = cur.val[i - 1];
            }
            cur.val[p - 1] = ch;
        }
        cur.len++;
        if (cur.len >= 500) split(cur);
    }

    public static char find(node head, int p) {
        node cur = head;
        while (p > cur.len) {
            p -= cur.len;
            cur = cur.next;
        }
        if (cur.flag == -1) {
            return (char) ('a' + 'z' - cur.val[p - 1]);
        } else {
            return cur.val[p - 1];
        }
    }

    public static void transform(node head, int l, int r) {
        node cur = head;
        while (l > cur.len) {
            l -= cur.len;
            r -= cur.len;
            cur = cur.next;
        }
        if (r <= cur.len) {
            for (int j = l - 1; j <= r - 1; j++) {
                char c = cur.val[j];
                cur.val[j] = (char) ('a' + 'z' - c);
            }
        }
        if (r > cur.len) {
            for (int i = l - 1; i <= cur.len - 1; i++) {
                char c = cur.val[i];
                cur.val[i] = (char) ('a' + 'z' - c);
            }
            cur = cur.next;
            while (r > cur.len) {
                r -= cur.len;
                cur.flag *= -1;
                cur = cur.next;
            }
            for (int j = 0; j <= r - 1; j++) {
                char c = cur.val[j];
                cur.val[j] = (char) ('a' + 'z' - c);
            }
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        String s = in.nextLine();
        node head = new node();
        init(head, s);

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int op = in.nextInt();
            switch (op) {
                case 1:
                    char ch = in.next().charAt(0);
                    int p1 = in.nextInt();
                    insert(head, ch, p1);
                    break;
                case 2:
                    int p2 = in.nextInt();
                    char res = find(head, p2);
                    out.println(res);
                    break;
                case 3:
                    int l = in.nextInt();
                    int r = in.nextInt();
                    transform(head, l, r);
                    break;
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