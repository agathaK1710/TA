import java.util.*;
import java.io.*;

public class FenwickTree{
    private static long[] tree;

    public FenwickTree(int n){
        tree = new long[n];
        for (int i = 0; i<n; ++i){
            tree[i] = 0;
        }
    }

    public void Increment(int i, int n){
        for (; i<=n; i = (i | (i+1))){
            tree[i]++;
        }
    }

    public long Sum(int i) {
        long result = 0;
        for (; i >= 0;i = (i & (i + 1)) - 1)
            result += tree[i];
        return result;
    }

    public void Upgrade(int n){
        for (int i = 0; i<n; ++i){
            tree[i] = 0;
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            int n = Integer.parseInt(reader.readLine());
            int [] arr = new int [n];
            long [] pref = new long[n];
            long res = 0;
            FenwickTree tr = new FenwickTree(n+1);
            for (int i = 0; i < n; ++i){
                arr[i] = Integer.parseInt(reader.readLine());
            }
            for (int i = 1; i <= n; ++i){
                tr.Increment(arr[i-1], n);
                pref[i-1] = i - tr.Sum(arr[i-1]);
            }

            tr.Upgrade(n+1);
            for(int i = n; i >=1; i--){
                tr.Increment(arr[i-1], n);
                res += pref[i-1] * tr.Sum(arr[i-1] - 1);
            }

            writer.write(String.valueOf(res));
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}