package flik;

import org.junit.Test;
import  static org.junit.Assert.*;
public class FlikTest {
    /** Test whether isSameNumber works. */
    @Test
    public void isSameNumberTest() {
        int a = 128;
        int b = 128;
        boolean result = Flik.isSameNumber(a, b);
        assertTrue(result);
    }
}
