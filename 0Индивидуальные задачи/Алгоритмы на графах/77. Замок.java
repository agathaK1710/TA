import java.io.*;
import java.util.*;

public class Castle implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Castle(), "", 64 * 1024 * 1024).start();
    }

    public static int[] adjX = {0, -1, 0, 1};
    public static int[] adjY = {-1, 0, 1, 0};
    public static int numOfRooms = 0;
    public static int maxAfter;


    public static void DFS(int[] roomSize, int x, int y, int n, int m, boolean [][] visit, int[][][] wall) {
        visit[x][y] = true;
        roomSize[numOfRooms]++;
        wall[x][y][4] = numOfRooms;
        for (int i = 0; i < 4; ++i) {
            int nx = x + adjX[i];
            int ny = y + adjY[i];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                continue;
            }
            if (visit[nx][ny]) {
                continue;
            }
            if (wall[x][y][i] != 0) {
                continue;
            }
            DFS(roomSize, nx, ny, n, m, visit, wall);
        }
    }

    public static void merge (int[] sizeOfRoom, int x, int y, int n, int[][][] wall){
        int sum;
        if (x - 1 > 0 && wall[x][y][4] != wall[x-1][y][4]){
            sum = sizeOfRoom[wall[x][y][4]] + sizeOfRoom[wall[x-1][y][4]];
            if (sum > maxAfter){
                maxAfter = sum;
            }
        }
        if (y+1 < n && wall[x][y][4] != wall[x][y+1][4]){
            sum = sizeOfRoom[wall[x][y][4]] + sizeOfRoom[wall[x][y+1][4]];
            if (sum > maxAfter){
                maxAfter = sum;
            }
        }
    }

    @Override
    public void run () {
        try {
            FastScanner reader = new FastScanner("in");
            PrintWriter writer = new PrintWriter("out");
            int m = reader.nextInt();
            int n = reader.nextInt();
            int[][][] wall = new int[m][n][5];
            int maxRoom = 0;
            boolean[][] visit = new boolean[m][n];
            int[] roomSize = new int[n*m];
            int k;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    k = reader.nextInt();
                    for (int l = 0; l < 4; ++l) {
                        //System.out.println(i + " " + j + " "+ l + " " + k%2);
                        wall[i][j][l] = k % 2;
                        k /= 2;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        roomSize[numOfRooms] = 0;
                        DFS(roomSize,i,j,n,m,visit,wall);
                        numOfRooms++;
                    }
                }
            }

            for (int i = 0; i < numOfRooms; i++) {
                if (roomSize[i] > maxRoom){
                    maxRoom = roomSize[i];
                }

            }
            maxAfter = maxRoom;
            for (int i = 0; i < m; i++) {
                for (int j = n-1; j >=0 ; j--) {
                    merge(roomSize,i, j, n,wall);
                }
            }
            writer.write(String.valueOf(numOfRooms) + "\n");
            writer.write(String.valueOf(maxRoom) + "\n");
            writer.write(String.valueOf(maxAfter));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
//создать несколько процессов