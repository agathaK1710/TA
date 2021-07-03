import java.util.*;
import java.io.*;

public class Tree {
    Node root;
    static long sum;
    public void add (int value) {
        Node node = root;
        Node parent = null;
        Node nod = new Node(value);
        while (node != null) {
            parent = node;
            if (value > node.data) {
                node = node.right;
            }
            else if (value < node.data) {
                node = node.left;
            }
            else {
                return;
            }
        }
        sum +=value;

        if (parent == null) {
            root = nod;
        }
        else if (value > parent.data) {
            parent.right = nod;
        }
        else if (value < parent.data) {
            parent.left = nod;

        }
    }
    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        try {
            Tree tree = new Tree();
            FileReader reader= new FileReader("input.txt");
            Scanner scan = new Scanner(reader);
            FileWriter writer = new FileWriter("output.txt");

            while (scan.hasNext()) {
                tree.add(scan.nextInt());
            }
            if (!tree.isEmpty()) {
                writer.write(String.valueOf(sum));
            }
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

};
class Node {
    int data;
    Node left;
    Node right;

    public Node() {
        this(0, null, null);
    }

    public Node(int e, Node aLeft, Node aRight) {
        data = e;
        left = aLeft;
        right = aRight;
    }

    Node(int value) {
        this.data = value;
        right = null;
        left = null;
    }
}
