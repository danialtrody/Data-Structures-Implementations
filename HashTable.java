/**
 * Created by Danial Trody
 * Custom hash table implementation supporting key-value pairs with dynamic resizing.
 * This class provides an efficient key-value storage and retrieval mechanism using a hash table.
 * It dynamically resizes the underlying data structure to maintain performance.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HashTable<Key, Value> {

    // Constants for initial capacity and load factor threshold
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private List<LinkedList<MapEntry<Key, Value>>> buckets;
    private int size;

    // Constructor to create a hash table with the default initial capacity
    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    // Constructor to create a hash table with a specified initial capacity
    public HashTable(int initialCapacity) {
        // Initialize the buckets with empty linked lists
        this.buckets = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    // Hash function to determine the bucket index for a given key
    private int hashFunction(Key key) {
        return Math.abs(key.hashCode()) % buckets.size();
    }

    // Method to put a key-value pair into the hash table
    public void put(Key key, Value value) {
        int index = hashFunction(key);
        LinkedList<MapEntry<Key, Value>> bucket = this.buckets.get(index);

        // Check if the key already exists in the bucket and update its value
        for (MapEntry<Key, Value> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        // If the key is not found, add a new key-value pair to the bucket
        bucket.add(new MapEntry<>(key, value));
        size++;

        // Check if resizing is needed based on the load factor threshold
        if ((double) size / buckets.size() >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }


    }

    // Method to get the value associated with a given key
    public Value get(Key key) {
        int index = hashFunction(key);
        LinkedList<MapEntry<Key, Value>> bucket = this.buckets.get(index);

        // Search for the key in the bucket and return its value
        for (MapEntry<Key, Value> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        // Key not found, throw an exception
        throw new NoSuchElementException("Key not found");
    }

    // Method to check if a key exists in the hash table
    public boolean containsKey(Key key) {
        int index = hashFunction(key);
        LinkedList<MapEntry<Key, Value>> bucket = this.buckets.get(index);
        for (MapEntry<Key, Value> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;

    }

    // Method to remove a key-value pair from the hash table
    public void remove(Key key) {
        int index = hashFunction(key);
        LinkedList<MapEntry<Key, Value>> bucket = this.buckets.get(index);
        for (MapEntry<Key, Value> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
        throw new NoSuchElementException("Key not found");
    }

    public int size() {
        return this.size;
    }


    private void resize() {
        int newCapacity = buckets.size() * 2;
        List<LinkedList<MapEntry<Key, Value>>> newBuckets = new ArrayList<>(newCapacity);

        // Initialize the new buckets with empty linked lists
        for (int i = 0; i < newCapacity; i++) {
            newBuckets.add(new LinkedList<>());
        }

        // Rehash the key-value pairs into the new buckets
        for (LinkedList<MapEntry<Key, Value>> bucket : buckets) {
            for (MapEntry<Key, Value> entry : bucket) {
                int newIndex = Math.abs(entry.getKey().hashCode()) % newCapacity;
                newBuckets.get(newIndex).add(entry);
            }
        }

        this.buckets = newBuckets;
    }

    public List<Key> keys() {
        List<Key> keys = new ArrayList<>();
        for (LinkedList<MapEntry<Key, Value>> bucket : buckets) {
            for (MapEntry<Key, Value> entry : bucket) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }


    // Inner class to represent key-value pairs
    private static class MapEntry<Key, Value> implements Map.Entry<Key, Value> {

        private Key key;
        private Value value;

        public MapEntry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        @Override
        public Value setValue(Value value) {
            Value oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

}

