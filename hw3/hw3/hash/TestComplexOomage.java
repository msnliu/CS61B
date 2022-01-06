package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        for (Oomage i : oomages) {
            System.out.println(i.hashCode());
            // System.out.println(256 * 256 * 256 * 256);
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */

    // https://stackoverflow.com/questions/32042346/why-does-this-multiplication-integer-overflow-result-in-zero
    // Since multiplied by 256, which is an even number, Note even number * even OR odd still results in even,
    // by the stackoverflow post above, "This will only happen if the starting value of x is even." (the case where overflow results in 0)
    // Solution? As given in lecture, change 256 to 31. Prime number works just fine, and smaller number results in less computation.

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        // Your code here.
        for (int i = 0; i < 10; i++) {
            List<Integer> lst = new LinkedList<>();
            lst.add(i);
            // add(i) is important here as it distinguishes different hashcode below
            for (int j = 0; j < 4; j++) {
                lst.add(1);
            }
            System.out.print(lst);
            ComplexOomage item = new ComplexOomage(lst);
            deadlyList.add(item);
        }
        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
