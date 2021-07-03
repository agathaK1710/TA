import java.util.*;
import java.io.*;
public class BFS implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new BFS(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            int n = Integer.parseInt(reader.readLine());
            int [][] matrix = new int[n][n];
            int [] res = new int[n];
            boolean [] visited = new boolean[n];

            for (int i = 0; i < n; ++i){
                String [] str = reader.readLine().split(" ");
                for (int j = 0; j < n; ++j){
                    matrix[i][j] = Integer.parseInt(str[j]);
                }
            }
            Deque <Integer> q = new ArrayDeque<>();
            int num = 0;
            q.push(0);
            visited[0] = true;
            while (num < n){
                while (!q.isEmpty()){
                    int v = q.pop();
                    res[v] = ++num;
                    for (int i = 0; i < n; i++){
                        if(!visited[i] && matrix[v][i] != 0){
                            q.addLast(i);
                            visited[i] = true;
                        }
                    }
                }
                for (int i = 0; i< n;i++){
                    if (!visited[i]){
                        visited[i] = true;
                        q.push(i);
                        break;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                writer.write(res[i] + " ");
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Edge{
        int first;
        int last;

        public Edge(int f, int l){
            this.first = f;
            this.last = l;
        }
    }
}