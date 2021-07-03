import java.util.*;
import java.io.*;
public class Palindrome implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Palindrome(), "", 64 * 1024 * 1024).start();

    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            PrintWriter writer = new PrintWriter("output.txt");
            ArrayList<Character> arr = new ArrayList<>();
            int c;
            while ((c = reader.read()) != -1) {
                arr.add((char) c);
            }

            int[][] matrix = new int[arr.size()][arr.size()];
            for (int i = 0; i < arr.size(); i++) {
                matrix[i][i] = 1;
            }

            for (int i = 0; i < arr.size()-1; i++) {
                if (arr.get(i) == arr.get(i + 1)) {
                    matrix[i][i + 1] = 2;
                } else {
                    matrix[i][i + 1] = 1;
                }
            }

            for (int j = 2; j < arr.size(); j++) {
                for (int i = j-2; i>=0 ; i--) {
                    if (arr.get(i) == arr.get(j)) {
                        matrix[i][j] = matrix[i + 1][j - 1] + 2;
                    }
                    else {
                        matrix[i][j] = Math.max(matrix[i + 1][j], matrix[i][j - 1]);
                    }
                }
            }
            int first = 0;
            int last = arr.size()-1;
            Character [] result = new Character[matrix[0][arr.size()-1]];
            StringBuilder palindrome = new StringBuilder();
            int rInd=matrix[0][arr.size()-1]-1;
            int lInd = 0;
            while (first<=last) {
                if (first == last && matrix[first][last] == 1) {
                    result[lInd++] = arr.get(first++);
                }
                else {
                    if (arr.get(first) == arr.get(last)) {
                        result[lInd++] = arr.get(first++);
                        result[rInd--] = arr.get(last--);
                    }
                    else {
                        if (matrix[first + 1][last] > matrix[first][last - 1]) {
                            first++;
                        }
                        else {
                            last--;
                        }
                    }
                }
            }

            for (Character character : result) {
                palindrome.append(character);
            }
            writer.write(String.valueOf(matrix[0][arr.size()-1]));
            writer.write("\n" + palindrome);

            reader.close();
            writer.flush();
        } catch(IOException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }
}