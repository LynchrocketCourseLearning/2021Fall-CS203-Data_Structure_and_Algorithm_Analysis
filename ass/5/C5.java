package dsaa;

import java.io.*;
import java.util.*;

public class C5 {
    static class stack {
        private int maxSize;
        private int top = -1;
        protected char[] s;

        public stack(int maxSize) {
            this.maxSize = maxSize;
            s = new char[maxSize];
        }

        public void push(char item) {
            if (this.top == maxSize) throw new IllegalArgumentException("Stack is FULL!");
            else {
                this.top++;
                s[this.top] = item;
            }
        }

        public char pop() {
            if (this.top == -1) throw new IllegalArgumentException("Stack is EMPTY!");
            else {
                return s[this.top--];
            }
        }

        public char peek() {
            if (this.top == -1) throw new IllegalArgumentException("this stack is already empty");
            return s[this.top];
        }

        public boolean isEmpty() {
            return this.top == -1;
        }

        public boolean isFull() {
            return this.top == this.maxSize;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            if (n % 2 == 1) {
                in.next();
                out.println("NO");
            } else {
                stack st = new stack(n);
                String str = in.next();
                int che = 0;
                for (int j = 0; j < n; j++) {
                    char c = str.charAt(j);
                    if (c == '(' || c == '[' || c == '{') {
                        st.push(c);
                    } else {
                        if (st.isEmpty()) {
                            out.println("NO");
                            che = 1;
                            break;
                        } else if ((st.peek() == '(' && c == ')') || (st.peek() == '[' && c == ']') || (st.peek() == '{' && c == '}')) {
                            st.pop();
                        } else {
                            out.println("NO");
                            che = 1;
                            break;
                        }
                    }
                }
                if (che == 0) {
                    if (!st.isEmpty()) out.println("NO");
                    else out.println("YES");
                }
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
