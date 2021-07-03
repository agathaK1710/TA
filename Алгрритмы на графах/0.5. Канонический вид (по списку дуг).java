import java.util.*;
import java.io.*;
public class CanonicView implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new CanonicView(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            int n = Integer.parseInt(reader.readLine());
            int res [] = new int[n];
            for (int i = 0; i < n-1; ++i){
                String str [] = reader.readLine().split(" ");
                int u = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);
                res[v-1] = u;
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
}