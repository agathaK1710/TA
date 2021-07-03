import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Dijkstra implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Dijkstra(), "", 64 * 1024 * 1024).start();
    }


    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            String[] str = reader.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            boolean [] processed = new boolean[n];
            long [] dist = new long[n];
            Arrays.fill(processed, false);
            Arrays.fill(dist, Long.MAX_VALUE);
            ArrayList<ArrayList<Pair>> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                str = reader.readLine().split(" ");
                int u = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);
                int w = Integer.parseInt(str[2]);
                if(u != v) {
                    arr.get(u - 1).add(new Pair(v - 1, w));
                    arr.get(v - 1).add(new Pair(u - 1, w));
                }
            }
            Comparator <Pair> comparator = Comparator.comparingLong(v -> v.mark);
            PriorityQueue <Pair> pq = new PriorityQueue<>(comparator);
            pq.add(new Pair(0,0L));
            Pair b;
            while (!pq.isEmpty()) {
                b = pq.poll();
                if(!processed[b.a]){
                    processed[b.a] = true;
                    if(b.a == 0){
                        dist[b.a] = 0L;
                    }else{
                        dist[b.a] = b.mark;
                    }
                    for (int i = 0; i < arr.get(b.a).size(); i++) {
                        pq.add(new Pair(arr.get(b.a).get(i).a, dist[b.a] + arr.get(b.a).get(i).b));
                    }
                }
;            }
            writer.write(String.valueOf(dist[n-1]));
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class Pair{
        int a;
        int b;
        long mark;

        public Pair (int a, int b){
            this.a = a;
            this.b = b;
        }

        public Pair (int a, long mark){
            this.a = a;
            this.mark = mark;
        }
    }
}