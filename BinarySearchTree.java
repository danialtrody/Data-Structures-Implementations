/**
 * Created by Danial Trody
 * Binary Search Tree Implementation
 * This class represents a binary search tree data structure.
 */
import java.util.Objects;
public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    // Constructor: Initializes an empty BinarySearchTree
    public BinarySearchTree() {
        this.root = null;
    }

    // Insert a new value into the BinarySearchTree
    public BinarySearchTree<T> insert(T value) {
        // Ensure the value is not null
        Objects.requireNonNull(value, "Cannot insert null values.");

        Node newNode = new Node(value);

        if (isEmpty()) {
            root = newNode;
        } else {
            Node current = root;
            while (true) {
                int cmp = value.compareTo(current.getValue());
                if (cmp < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(newNode);
                        break;
                    }
                    current = current.getLeft();
                } else {
                    if (current.getRight() == null) {
                        current.setRight(newNode);
                        break;
                    }
                    current = current.getRight();
                }
            }
        }
        return this;
    }

    // Check if the BinarySearchTree contains a given value
    public boolean contains(T value) {
        // Ensure the value is not null
        Objects.requireNonNull(value, "Cannot search for null values.");

        Node current = root;
        while (current != null) {
            int cmp = value.compareTo(current.getValue());
            if (cmp == 0) {
                return true; // Found the value
            } else if (cmp < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }

    // Check if the BinarySearchTree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Remove a value from the BinarySearchTree
    public boolean remove(T value) {
        Objects.requireNonNull(value, "Cannot remove null values.");

        if (isEmpty()) {
            return false;
        }
        root = remove(root, value);
        return true;
    }

    // Recursive helper method for removing a value from the tree
    private Node remove(Node node, T value) {
        if (node == null) {
            return node;
        }

        int cmp = value.compareTo(node.getValue());

        if (cmp < 0) {
            node.setLeft(remove(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(remove(node.getRight(), value));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setValue(findMin(node.getRight()).getValue());
            node.setRight(remove(node.getRight(), node.getValue()));
        }
        return node;
    }

    // Find the minimum value in the BinarySearchTree
    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    // Calculate the size (number of nodes) of the BinarySearchTree
    public int size() {
        return size(root);
    }

    // Recursive helper method for calculating the size
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    // Generate a string representation of the BinarySearchTree
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinarySearchTree [");
        if (!isEmpty()) {
            inOrderTraversal(root, sb);
            sb.delete(sb.length() - 2, sb.length()); // Remove the trailing comma and space
        }
        sb.append("]");
        return sb.toString();
    }

    // Recursive in-order traversal for generating the string representation
    private void inOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), sb);
            sb.append(node.getValue()).append(", ");
            inOrderTraversal(node.getRight(), sb);
        }
    }

    // Inner Node class representing individual nodes in the BinarySearchTree
    private class Node {
        private T value;
        private Node right;
        private Node left;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(T value) {
            this.value=value;
        }
    }
}
