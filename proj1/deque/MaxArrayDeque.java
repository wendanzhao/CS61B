package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /** creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty,simply returns null.
     *
     */
    public T max() {
        return max(comparator);
    }

    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply returns null.
     */
    public T max(Comparator<T> c) {
        T maxItem = get(0);
        for (int i = 0; i < super.size(); i++) {
            T item = get(i);
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
