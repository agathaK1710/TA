import java.util. *;
import java.io. *;

public class Tree {
    Node root;
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


    private void PreLeftOrder (Node node, FileWriter writer) throws IOException {
        Stack<Node> container = new Stack<Node>();
        while (true) {
            if (node != null) {
                container.push(node);
                writer.write(String.valueOf(node.data) + '\n');
                node = node.left;
            }
            else if (!container.isEmpty()) {
                node = container.pop();
                node = node.right;
            }
            else {
                break;
            }
        }
    }

    public void PreLeftOrder (FileWriter writer) throws IOException {
        PreLeftOrder(root, writer);
    }

    private Node delete(Node node, int value) {
        if (node != null) {

            if (value < node.data) {
                node.left = delete(node.left, value);
                return node;
            }
            if (value == node.data) {
                if (node.left == null && node.right == null) {
                    return null;
                }
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }
                node.data = leftmost(node.right);
                node.right = delete(node.right, node.data);
                return node;
            }
            node.right = delete(node.right, value);
        }
        return node;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private int leftmost(Node root) {
        if (root.left == null){
            return root.data;
        }
        else{
            return leftmost(root.left);
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

            int val = scan.nextInt();
            while (scan.hasNext()) {
                tree.add(scan.nextInt());
            }
            if (!tree.isEmpty()) {
                tree.delete(val);
                tree.PreLeftOrder(writer);
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
