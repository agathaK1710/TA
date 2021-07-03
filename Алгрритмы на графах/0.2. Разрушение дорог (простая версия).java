import java.util.*;
import java.io.*;
public class Roads implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Roads(), "", 64 * 1024 * 1024).start();
    }

    int [] parent;
    int [] size;

    public void MakeSet(int v) {
        parent[v] = v;
        size[v] = 1;
    }
    public int FindSet(int v) {
        if (v == parent[v])
            return v;
        return parent[v] = FindSet(parent[v]);
    }

    public void UnionSets (int a, int b) {
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
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            String [] str = reader.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int q = Integer.parseInt(str[2]);

            parent = new int[n+1];
            size = new int[n+1];

            int s = 0;
            for(int i = 1; i < n + 1 ; i++) {
                MakeSet(i);
                s++;
            }

            boolean flag;
            boolean [] fl = new boolean [m+1];

            Road [] roads = new Road[m+1];
            int [] delete = new int[q+1];
            int [] result = new int [q+1];
            Arrays.fill(result, 1);

            for (int i = 1; i < m+1; ++i){
                str = reader.readLine().split(" ");
                int u = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);
                roads[i] = new Road(u,v);
            }


            for (int i = 1; i < q+1; ++i) {
                int a = Integer.parseInt(reader.readLine());
                delete[i] = a;
                fl[a] = true;
            }

            for (int i = 1; i < m + 1; i++) {
                if (!fl[i]) {
                    int a = roads[i].a;
                    int b = roads[i].b;
                    flag = FindSet(a) == FindSet(b);
                    UnionSets(a, b);
                    if (!flag) {
                        s--;
                    }
                }

            }

            if(s!=1) {
                for (int i = q; i >= 1; i--) {
                    if (fl[delete[i]]) {
                        int a = roads[delete[i]].a;
                        int b = roads[delete[i]].b;
                        flag = FindSet(a) == FindSet(b);
                        UnionSets(a, b);
                        if (!flag) {
                            s--;
                        }
                        if (s == 1) {
                            result[i]=0;
                            break;
                        } else
                            result[i] = 0;
                    }


                }
            }

            for(int i = 1; i < q+1; i++){
                writer.write(String.valueOf(result[i]));
            }

            reader.close();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    static class Road{
        int a;
        int b;

        public Road(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
}