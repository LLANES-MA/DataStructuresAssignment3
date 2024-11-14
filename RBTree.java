public class RBTree <Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key ID;
        private Value Name, Category, Price;
        private Node left, right;
        boolean color;

        public Node(Key ID, Value Name, Value Category, Value Price, boolean color) {
            this.ID = ID;
            this.Name = Name;
            this.Category = Category;
            this.Price = Price;
            this.color = color;
            this.left = null;
            this.right = null;
        }
    }

    public RBTree() {
        root = null;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColor(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.ID);
            if (cmp < 0) {x = x.left;}
            if (cmp > 0) {x = x.right;}
            else {return x.Name;}
        }
        return null;
    }

    public void query(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.ID);
            if (cmp < 0) {x = x.left;}
            if (cmp > 0) {x = x.right;}
            else {
                System.out.println(x.ID + ", " + x.Name + ", " + x.Category + ", " + x.Price);
                System.out.println();
                x = null;
            }
        }
    }

    public void put(Key ID, Value Name, Value Category, Value Price) {
        root = put(root, ID, Name, Category, Price);
    }

    private Node put(Node h, Key ID, Value Name, Value Category, Value Price) {
        // if there is not a node at this location yet, make it the node we're inserting
        if (h == null) return new Node(ID, Name, Category, Price, RED);
        // since there's already a node, compare our id to it
        int cmp = ID.compareTo(h.ID);
        // if we have a smaller id, put our node in the left spot
        if (cmp < 0) {h.left = put(h.left, ID, Name, Category, Price); }
        // if our ID is greater, put our node in the right spot
        else if (cmp > 0) {h.right = put(h.right, ID, Name, Category, Price); }
        // this should never happen because of our throw, but we'll make it the same code as
        // the throw with a slight change
        else {System.out.println("Key " + ID + " already exists\n");}

        // lean left
        if (isRed(h.right) && !isRed(h.left)) {h = rotateLeft(h);}
        // balance 4 node
        if (isRed(h.left) && isRed(h.left.left)) {h = rotateRight(h);}
        // split node
        if (isRed(h.left) && isRed(h.right)) {flipColor(h);}

        return h;
    }
}
