import java.util.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    long MOD = 1000000007;

    public long specialFoo(long a, long pow, long mod){
        long p = 1;
        while (pow > 0) {
            if (pow % 2 == 1) {
                p *= a;
                p %= mod;

            }
            a *= a;
            a %= mod;
            pow >>= 1;
        }
        return p;
    }
    @Override
    public void run() {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        int k = reader.nextInt();
        long num = 1;
        long denom = 1;
        for (int i = 1; i <= k; ++i) {
            denom = (denom * i) % MOD;
        }
        for (int i = n; i > n - k; --i) {
            num = (num * i) % MOD;
        }
        System.out.println(String.valueOf((num * specialFoo(denom, (MOD-2),MOD)) % MOD));
    }
}