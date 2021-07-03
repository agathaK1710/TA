import java.util.*;
import java.io.*;
public class Vitro implements Runnable{
    public static void main(String[] args) {
        new Thread(null, new Vitro(), "", 64 * 1024 * 1024).start();
    }

    public int hashState(Condition c) {
        return (int) (c.firstVitro * Math.pow(100, 2) + c.secondVitro * 100 + c.thirdVitro);
    }

    public int getNumOfTransfusion(Condition c, int [] division, int x) {
        int div;
        Queue <Condition> q = new LinkedList<>() ;
        int size = (int) (Math.pow(100, 3) + 1);
        boolean [] visited = new boolean[size];
        Arrays.fill(visited, false);
        Condition temp;
        q.add(c);
        while (!q.isEmpty()) {
            temp = q.peek();
            q.poll();
            if (!visited[hashState(temp)]) {
                if (temp.thirdVitro == x) {
                    return temp.numOfTransfusion;
                }
                q.add(new Condition(0, temp.secondVitro + temp.firstVitro, temp.thirdVitro, temp.numOfTransfusion + 1));
                q.add(new Condition(0, temp.secondVitro, temp.thirdVitro + temp.firstVitro, temp.numOfTransfusion + 1));
                q.add(new Condition(temp.firstVitro + temp.secondVitro, 0, temp.thirdVitro, temp.numOfTransfusion + 1));
                q.add(new Condition(temp.firstVitro, 0, temp.thirdVitro + temp.secondVitro, temp.numOfTransfusion + 1));
                q.add(new Condition(temp.firstVitro + temp.thirdVitro, temp.secondVitro, 0, temp.numOfTransfusion + 1));
                q.add(new Condition(temp.firstVitro, temp.secondVitro + temp.thirdVitro, 0, temp.numOfTransfusion + 1));

                for (int m = 0; m < division.length; ++m) {
                    div = division[m];

                    if (temp.firstVitro > div) {
                        q.add(new Condition(div, temp.secondVitro + (temp.firstVitro - div), temp.thirdVitro, temp.numOfTransfusion + 1));
                        q.add(new Condition(div, temp.secondVitro, temp.thirdVitro + (temp.firstVitro - div), temp.numOfTransfusion + 1));
                    }
                    if (temp.secondVitro > div) {
                        q.add(new Condition(temp.firstVitro + (temp.secondVitro - div), div, temp.thirdVitro, temp.numOfTransfusion + 1));
                        q.add(new Condition(temp.firstVitro, div, temp.thirdVitro + (temp.secondVitro - div), temp.numOfTransfusion + 1));
                    }

                    if ((temp.firstVitro - (div - temp.secondVitro) > 0) && (temp.secondVitro < div)) {
                        q.add(new Condition(temp.firstVitro - (div - temp.secondVitro), div, temp.thirdVitro, temp.numOfTransfusion + 1));
                    }
                    if ((temp.secondVitro - (div - temp.firstVitro) > 0) && (temp.firstVitro < div)) {
                        q.add(new Condition(div, temp.secondVitro - (div - temp.firstVitro), temp.thirdVitro, temp.numOfTransfusion + 1));
                    }
                    if ((temp.thirdVitro - (div - temp.firstVitro) > 0) && (temp.firstVitro < div)) {
                        q.add(new Condition(div, temp.secondVitro, temp.thirdVitro - (div - temp.firstVitro), temp.numOfTransfusion + 1));
                    }
                    if ((temp.thirdVitro - (div - temp.secondVitro) > 0) && (temp.secondVitro < div)) {
                        q.add(new Condition(temp.firstVitro, div, temp.thirdVitro - (div - temp.secondVitro), temp.numOfTransfusion + 1));
                    }
                }
                visited[hashState(temp)] = true;
            }
        }
        return Integer.MAX_VALUE;
    }
    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("in.txt"));
            PrintWriter writer = new PrintWriter("out.txt");

            int x = Integer.parseInt(reader.readLine());

            int first = Integer.parseInt(reader.readLine());
            int second = Integer.parseInt(reader.readLine());
            int third = 100 - first - second;
            int num = 0;

            Condition vitro = new Condition(first, second, third, num);

            String str = reader.readLine();
            String [] arr = str.split(" ");

            int [] division = new int[arr.length - 1];

            for (int i = 0; i < division.length; ++i){
                division [i] = Integer.parseInt(arr[i]);
            }

            int result = getNumOfTransfusion(vitro, division, x);

            if (result == Integer.MAX_VALUE) {
                writer.write( "No solution");
            }
            else {
                writer.write(String.valueOf(result));
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
};

class Condition{
    int firstVitro;
    int secondVitro;
    int thirdVitro;
    int numOfTransfusion;

    public Condition (int f, int s, int th, int n){
        this.firstVitro = f;
        this.secondVitro = s;
        this.thirdVitro = th;
        this.numOfTransfusion = n;
    }
}