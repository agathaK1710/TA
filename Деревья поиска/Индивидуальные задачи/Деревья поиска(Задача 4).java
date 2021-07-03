import java.util. *;
import java.io. *;

public class Tree implements Runnable {

    static Node root;
    ArrayList<Node> arrList = new ArrayList<>();
    static ArrayList<Integer> list = new ArrayList<>();
    
    int Height;

    public static void main(String[] args) {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    public void add(int value) {
        Node node = root;
        Node parent = null;
        Node nod = new Node(value);
        while (node != null) {
            parent = node;
            if (value > node.data) {
                node = node.right;
            } else if (value < node.data) {
                node = node.left;
            } else {
                return;
            }
        }

        if (parent == null) {
            root = nod;
        } else if (value > parent.data) {
            parent.right = nod;
        } else {
            parent.left = nod;
        }
    }
    public void getRH(Node node) {
        if (node != null) {
            node.level = (Height - deepNode(node));
            if (node.level == (Height/2)) {
                arrList.add(node);
            } else if (node.level < (Height/2))
                return;
            getRH(node.left);
            getRH(node.right);
        }
    }

    private void PreLeftOrder(Node node, PrintWriter writer) throws IOException {
        Stack<Node> container = new Stack<Node>();
        while (true) {
            if (node != null) {
                container.push(node);
                writer.write(String.valueOf(node.data) + '\n');
                node = node.left;
            } else if (!container.isEmpty()) {
                node = container.pop();
                node = node.right;
            } else {
                break;
            }
        }
    }

    public void PreLeftOrder(PrintWriter writer) throws IOException {
        PreLeftOrder(root, writer);
    }

    private static void replace_child(Node parent, Node node,Node nod) {
        if (parent == null) {
            root = nod;
        }
        else if (parent.left == node)
            parent.left = nod;
        else if(parent.right == node)
            parent.right = nod;
    }

    public static void delete(int value){
        Node parent = null;
        Node node = root;
        Node result;
        Node nod;
        Node newNode;
        while (true) {
            if ( value == node.data)
                break;
            else if (value < node.data) {
                if (node.left == null) return;
                parent = node;
                node = node.left;
            } else {
                if (node.right==null) return;
                parent = node;
                node = node.right;
            }
        }
        if (node.left == null){
            result=node.right;
        }
        else if (node.right == null){
            result = node.left;
        }
        else {
            nod = node;
            newNode = node.right;
            while (newNode.left != null) {
                nod = newNode;
                newNode = newNode.left;
            }
            result = node;
            node.data = newNode.data;
            replace_child(nod, newNode, newNode.right);
        }
        replace_child(parent, node, result);
    }


    public int height(Node node) {
        if (node != null) {
            return Math.max(height(node.left), height(node.right)) + 1;
        } else {
            return -1;
        }
    }

    public static int deepNode(Node node) {
        Node r=root;
        node.depth=0;
        while (true) {
            if (r.data == node.data ) {
                return r.depth;
            } else if (node.data< r.data) {
                r=r.left;
                node.depth++;
            } else {
                r=r.right;
                node.depth++;
            }
        }
    }

    public int getNumberOfSons(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getNumberOfSons(node.left) + getNumberOfSons(node.right);
    }

    public void NeededVertex(Node node, ArrayList<Node> arr) {
        Height = height(root);
        getRH(root);
        LinkedList<Integer> answer= new LinkedList<>();
        for (Node value : arrList) {
            if (getNumberOfSons((value.left)) > getNumberOfSons((value.right))) {
                answer.add((value.data));
            }
        }
        if (answer.size() % 2 != 0){
            delete((answer.get(answer.size()/2)));
        }
    }

    @Override
    public void run() {
        try {
            Tree tree = new Tree();

            BufferedReader reader = new BufferedReader(new FileReader ("in.txt"));
            PrintWriter writer = new PrintWriter("out.txt");

            String str;
            while((str = reader.readLine())!=null){
                tree.add(Integer.parseInt(str));
            }

            ArrayList<Node> arr = new ArrayList<>();
            tree.NeededVertex(root, arr);
            tree.PreLeftOrder(writer);

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Node {
        int data;
        Node left;
        Node right;
        int depth;
        int level;


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
};