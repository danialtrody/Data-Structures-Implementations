/**
 * Created by Danial Trody
 * Custom array-like data structure that allows dynamic resizing.
 * This class provides an implementation of a dynamic array
 */
public class MyArray<T> {

    private int size;
    private int initialCapacity;
    private Object[] data;

    //Constructor to initialize the MyArray with the specified initial capacity.
    public MyArray() {
        this.data = new Object[1];
        this.initialCapacity = 1;
        this.size = 0;
    }

    //Appends an item to the end of the array.
    public boolean append(T item) {
        if (item == null) return false;
        if (this.size == this.initialCapacity) {
            ensureCapacity();
        }
        this.data[this.size] = item;
        this.size++;
        return true;
    }

    //Inserts an item at the specified index in the array, shifting elements to the right.
    public boolean insert(T item, int index) {
        if (item == null || index < 0 || index > this.size) return false;
        if (this.size == this.initialCapacity) {
            ensureCapacity();
        }
        shiftItemsRight(index);
        this.data[index] = item;
        this.size++;
        return true;
    }

    //Removes and returns the last item in the array.
    @SuppressWarnings("unchecked")
    public T pop() throws Exception {
        if (isEmpty()) throw new Exception("The array is empty");
        T temp = (T) this.data[this.size - 1];
        this.size--;
        return temp;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        return (T) this.data[index];
    }

    public void set(T item, int index) {
        if (!(index < 0 || index >= this.size || item == null))
            this.data[index] = item;
    }


    //Deletes the item at the specified index, shifting elements to the left.
    @SuppressWarnings("unchecked")
    public boolean delete(int index) {
        if (index < 0 || index >= this.size) return false;
        T item = (T) this.data[index];
        this.shiftItemsLift(index);
        this.size--;
        return true;
    }

    //Checks if the array contains the specified item.
    public boolean contain(T item) {
        for (int i = 0; i < this.size; i++) {
            if (this.get(i) == item)
                return true;
        }
        return false;
    }

    //Finds the index of the specified item in the array.
    public int indexOf(T item) {
        for (int i = 0; i < this.size; i++) {
            if (this.get(i) == item)
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //* Reverses the order of elements in the array.
    public void reverse() throws Exception {
        MyArray<T> tempArr = new MyArray<>();
        for (int i = this.size - 1; i >= 0; i--) {
            tempArr.append(get(i));
        }
        this.data = tempArr.data;
    }

    public void swap(int index1, int index2) {
        T temp = this.get(index1);
        this.set(this.get(index2), index1);
        this.set(temp, index2);

    }

    public boolean equals(MyArray<T> otherArray) {
        for (int i = 0; i < this.size; i++) {
            if (this.get(i) != otherArray.get(i))
                return false;
        }
        return true;
    }

    //0(1)
    public void clear() {
        this.size = 0;
    }

    public int size() {
        return size;
    }


    //Shifts elements to the left by one position starting from the specified index.
    private void shiftItemsLift(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        // Set the last item in the array to null after the shift
        this.data[this.size - 1] = null;
    }

    //Shifts elements to the right by one position starting from the specified index.
    private void shiftItemsRight(int index) {
        for (int i = this.size; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }

    }

    //Ensures the capacity of the internal array by increasing its size when needed.
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        this.initialCapacity *=2;
        Object[] newData = new Object[this.initialCapacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < this.size; i++) {
            str.append(this.get(i));
            if (i != this.size - 1) str.append(",");
        }
        str.append("]");

        return str.toString();
    }

}
