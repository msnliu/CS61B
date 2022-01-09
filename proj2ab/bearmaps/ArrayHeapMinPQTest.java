package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    public ArrayHeapMinPQ<String> testConstructor() {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ<>();
        // System.out.println(test.getSmallest());
        // System.out.println(test.removeSmallest());
        test.add("abc", 3.3);
        test.add("5000", 2.0);
        test.add("c", -5.0);
        // test.add("c", -4.0);
        test.add("!", 1000);
        test.add("&&", 0);
        return test;
    }

    @Test
    public void testArrayHeapMinPQ() {
        ArrayHeapMinPQ<String> test = testConstructor();
        assertEquals(test.contains("abc"), true);
        assertEquals(test.contains("5000"), true);
        assertEquals(test.contains("c"), true);
        assertEquals(test.contains("!"), true);
        assertEquals(test.contains("&&"), true);
        assertEquals(test.size(), 5);
        assertEquals(test.getSmallest(), "c");
        assertEquals(test.removeSmallest(), "c");
        assertEquals(test.contains("c"), false);
        assertEquals(test.size(), 4);
        assertEquals(test.getSmallest(), "&&");
        assertEquals(test.removeSmallest(), "&&");
        assertEquals(test.contains("&&"), false);
        assertEquals(test.size(), 3);
        assertEquals(test.getSmallest(), "5000");
        assertEquals(test.removeSmallest(), "5000");
        assertEquals(test.contains("5000"), false);
        assertEquals(test.size(), 2);
        assertEquals(test.getSmallest(), "abc");
        assertEquals(test.removeSmallest(), "abc");
        assertEquals(test.contains("abc"), false);
        assertEquals(test.size(), 1);
        assertEquals(test.getSmallest(), "!");
        assertEquals(test.removeSmallest(), "!");
        assertEquals(test.contains("!"), false);
        assertEquals(test.size(), 0);
        // System.out.println(test.getSmallest());
        // System.out.println(test.removeSmallest());
    }

    @Test
    public void testchangePriority() {
        ArrayHeapMinPQ<String> test = testConstructor();
        test.changePriority("c", 1000.1);
        assertEquals(test.getSmallest(), "&&");
        assertEquals(test.removeSmallest(), "&&");
    }
}
