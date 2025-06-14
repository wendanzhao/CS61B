package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesLessThanTen() {
        IntList lst = IntList.of(1, 2, 3, 4, 5);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 9 -> 4 -> 25", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesLargerThanTen() {
        IntList lst = IntList.of(11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("121 -> 12 -> 169 -> 14 -> 15 -> 16 -> 289 -> 18 -> 361 -> 20", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesRandom() {
        IntList lst = IntList.of(2, 3, 13, 5, 17, 7, 19);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 169 -> 25 -> 289 -> 49 -> 361", lst.toString());
        assertTrue(changed);
    }
}

