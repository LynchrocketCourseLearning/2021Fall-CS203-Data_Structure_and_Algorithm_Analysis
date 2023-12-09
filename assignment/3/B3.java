package dsaa;

import java.io.*;
import java.util.*;

public class B3 {
    public static void main(String[] args) {
        QReader sc = new QReader();
        QWriter out = new QWriter();
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextLong();

        long[] a = sort(arr, 0, n - 1);

        long res = 0;
        if (n % 2 == 1) res = 2 * a[n / 2];
        else res = a[n / 2 - 1] + a[n / 2];
        out.print(res);
        out.close();
    }

    public static long[] sort(long[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);

            merge(a,low,mid,high);
        }
        return a;
    }

    public static void merge(long[] a, int low, int mid, int high) {
        long[] temp = new long[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;

        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }

        while(i<=mid) temp[k++] = a[i++];
        while(j<=high) temp[k++] = a[j++];

        for(int x=0;x<temp.length;x++) a[x+low] = temp[x];

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
