import java.util.*;
import java.io.*;
public class Roads implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Roads(), "", 64 * 1024 * 1024).start();
    }

    final int SIZE = 500003;
    int [] parent = new int[SIZE];
    int [] size = new int [SIZE];

    public void MakeSet (int v) {
        parent[v] = v;
        size[v] = 1;
    }
    public int FindSet(int v) {
        if (v == parent[v])
            return v;
        return parent[v] = FindSet(parent[v]);
    }

    void UnionSets (int a, int b) {
        a = FindSet (a);
        b = FindSet (b);
        if (a != b) {
            if (size[a] < size[b]){
                int tmp = a;
                a = b;
                b = tmp;
            }
            parent[b] = a;
            size[a] += size[b];
        }
    }
    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            String [] str = reader.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int numOfinq = Integer.parseInt(str[1]);

            int u;
            int v;
            int l = n;

            for (int i=0; i<n; ++i){
               MakeSet(i);
            }
            for (int i = 0; i < numOfinq; ++i){
                str = reader.readLine().split(" ");
                u = Integer.parseInt(str[0]);
                v = Integer.parseInt(str[1]);
                if (FindSet(u)!= FindSet(v)){
                    UnionSets(u,v);
                    l--;
                }
                writer.write(String.valueOf(l) + "\n");
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}