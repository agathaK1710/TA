import java.util.*;
import java.io.*;

public class Chords implements Runnable {

    public static void main(String[] args) {
        new Thread(null, new Chords(), "", 64 * 1024 * 1024).start();
    }

    public static long[] tree;

    public void setSize(int size){
        tree = new long [size];
    }

    public static long sum(int x, int first, int last, int L, int R) {
        if ( R <= last && first <= L) {
            return tree[x - 1];
        }
        if (first > R || L > last) {
            return 0;
        }
        return sum(x * 2,first, last, L,(L + R) / 2) + sum(x * 2 + 1, first, last, (L + R) / 2 + 1, R);
    }

    public static void modification(int x, int index, int val, int L, int R) {
        if (index == L && index == R) {
            tree[x - 1] = val;
            return;
        }
        if (index > R || index < L) {
            return;
        }
        modification(x * 2, index, val, L, (L + R) / 2);
        modification(x * 2 + 1, index, val, (L + R) / 2 + 1, R);
        tree[x - 1] = tree[x * 2 - 1] + tree[x * 2];
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("chords.in"));
            PrintWriter writer = new PrintWriter("chords.out");
            int n = Integer.parseInt(reader.readLine());
            Chords tree = new Chords();
            tree.setSize(8*n);

            long res = 0;

            ArrayList<Chorda> chordas = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                String[] str = reader.readLine().split(" ");
                chordas.add(new Chorda(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
            }

            Comparator <Chorda> comparator = Comparator.comparingInt(ch -> ch.ai);
            chordas.sort(comparator);

            for (Chorda chord : chordas) {
                res += sum(1, chord.ai - 1, chord.bi - 1, 0, 2 * n - 1);
                modification(1, chord.ai - 1, 1, 0, 2 * n - 1);
                modification(1, chord.bi - 1, 1, 0, 2 * n - 1);

            }
          
            writer.write(String.valueOf(res));
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Chorda {
        int ai;
        int bi;

        public Chorda(int first, int last){
            if (first > last) {
                this.ai = last;
                this.bi = first;
            }
            else{
                this.ai = first;
                this.bi = last;
            }
        }

    }
}