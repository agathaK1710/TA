import java.util.*;
import java.io.*;
public class Huffman implements Runnable {
    static Node root;
    static long length;

    public static void main(String[] args) {
        new Thread(null, new Huffman(), "", 64 * 1024 * 1024).start();
    }

    public static long getLength (Node node, int height) {
        if (node != null) {
            height++;
            getLength(node.left,height);
            getLength(node.right,height);
            if ((node.left == null && node.right == null)) {
                length += node.data * (--height);
            }
        }
        return length;
    }

    public static long getLength () {
        return getLength(root, 0);
    }
    public static void buildHuffmanTree(PriorityQueue<Long> freq, int n){
        Node m1;
        Node m2;
        Node node;
        long  sum;

        Comparator <Node> comparator = Comparator.comparingLong(nod -> nod.data);
        PriorityQueue<Node> roots = new PriorityQueue<>(comparator);
        Long [] f = new Long[n];
        freq.toArray(f);
        for(int i = 0;i < n;i++){
            roots.add(new Node(Math.toIntExact(f[i])));
        }
        while(roots.size() != 1){
            m1 = roots.peek();
            roots.remove();
            m2 = roots.peek();
            roots.remove();
            sum = m1.data + m2.data;
            node = new Node(sum);
            node.right = m2;
            node.left = m1;
            root = node;
            roots.add(node);
        }
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader ("huffman.in"));
            PrintWriter writer = new PrintWriter("huffman.out");

            int n = Integer.parseInt(reader.readLine());

            PriorityQueue<Long> freq = new PriorityQueue<>();
            String str = reader.readLine();
            String [] arr = str.split(" ");

            for (int i = 0; i < n; ++i){
                freq.add(Long.parseLong(arr[i]));
            }

            buildHuffmanTree(freq, n);
            writer.write(String.valueOf(getLength()));

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
};
class Node {
    long data;
    Node left;
    Node right;

    public Node() {
        this(0, null, null);
    }

    public Node(long e, Node aLeft, Node aRight) {
        data = e;
        left = aLeft;
        right = aRight;
    }

    Node(long value) {
        this.data = value;
        right = null;
        left = null;
    }
}