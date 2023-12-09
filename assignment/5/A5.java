package dsaa;

import java.io.*;
import java.util.*;

public class A5 {
    static class stack {
        private int maxSize;
        private int top = -1;
        public String[] s;

        public stack(int maxSize) {
            this.maxSize = maxSize;
            s = new String[maxSize];
        }

        public void push(String item) {
            if (this.top == maxSize) throw new IllegalArgumentException("Stack is FULL!");
            else {
                s[++this.top] = item;
            }
        }

        public String pop() {
            if (this.top == -1) throw new IllegalArgumentException("Stack is EMPTY!");
            else {
                return s[this.top--];
            }
        }

        public String peek() {
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

    static class node {
        private String food;
        public node next = null;

        public node(String food) {
            this.food = food;
        }
    }

    static class queue {
        private node front;
        private node rear;
        private int size;

        public queue() {
            rear = front = new node("");
        }

        public void enQueue(String food) {
            rear.next = new node(food);
            rear = rear.next;
            size++;
        }

        public node deQueue() {
            if (isEmpty()) throw new IllegalArgumentException("Queue is EMPTY");
            else {
                node tmp = front.next;
                front.next = front.next.next;
                size--;
                if (size == 0) rear = front;
                return tmp;
            }
        }

        public boolean isEmpty() {
            return (front == rear);
        }

        public int size() {
            return this.size;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int T = in.nextInt();
        stack sta = new stack(T);
        queue que = new queue();
        String op;
        for (int i = 0; i < T; i++) {
            op = in.next();
            switch (op) {
                case "NewFood":
                    sta.push(in.next());
                    break;
                case "NewComer":
                    que.enQueue(in.next());
                    break;
                case "TakeFood":
                    if (sta.isEmpty() || que.isEmpty()) {
                        continue;
                    } else {
                        node stu = que.deQueue();
                        String food = sta.peek();
                        if (stu.food.equals(food)) {
                            sta.pop();
                        } else {
                            que.enQueue(stu.food);
                        }
                    }
                    break;
            }
        }

        while (!sta.isEmpty()) {
            boolean che = false;
            if (sta.isEmpty() || que.isEmpty()) {
                break;
            } else {
                for (int i = 0; i < que.size; i++) {
                    String food = sta.peek();
                    node stu = que.deQueue();
                    if (stu.food.equals(food)) {
                        sta.pop();
                        che = true;
                    } else {
                        que.enQueue(stu.food);
                    }
                }
                if (!che) break;
            }
        }

        if (que.isEmpty()) {
            out.print("Qi Fei!");
        } else {
            out.print(que.size);
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
