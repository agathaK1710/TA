import java.util.*;
import java.io.*;
public class Canonic implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Canonic(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            int n = Integer.parseInt(reader.readLine());
            int [][] matrix = new int[n][n];
            ArrayList<Edge> edges = new ArrayList<>();
            int [] res = new int[n];

            for (int i = 0; i < n; ++i){
                String [] str = reader.readLine().split(" ");
                for (int j = 0; j < n; ++j){
                    matrix[i][j] = Integer.parseInt(str[j]);
                }
            }

            for (int i = 0; i < n; ++i){
                for (int j = 0; j < n; ++j){
                    if (matrix[i][j] == 1) {
                        edges.add(new Edge(i+1, j+1));
                    }
                }
            }
            for (Edge edge : edges) {
                res[edge.last - 1] = edge.first;
            }
            for (int i = 0; i < n; ++i){
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