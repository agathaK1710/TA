import java.util.*;
import java.io.*;
public class AdjList implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new AdjList(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            String [] str = reader.readLine().split(" ");

            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int num;
            ArrayList [] arrayLists = new ArrayList[n];

            for(int i=0;i<n;i++){
                arrayLists[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; ++i){
                str = reader.readLine().split(" ");
                int u = Integer.parseInt(str[0]);
                int v = Integer.parseInt(str[1]);
                arrayLists[u-1].add(v);
                arrayLists[v-1].add(u);
            }
            for(int i=0;i<n;i++){
                num = arrayLists[i].size();
                writer.write(num + " ");
                for(int j=0; j < arrayLists[i].size();j++){
                    writer.write(arrayLists[i].get(j)+" ");
                }
                writer.println();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}