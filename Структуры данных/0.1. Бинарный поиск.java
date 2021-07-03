import java.util.*;
public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public int binS(int [] arr, int value){
        int first = 0;
        int last = arr.length;
        int answer = 0;
        while (first < last) {
            int centre = (first + last) / 2;
            if (arr[centre] == value ) {
                answer = 1;
                break;
            }
            else if (arr[centre] > value) {
                last = centre;
            }
            else if (arr[centre] < value) {
                first = centre + 1;
            }
        }
        return answer;
    }

    public int notStrictUpper(int [] arr, int value){
        int first = 0;
        int last = arr.length;
        while (first < last) {
            int centre = (first + last) / 2;
            if (arr[centre] >= value)
                last = centre;
            else
                first = centre + 1;
        }
        return first;
    }

    public int strictUpper(int [] arr, int value){
        int first = 0;
        int last = arr.length;
        while (first < last) {
            int centre = (first + last) / 2;
            if (arr[centre] > value) {
                last = centre ;
            }
            else first = centre + 1;
        }
        return first;
    }

    @Override
    public void run() {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        int [] arr = new int[n];
        for (int i=0; i<n; ++i){
            arr[i] = reader.nextInt();
        }
        int k = reader.nextInt();
        int [] inquiry = new int[k];
        for (int i=0; i<k; ++i) {
            inquiry[i] = reader.nextInt();
        }
        reader.close();

        for (int i = 0; i<k; ++i) {
            System.out.println(binS(arr, inquiry[i]) + " " + notStrictUpper(arr,inquiry[i]) + " " + strictUpper(arr, inquiry[i]));
        }
    }
}