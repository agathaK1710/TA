import java.util.*;
import java.io.*;
public class AdjMatrix implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new AdjMatrix(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            String [] str = reader.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            int [][] matrix = new int[n][n];
            for (int i = 0; i < n; ++i){
                for (int j = 0; j < n; ++j){
                    matrix[i][j] = 0;
                }
            }

            for (int i = 0; i < m; ++i){
                str = reader.readLine().split(" ");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                matrix [x-1][y-1]++;
                matrix [y-1][x-1]++;
            }
            for (int i = 0; i < n; ++i){
                for (int j = 0; j < n; ++j){
                    writer.write(matrix[i][j] + " ");
                }
                writer.write("\n");
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}