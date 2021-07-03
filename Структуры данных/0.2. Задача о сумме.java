import java.util.*;
import java.io.*;
public class Sum implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Sum(), "", 64 * 1024 * 1024).start();
    }

    public void build (long[] arr, long[] b) {
        for (int i=0; i < arr.length; ++i){
            b[i / b.length] += arr[i];
        }
    }

    public long FindSum(long [] arr, long [] b, int l, long r) {
        long sum = 0;
        for (int i=l; i<r; )
            if (i % b.length == 0 && i + b.length - 1 < r) {
                sum += b[i / b.length];
                i += b.length;
            }
            else {
                sum += arr[i];
                ++i;
            }
        return sum;
    }

    public void Add(long[] arr, long [] b, int i, long x) {
        arr[i] += x;
        b[i/b.length] += x;
    }

    @Override
    public void run(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int size = Integer.parseInt(reader.readLine());
            String array = reader.readLine();
            String [] arrs = array.split(" ");
            long [] arr = new long[size];
            for (int i = 0; i<arrs.length; ++i){
                arr[i] = Long.parseLong(arrs[i]);
            }

        int len = (int)Math.sqrt(arr.length) + 1;
        long [] b = new long[len];
        build(arr,b);

        int numOfInquiry = Integer.parseInt(reader.readLine());
        String str;
        String [] words;
        int i;
        long x;
        for (int j = 0; j<numOfInquiry; ++j){
            str = reader.readLine();
            words = str.split(" ");
            i = Integer.parseInt(words[1]);
            x = Long.parseLong(words[2]);
            if (words[0].equals("FindSum")){
                System.out.println(String.valueOf(FindSum(arr,b,i,x)));
            }
            else if (words[0].equals("Add")){
                Add(arr,b,i,x);
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}