package deque;

import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;
public class MaxArrayDequeTest {
    private class Dog {
        private String name;
        private int size;

        public Dog(String n, int s) {
            name = n;
            size = s;
        }
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    private static class SizeComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.size - b.size;
        }
    }
    public Comparator<Dog> getSizeComparator() {
        return new SizeComparator();
    }

    public Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }

    @Test
    public void maxTest() {
        MaxArrayDeque<Dog>  ad = new MaxArrayDeque<>(getNameComparator());
        Dog max = ad.max();
        assertEquals(null, max);
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        ad.addLast(d1);
        ad.addLast(d2);
        ad.addLast(d3);
        max = ad.max();
        System.out.println(max.name);
        assertEquals(d2.name, max.name);
        max = ad.max(getSizeComparator());
        System.out.println(max.size);
        assertEquals(d3, max);
    }
}
