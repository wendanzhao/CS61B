package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Optional;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
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
    public void timeLinkedListDequeConstruction() {
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCounts = new LinkedListDeque<>();
        int N = 1000;
        int maxNumber = 128000;
        while (N <= maxNumber) {
            LinkedListDeque<Integer> temp = new LinkedListDeque<>();
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
    public void randomizedTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                lld.addLast(randVal);
                ad.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = lld.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if (lld.size() > 0) {
                    assertEquals(lld.get(lld.size() - 1), ad.get(ad.size() - 1));
                    assertEquals(lld.removeLast(), ad.removeLast());
                }
            }
        }
    }

    @Test
    public void iteratorTest() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addLast("I");
        lld.addLast("have");
        lld.addLast("a");
        lld.addLast("nice");
        lld.addLast("day");
        for (String x : lld ) {
            System.out.println(x);
        }
    }

    @Test
    /** Test two Deque that have the same contents and the same order. */
    public void equalTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(5);
        lld1.addLast(23);
        lld1.addLast(42);

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addLast(5);
        lld2.addLast(23);
        lld2.addLast(42);

        boolean result = lld1.equals(lld2);
        assertTrue("lld1 should be equal to lld2", result);
    }

    @Test
    /** Test two Deques that have the same contents but different order. */
    public void notEqualTest1() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(5);
        lld1.addLast(23);
        lld1.addLast(42);

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addLast(23);
        lld2.addLast(42);
        lld2.addLast(5);

        assertNotEquals(lld1, lld2);
    }

    @Test
    public void notEqualsTest2() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addLast(5);
        lld1.addLast(23);
        lld1.addLast(42);

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addLast(5);
        lld2.addLast(23);

        assertNotEquals(lld1, lld2);
    }

    @Test
    public void getTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(5);
        lld.addLast(23);
        lld.addLast(42);
        lld.addLast(62);
        assertEquals(5, (double)lld.get(0), 0.0);
        assertEquals(23, (double )lld.get(1), 0.0);
        assertEquals(42, (double)lld.get(2), 0.0);
        assertEquals(62, (double)lld.get(3), 0.0);
        assertEquals(null, lld.get(4));
    }
}
