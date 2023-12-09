package dsaa;

import java.io.*;
import java.util.*;

public class D5 {
    static class node {
        private char c;
        public int val;
        public node next;
        public node pre;

        public node(char c) {
            this.c = c;
            this.val = 0;
            this.next = null;
            this.pre = null;
        }
    }

    static class stack {
        private int size;
        private node top;
        private node base;

        public stack() {
            top = base = new node('-');
            size = 0;
        }

        public void push(char c) {
            top.next = new node(c);
            top.next.pre = top;
            top = top.next;
            size++;
        }

        public node pop() {
            if (isEmpty()) throw new IllegalArgumentException("Stack is EMPTY");
            else {
                node tmp = top;
                top = top.pre;
                top.next = null;
                size--;
                if (tmp.val == 0) {
                    top.val += 1;
                } else {
                    int val = tmp.val * 2 % 514329;
                    top.val += val;
                }
                return tmp;
            }
        }

        public int getSize() {
            return this.size;
        }

        public boolean isEmpty() {
            return base == top;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        stack sta = new stack();
        long score = 0;
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') sta.push(c);
            else {
                sta.pop();
                if (sta.getSize() == 0) {
                    score = sta.top.val;
                }
            }
        }
        out.print(score);

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
