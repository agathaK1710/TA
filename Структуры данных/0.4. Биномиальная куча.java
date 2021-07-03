import java.util.*;
import java.io.*;
public class BinomialTree implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new BinomialTree(), "", 64 * 1024 * 1024).start();

    }

    @Override
    public void run() {
        try {
            FileReader reader= new FileReader("input.txt");
            Scanner scan = new Scanner(reader);
            PrintWriter writer = new PrintWriter("output.txt");
            long number = scan.nextLong();
            ArrayList <Integer> deg2 = new ArrayList<>();
            int deg = 0;

            while (number!=0) {
                if (number%2 == 1){
                    deg2.add(deg);
                    number--;
                }
               deg++;
               number/=2;
            }

            for (Integer integer : deg2) {
                writer.write(integer + "\n");
            }
            reader.close();
            writer.flush();
        } catch(IOException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }
}