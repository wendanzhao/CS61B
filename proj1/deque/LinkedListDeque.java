package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node p, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Adds an item of type T to the front of the deque. You can assume that item is never null. */
    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    /** Adds an item of type T to the back of the deque. You can assume that item is never null. */
    @Override
    public void addLast(T item) {
        size += 1;
        Node toLast = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = toLast;
        sentinel.prev = toLast;
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
        Node p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item and so forth.
     * if no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index) {
        Node p = sentinel.next;
        if (index >= size) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (index == i) {
                return p.item;
            }
            p = p.next;
        }
        return null;
    }

    /** Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        return getHelper(0, index, sentinel.next);
    }

    private T getHelper(int i, int index, Node p) {
        if (p == sentinel) {
            return null;
        } else if (i == index) {
            return p.item;
        }
        return getHelper(i + 1, index, p.next);
    }

    /** The Deque objects we'll make are iterable(i.e. Iterable<T>. */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizPos;
        public  LinkedListDequeIterator() {
            wizPos = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return wizPos != sentinel;
        }
        public T next() {
            T returnItem = wizPos.item;
            wizPos = wizPos.next;
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
        if (o instanceof LinkedListDeque otherDeque) {
            if (this.size != otherDeque.size()) {
                return false;
            }
            Node temp1 = this.sentinel.next;
            Node temp2 = otherDeque.sentinel.next;
            while (temp1 != sentinel) {
                if (!temp1.item.equals(temp2.item)) {
                    return false;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return true;
        }
        return false;
    }
}
