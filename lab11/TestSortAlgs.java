import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = buildString();
        Queue<String> Sorted = QuickSort.quickSort(tas);
        assertTrue(isSorted(Sorted));
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = buildString();
        Queue<String> Sorted = MergeSort.mergeSort(tas);
        assertTrue(isSorted(Sorted));
    }

    public Queue<String> buildString() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        return tas;
    }
    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
