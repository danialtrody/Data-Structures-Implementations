/**
 * Created by Danial Trody
 * Custom Doubly Linked List Implementation
 * This class represents a doubly linked list data structure.
 */
public class DLinkedList<T> {

    private Node cursor;
    private Node head;
    private Node tail;
    private int size;

    //Constructs an empty doubly linked list.
    public DLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        cursor = new Node(null);
        head = tail;
        size = 0;
    }

    //Inserts a new element at the current position in the list.
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(value);
        if (isEmpty()) {
            cursor = newNode;
            head = cursor;
            tail = cursor;
        } else if (cursor.getNext() != null) {
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor);
            cursor = newNode;
        } else {
            cursor.setNext(newNode);
            newNode.setPrev(cursor);
            newNode.setNext(null);
            cursor = newNode;
            tail = cursor;

        }
        size++;
    }

    //Removes and returns the element at the current position in the list.
    public T remove() {
        T removedValue = cursor.getValue();
        if (isEmpty()) return null;
        else if (head == tail) {
            cursor = new Node(null);
            clear();
        } else if (cursor.getNext() == null) {
            tail = cursor.getPrev();
            tail.setNext(null);
            cursor = head;
        } else if (cursor.getPrev() == null) {
            head = cursor.getNext();
            head.setPrev(null);
            cursor = head;
        } else {
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getNext();
        }
        size--;
        return removedValue;
    }

    //Removes the first occurrence of a specific value in the list.
    public T remove(T value) {
        goToBeginning();
        while (hasNext()) {
            if (cursor.getValue().equals(value)) {
                return remove();
            }
            getNext();
        }
        if (cursor.getValue().equals(value)) {
            return remove();
        }
        return null; // Value not found in the list
    }


    //Clears the list, removing all elements.
    public void clear() {
        head = new Node(null);
        tail = new Node(null);
        cursor = new Node(null);
        head = tail;
        size = 0;

    }

    //Replaces the value at the current position in the list with the given value.
    public void replace(T value) {
        if (isEmpty() || value == null) throw new NullPointerException();
        cursor.value = value;

    }

    //Checks if the list is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    //Moves the cursor to the beginning of the list.
    public boolean goToBeginning() {
        if (isEmpty())
            return false;
        cursor = head;
        return true;
    }

    //Moves the cursor to the end of the list.
    public boolean goToEnd() {
        if (isEmpty())
            return false;
        cursor = tail;
        return true;
    }

    //Gets the next element in the list and moves the cursor forward.
    public T getNext() {
        if (hasNext()) {
            cursor = cursor.getNext();
            return cursor.value;

        }
        return null;
    }

    //Gets the previous element in the list and moves the cursor backward.
    public T getPrev() {
        if (hasPrev()) {
            cursor = cursor.getPrev();
            return cursor.value;
        }
        return null;

    }


    //Gets the element at the current cursor position.
    public T getCursor() {
        if (isEmpty())
            return null;
        return cursor.getValue();
    }

    //Checks if there is a next element in the list.
    public boolean hasNext() {
        return cursor.getNext() != null;
    }


    //Checks if there is a previous element in the list.
    public boolean hasPrev() {
        return cursor.getPrev() != null;
    }

    //return the size of the list
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "DLinkedList { empty }";
        }

        Node tempCursor = head;
        StringBuilder str = new StringBuilder();
        str.append("DLinkedList { ");
        while (tempCursor != null) {
            str.append(tempCursor.value);
            if (tempCursor.getNext() != null) {
                str.append(" --> ");
            }
            tempCursor = tempCursor.getNext();
        }
        str.append(" }");
        return str.toString();
    }


    //Private inner class representing a node in the doubly linked list.
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
