package dsaa;

import java.io.*;
import java.util.*;

public class D4 {
    public static class node {
        private long val;
        private int rank;
        public node next = null;
        public node pre = null;

        public node(long val, int rank) {
            this.val = val;
            this.rank = rank;
        }
    }

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        long[][] list = new long[n][2];
        for (int i = 0; i < n; i++) {
            list[i][0] = in.nextLong();
            list[i][1] = i;
        }
        sort(list, 0, n - 1);

        node head = new node(-1000000010, -1);
        node cur = head;
        for (int i = 0; i < n; i++) {
            cur.next = new node(list[i][0], (int) list[i][1]);
            cur.next.pre = cur;
            cur = cur.next;
        }
        node tail = new node(1000000010, -1);
        cur.next = tail;
        cur.next.pre = cur;

        head.pre = null;
        tail.next = null;
        cur = head.next;
        long[] res = new long[n - 1];
        node pr, ne;
        for (int i = 0; i < n; i++) {
            pr = cur.pre;
            ne = cur.next;
            for (int j = 0; j < n; j++) {
                if (cur.rank == n - 1) continue;
                long tmp1 = -1;
                long tmp2 = -1;
                int che1 = 0;
                int che2 = 0;

                if (pr.rank > cur.rank) {
                    tmp1 = Math.abs(cur.val - pr.val);
                    che1 = 1;
                } else {
                    if (pr.pre != null) {
                        pr = pr.pre;
                    } else che1 = 2;
                }
                if (ne.rank > cur.rank) {
                    tmp2 = Math.abs(cur.val - ne.val);
                    che2 = 1;
                } else {
                    if (ne.next != null) {
                        ne = ne.next;
                    } else che2 = 2;
                }

                if (ne.next == null && pr.pre == null) {
                    break;
                }
                if (che1 == 0 && che2 == 0) {
                    continue;
                }
                if (che1 != 0 && che1 != 2) {
                    if (che2 == 2) {
                        res[cur.rank] = tmp1;
                        break;
                    }
                    if (che2 != 0) {
                        res[cur.rank] = Math.min(tmp1, tmp2);
                        break;
                    }

                }
                if (che2 != 0 && che2 != 2) {
                    if (che1 == 2) {
                        res[cur.rank] = tmp2;
                        break;
                    }
                    if (che1 != 0) {
                        res[cur.rank] = Math.min(tmp1, tmp2);
                        break;
                    }
                }
            }
            cur = cur.next;
        }
        for (int i = 0; i < n - 1; i++) {
            out.print(res[i] + " ");
        }

        out.close();
    }

    static void merge(long[][] arr, int l, int m, int r) {
        int LLength = m - l + 1;
        int RLength = r - m;
        long[][] L = new long[LLength][2];
        long[][] R = new long[RLength][2];
        for (int i = 0; i < LLength; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < RLength; j++) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0;
        for (int k = l; k < r + 1; k++) {
            if (i == L.length) {
                arr[k] = R[j];
                j++;
                continue;
            }
            if (j == R.length) {
                arr[k] = L[i];
                i++;
                continue;
            }
            if (L[i][0] >= R[j][0]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
    }

    static void sort(long[][] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
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
