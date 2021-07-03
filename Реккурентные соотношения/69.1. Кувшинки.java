import java.util.*;
import java.io.*;
public class Frog implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Frog(), "", 64 * 1024 * 1024).start();

    }
    @Override
    public void run() {
        try {
            FastScanner reader = new FastScanner("input.txt");
            PrintWriter writer = new PrintWriter("output.txt");
            int numOfLilies = reader.nextInt();
            int [] nenuphar = new int [numOfLilies+1];
            int [] answer = new int [numOfLilies+1];

            for(int i=1; i<=numOfLilies; ++i) {
                nenuphar[i] = reader.nextInt();
            }

            for (int i = 1; i <= numOfLilies; i++) {
                if (i == 1){
                    answer[1] = nenuphar[1];
                }
                if (i==2){
                    answer[2] = -1;
                }
                if (i+2 <= numOfLilies){
                    answer[i+2] = Math.max(answer[i+2], answer[i] + nenuphar[i+2]);
                }
                if(i+3 <= numOfLilies){
                    answer[i+3] = Math.max(answer[i+3], answer[i] + nenuphar[i+3]);
                }
            }
            writer.write(String.valueOf(answer[numOfLilies]));
            writer.flush();

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    static class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String str = reader.readLine();
                if (str == null) {
                    throw new EOFException();
                }
                tokenizer = new StringTokenizer(str);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}