import java.util.*;
import java.io.*;
public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            long n = Long.parseLong(reader.readLine());
            int MOD = 1000000007;
            long res = 0;
            long f1 = 1;
            long f2 = 2;

            if (n == 1){
                res = f1;
            }
            else if (n == 2){
                res = f2;
            }
            else{
                for (int i = 3; i<=n; ++i) {
                    res = f1 % MOD + f2 % MOD;
                    f1 = f2 % MOD;
                    f2 = res % MOD;
                }
            }

            writer.write(String.valueOf(res % MOD));

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}