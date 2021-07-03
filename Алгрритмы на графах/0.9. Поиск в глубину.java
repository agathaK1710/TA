import java.util.*;
import java.io.*;
public class DFS implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new DFS(), "", 64 * 1024 * 1024).start();
    }
    public static int num = 1;
    private static void DFS(int index, int res[], int matrix[][], boolean [] visited) {
        visited[index] = true;
        res[index] = num;
        num++;
        for (int i = 0; i < res.length; i++) {
            if (matrix[index][i] == 1 && !visited[i]){
                DFS(i, res, matrix,visited);
            }
        }
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
            Arrays.fill(visited,false);
            Arrays.fill(res,0);

            for (int i = 0; i < n; ++i){
                String [] str = reader.readLine().split(" ");
                for (int j = 0; j < n; ++j){
                    matrix[i][j] = Integer.parseInt(str[j]);
                }
            }
            for (int i = 0; i < n; i++) {
                if (!visited[i]){
                    DFS(i, res, matrix, visited);
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
}