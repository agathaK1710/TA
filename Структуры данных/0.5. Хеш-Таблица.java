import java.util.*;
import java.io.*;
public class HashTable implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new HashTable(), "", 64 * 1024 * 1024).start();
    }
    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            String [] str = reader.readLine().split(" ");
            int m = Integer.parseInt(str[0]);
            int c = Integer.parseInt(str[1]);
            int n = Integer.parseInt(str[2]);

            int [] hashT = new int[m];
            Arrays.fill(hashT, -1);

            int x;
            boolean attempt;
            int coef;

            for (int i = 0; i<n; ++i){
                x = Integer.parseInt(reader.readLine());
                attempt = true;

                for(int k=0; k< m; k++){
                    if( hashT[k]==x)
                        attempt=false;
                }
                if (attempt){
                    for(int j=0; j < m; j++){
                        coef = (x % m + c*j) % m;
                        if (hashT[coef] == -1 ){
                            hashT[coef] = x;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < m; ++i){
                writer.write(hashT[i] + " ");
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}