/**
 * Created by Danial Trody
 * A generic stack data structure implemented using a linked list.
 * supporting push, pop, peek, and isEmpty operations
 */
public class Stack<T> {

    private Node top;
    private Node bottom;
    private int length;

    // Constructor to initialize an empty stack
    public Stack() {
        top = null;
        bottom = null;
        length = 0;
    }

    // Method to peek at the top element of the stack without removing it
    public T peek() {
        if (isEmpty()) return null;
        return top.value;
    }

    // Method to push a new element onto the top of the stack
    public void push(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }
        Node newNode = new Node(value);
        if (isEmpty()) {
            top = newNode;
            bottom = newNode;
        } else {
            top.setNext(newNode);
            newNode.setPrev(top);
            top = newNode;
        }
        length++;
    }

    // Method to pop and remove the top element from the stack
    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty Stack");
        }
        T itemToRemove = top.getValue();
        top = top.getPrev();
        if (top == null) {
            bottom = null;
        } else {
            top.setNext(null);
        }
        length--;
        return itemToRemove;
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return length == 0;
    }

    // Method to clear all elements from the stack
    public void clear() {
        top = null;
        bottom = null;
        length = 0;
    }

    // Method to get the number of elements in the stack
    public int size() {
        return length;
    }




    // Method to provide a string representation of the stack
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Stack:\n");

        Node current = bottom;  // Start from the bottom of the stack
        while (current != null) {
            str.append(current.getValue()).append("\n");
            current = current.getNext();
        }

        return str.toString();
    }


    // Node class to represent individual elements in the stack
    private class Node {

        private T value;
        private Node next;
        private Node prev;


        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrev() {
            return this.prev;
        }

        public T getValue() {
            return value;
        }

    }
}









