import java.util.*;
import java.io.*;
public class BinaryHeap implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new BinaryHeap(), "", 64 * 1024 * 1024).start();

    }
    @Override
    public void run() {
        try {
            FastScanner reader = new FastScanner("input.txt");
            PrintWriter writer = new PrintWriter("output.txt");
            int size = reader.nextInt();
            int [] heap = new int[size+1];
            boolean result = true;
            for (int i=1; i<=size; ++i){
                heap[i] = reader.nextInt();
            }
            if(size%2==0){
                if(!(heap[size]>=heap[size/2])){
                    result = false;
                }
            }
            for (int i=1; 2*i+1<= size; ++i){
                if (heap[i] > heap[2 * i] || heap[i] > heap[2 * i + 1]) {
                    result = false;
                    break;
                }
            }
            if(result){
                writer.write("Yes");
            }
            else {
                writer.write("No");
            }

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