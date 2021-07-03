import java.util.*;
import java.io.*;
public class Parquet implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Parquet(), "", 64 * 1024 * 1024).start();
    }
    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("in.txt"));
            PrintWriter writer = new PrintWriter("out.txt");
            long n = Long.parseLong(reader.readLine());
            long mod = 1000000007;
            long f27 = 5;
            long f21 = 1;
            long x, y;
            if (n % 2 != 0){
                f27 = 0;
            }
            else{
                for (int i = 2; i+2<= n; i=i+2) {
                    x = (5 * f27) % mod;
                    x += (4 * f21) % mod;
                    y = f27 % mod;
                    y += (2 * f21) % mod;
                    f27 = x % mod;
                    f21 = y % mod;
                }
            }
            writer.write(String.valueOf(f27));
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}