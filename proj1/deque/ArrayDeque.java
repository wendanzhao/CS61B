package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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
        int first = moveToBack(nextFirst);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[first];
            first = moveToBack(first);
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
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size = size + 1;
        nextFirst = moveToFront(nextFirst);
    }

    /** Adds an item of type T to the back of the deque. You can assume that item is never null. */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size = size + 1;
        nextLast = moveToBack(nextLast);
    }

    /** Returns the number of items in the deque.*/
   @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        int first = moveToBack(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[first] + " ");
            first = moveToBack(first);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
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
    @Override
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
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        index = (moveToBack(nextFirst) + index) % items.length;
        return items[index];
    }

    /** The Deque objects we'll make are iterable(i.e. Iterable<T>. */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public  ArrayDequeIterator() {
            wizPos = moveToBack(nextFirst);
        }
        @Override
        public boolean hasNext() {
            return wizPos != nextLast;
        }
        public T next() {
            T returnItem = items[wizPos];
            wizPos = moveToBack(wizPos);
            return returnItem;
        }
    }

    /** Returns whether or not the parameter o is equal to the Deque.
     * o is considered equal if it is a Deque and if it contains the same
     * contents(as governed by the generic T's equals method)in the same order.
     * You'll need to use the instance of keywords for this.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque otherDeque) {
            // check Deque are of the same size
            if (this.size != otherDeque.size()) {
                return false;
            }
            //check all items in Deque are the same and have the same order
            for (int i = 0; i < size; i++) {
                if (!this.get(i).equals(otherDeque.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
