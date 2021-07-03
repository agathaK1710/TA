import java.io.*;
import java.util.*;

public class maxFlow implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new maxFlow(), "", 64 * 1024 * 1024).start();
    }
    public static int n;
    public static int m;
    public static int[] arr;
    public static long[] distance;
    public static int [] q;
    public static Vector<Vector<Integer>> g;
    public static Vector<Edge> edge ;

    static public void addEdge(int a, int b, int cap){
        Edge e1 = new Edge(a, b, cap, 0);
        Edge e2 = new Edge(b, a, 0, 0);
        g.get(a).add((int)edge.size());
        edge.add(e1);
        g.get(b).add((int)edge.size());
        edge.add(e2);
    }

    public static boolean bfs(){
        int gh = 0;
        int gt = 0;
        q[gt++] = 1;
        Arrays.fill(distance, -1);
        distance[1] = 0;
        while (gh < gt && distance[n] == -1){
            int v = q[gh++];
            for (int i = 0; i < g.get(v).size(); i++) {
                int id = g.get(v).get(i);
                int to = edge.get(id).b;
                if (distance[to] == -1 && edge.get(id).flow < edge.get(id).cap) {
                    q[gt++] = to;
                    distance[to] = distance[v] + 1;
                }
            }
        }
        return distance[n]!=-1;
    }

    public static int dfs(int v, int flow){
        if(flow == 0) return 0;
        if (v == n) return flow;
        for (; arr[v] < g.get(v).size() ; ++arr[v]) {
            int id = g.get(v).get(arr[v]);
            int to = edge.get(id).b;
            if (distance[to] != distance[v] + 1)  continue;
            int pushed = dfs (to, Math.min(flow, edge.get(id).cap - edge.get(id).flow));
            if (pushed!=0) {
                edge.get(id).flow += pushed;
                edge.get(id ^ 1).flow -= pushed;
                return pushed;
            }
        }
        return 0;
    }

    public static int Dinic(){
        int flow = 0;
        while (bfs()) {
            Arrays.fill(arr, 0);
            int pushed = dfs(1, Integer.MAX_VALUE);
            while (pushed != 0) {
                flow += pushed;
                pushed = dfs(1, Integer.MAX_VALUE);
            }
        }
        return flow;
    }

    @Override
    public void run(){
        Scanner reader = new Scanner(System.in);
        n = reader.nextInt();
        m = reader.nextInt();
        distance = new long[n*(n-1)/2 + 1];
        arr = new int[n+1];
        g = new Vector<>();
        q = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
            g.add(new Vector<>());
        }
        edge = new Vector<>();

        for (int i = 0; i <m; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();
            int w = reader.nextInt();
            addEdge(u,v,w);
        }
        
        System.out.println(Dinic());
    }
    static class Edge{
        int a;
        int b;
        int cap;
        int flow;

        Edge(int a, int b, int cap, int flow){
            this.a = a;
            this.b = b;
            this.cap = cap;
            this.flow = flow;
        }
    }
}