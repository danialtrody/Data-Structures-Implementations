/**
 * Created by Danial Trody
 * This class supports enqueueing  implemented using a linked list
 * supporting dequeue, peeking at the front element, checking if the queue is empty
 */
public class Queue<T> {

    private Node first;
    private Node last;
    private int length;

    //Initializes an empty queue.
    public Queue() {
        first = null;
        last = null;
        length = 0;
    }


    /**
     * Peeks at the front element of the queue without removing it.
     *
     * @return The front element or null if the queue is empty.
     */
    public T peek() {
        if (isEmpty()) return null;
        return first.value;
    }

    /**
     * Enqueues an element at the back of the queue.
     *
     * @param value The value to enqueue.
     * @throws IllegalArgumentException if the provided value is null.
     */
    public void enqueue(T value) {
        if (value == null) throw new IllegalArgumentException();
        Node newNode = new Node(value);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        length++;
    }


    /**
     * Dequeues and removes the front element from the queue.
     *
     * @return The front element or null if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) return null;
        T itemToReturn = first.value;
        if (first == last) {
            last = null;
        }
        first = first.getNext();
        length--;
        return itemToReturn;
    }


    //Checks if the queue is empty.
    public boolean isEmpty() {
        return length == 0;
    }


    //Provides a string representation of the queue.
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty.";
        }

        StringBuilder str = new StringBuilder("Queue: ");
        Node current = first;
        while (current != null) {
            str.append(current.getValue()).append("");
            current = current.getNext();
        }
        return str.toString();
    }


    private class Node {

        private T value;
        private Node next;
        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        public Node getNext() {
            return this.next;
        }
        public T getValue() {
            return value;
        }

    }
}
