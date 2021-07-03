import java.util.*;
import java.io.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();

    }

    public int matrixMultipl(int[] arrayOfSize) {
        int[][] matrix = new int [arrayOfSize.length - 1][arrayOfSize.length - 1];

        for (int i = 0; i < arrayOfSize.length - 1; i++) {
            matrix[i][i] = 0;
        }

        for (int i = 1; i < arrayOfSize.length - 1; i++) {
            for (int j = 0; j < arrayOfSize.length - 1 - i; j++) {
                int t = i + j;
                matrix[j][t] = 2147483647;
                for (int n = j; n < t; n++) {
                    int tmp = matrix[j][n] + matrix[n+1][t] + arrayOfSize[j] * arrayOfSize[t+1] * arrayOfSize[n+1] ;
                    matrix[j][t] = Math.min(matrix[j][t], tmp);
                }
            }
        }
        return matrix[0][arrayOfSize.length - 2];
    }

    @Override
    public void run() {
        try {
            FastScanner reader = new FastScanner("input.txt");
            PrintWriter writer = new PrintWriter("output.txt");

            int size = reader.nextInt();
            int [] arrayOfSize = new int[size+1];

            for (int i = 0; i < size; i++) {
                arrayOfSize[i] = reader.nextInt();
                arrayOfSize[i + 1] = reader.nextInt();
            }

            writer.write(String.valueOf(matrixMultipl(arrayOfSize)));
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