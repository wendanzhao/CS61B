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

    /** move index toward the back of the Deque by 1 and ensure it within bound. */
    private int moveToBack(int index) {
        return (index + 1) % items.length;
    }

    /** move index toward the front of the Deque by 1 and ensure it within bound. */
    private int moveToFront(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** Adds an item of type T to the front of the deque. You can assume that item is never null. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size = size + 1;
        nextFirst = moveToFront(nextFirst);
    }

    /** Adds an item of type T to the back of the deque. You can assume that item is never null. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size = size + 1;
        nextLast = moveToBack(nextLast);
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
        int first = moveToBack(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[first] + " ");
            first = moveToBack(first);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (size - 1) < items.length / 4) {
            resize((size - 1) * 4);
        }
        int first = moveToBack(nextFirst);
        T returnItem = items[first];
        items[first] = null;
        nextFirst = first;
        size = size - 1;
        return returnItem;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (size - 1) < items.length / 4) {
            resize((size - 1) * 4);
        }
        int last = moveToFront(nextLast);
        T returnItem = items[last];
        nextLast = last;
        items[last] = null;
        size = size - 1;
        return returnItem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exits, returns null. Must not alter the deque!
     */

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        index = (moveToBack(nextFirst) + index) % items.length;
        return items[index];
    }

}
