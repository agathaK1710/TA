import java.util.*;
import java.io.*;

public class Tree implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();

    }

    @Override
    public void run() {
        try {
            FastScanner reader = new FastScanner("bst.in");
            PrintWriter writer = new PrintWriter("bst.out");
            int amount = reader.nextInt();
            NodeMm[] node = new NodeMm[amount];
            long maximum = 2147483648L;

            int data = reader.nextInt();
            node[0] = new NodeMm(data, Integer.MIN_VALUE, maximum);
            int vertex;
            int number;
            String meaning;
            boolean res = true;
            for (int i = 1; i < amount; ++i) {
                vertex = reader.nextInt();
                number = reader.nextInt();
                meaning = reader.next();

                if (meaning.equals("R")) {
                    if (vertex < node[number - 1].data || vertex >= node[number - 1].max) {
                        res = false;
                    }
                    node[i] = new NodeMm(vertex, node[number - 1].data, node[number - 1].max);
                } else {
                    if (vertex >= node[number - 1].data || vertex < node[number - 1].min) {
                        res = false;
                    }
                    node[i] = new NodeMm(vertex, node[number - 1].min, node[number - 1].data);
                }
            }
                if (res) {
                    writer.write("YES");
                } else writer.write("NO");
                writer.flush();
                
            } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    static class NodeMm {
        int data;
        int min;
        long max;

        public NodeMm(int data, int minval, long maxval) {
            this.data = data;
            this.min = minval;
            this.max = maxval;
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
