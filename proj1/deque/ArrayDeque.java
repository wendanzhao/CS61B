package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Resizes the underlying array to the target capacity. */
    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int first = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            newArray[i] = items[first];
            first = (first + 1) % items.length;
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newArray;
    }

    /** Adds an item of type T to the front of the deque. You can assume that item is never null. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size = size + 1;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        nextFirst = nextFirst - 1;
    }

    /** Adds an item of type T to the back of the deque. You can assume that item is never null. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size = size + 1;
        nextLast = (nextLast + 1) % items.length;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int first = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[first] + " ");
            first = (first + 1) % items.length;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size = size - 1;
        if (items.length >= 16 && size < items.length / 4) {
            resize(size * 4);
        }
        int first = (nextFirst + 1) % items.length;
        T returnItem = get(first);
        items[first] = null;
        return returnItem;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size = size - 1;
        if (items.length >= 16 && size < items.length / 4) {
            resize(size * 4);
        }
        int last;
        if (nextLast == 0) {
            last = items.length - 1;
        } else {
            last = nextLast - 1;
        }
        T returnItem = get(last);
        items[last] = null;
        return returnItem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exits, returns null. Must not alter the deque!
     */

    public T get(int index) {
        return items[index];
    }

}
