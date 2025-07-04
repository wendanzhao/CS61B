package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("A newly initialized ADeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  ad1 = new ArrayDeque<>() ;
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigADequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 50000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 99999; i > 50000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    private static void printTimingTable(ArrayDeque<Integer> Ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    @Test
    /* check if addLast method takes constant time.*/
    public void timeArrayDequeConstruction() {
        ArrayDeque<Integer> Ns = new ArrayDeque<>();
        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> opCounts = new ArrayDeque<>();
        int N = 1000;
        int maxNumber = 128000;
        while (N <= maxNumber) {
            ArrayDeque<Integer> temp = new ArrayDeque<>();
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < N; i += 1) {
                temp.addLast(i);
            }
            double time = sw.elapsedTime();
            Ns.addLast(N);
            times.addLast(time);
            opCounts.addLast(N);
            N *= 2;
        }
        printTimingTable(Ns, times, opCounts);
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("I");
        ad.addLast("have");
        ad.addLast("a");
        ad.addLast("nice");
        ad.addLast("day");
        for (String x : ad ) {
            System.out.println(x);
        }
    }

    @Test
    /** Test two Deques that have the same contents and the same order. */
    public void equalTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(5);
        ad1.addLast(23);
        ad1.addLast(42);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(5);
        ad2.addLast(23);
        ad2.addLast(42);

        boolean result = ad1.equals(ad2);
        assertTrue("lld1 should be equal to lld2", result);
    }

    @Test
    /** Test two Deque that have the same contents but different order. */
    public void notEqualTest1() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(5);
        ad1.addLast(23);
        ad1.addLast(42);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(23);
        ad2.addLast(42);
        ad2.addLast(5);

        assertNotEquals(ad1, ad2);
    }

    @Test
    public void notEqualsTest2() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(5);
        ad1.addLast(23);
        ad1.addLast(42);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(5);
        ad2.addLast(23);

        assertNotEquals(ad1, ad2);
    }
}

