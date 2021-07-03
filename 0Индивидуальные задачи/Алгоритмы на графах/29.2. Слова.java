import java.io.*;
import java.util.*;

public class Words implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Words(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            int n = Integer.parseInt(reader.readLine());
            int alphabet = 26;

            ArrayList<ArrayList<LinkedList<String>>> symbols = new ArrayList<>();

            for (int i = 0; i < alphabet; ++i){
                symbols.add(new ArrayList<>());
            }

            for (int i = 0; i < alphabet; ++i) {
                for (int j = 0; j < alphabet; ++j) {
                    symbols.get(i).add(new LinkedList<>());
                }
            }

            int[][] matrix = new int[alphabet][alphabet];

            for (int i = 0; i < n; i++) {
                String word = reader.readLine();
                int a = word.charAt(0) - 97;
                int b = word.charAt(word.length()-1) - 97;
                symbols.get(a).get(b).add(word);
                matrix[a][b] = symbols.get(a).get(b).size();
            }

            Stack<Integer> s = new Stack<>();
            Deque <Integer> result = new ArrayDeque<>();

            int [] degrees = new int[alphabet];

            for (int i = 0; i < alphabet; ++i) {
                for (int j = 0; j < alphabet; ++j) {
                    degrees[i] += matrix[i][j];
                }
            }

            int ind = 0;
            while (degrees[ind] == 0){
                ++ind;
            }
            s.push(ind);

            while (!s.empty()) {
                int i = 0;
                int vert = s.lastElement();
                if (degrees[vert] == 0) {
                    result.add(vert);
                    s.pop();
                }
                else {
                    boolean flag = true;
                    while (flag) {
                        if (matrix[vert][i] ==0) {
                            i++;
                        }
                        else {
                            flag = false;
                        }
                    }
                    degrees[vert]--;
                    matrix[vert][i]--;
                    s.push(i);
                }
            }

            boolean check = false;
            ArrayList<String> res = new ArrayList<>();

            if (n+1 == result.size() && result.getFirst().equals(result.getLast())) {
                String words;
                for (int i = 0; i < n; i++) {
                    words = symbols.get(result.pollLast()).get(result.peekLast()).pollFirst();
                    if (words == null){
                        writer.write("No");
                        writer.close();
                        return;
                    }
                    res.add(words);
                }
                check = true;
            }
            if (check){
                writer.write("Yes" + "\n");
                for(int i = 0; i < n; i++){
                    writer.write(res.get(i) + "\n");
                }
            }
            else {
                writer.write("No");
            }
            reader.close();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}