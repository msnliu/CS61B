package bearmaps;
import org.junit.Test;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private List<Node<T>> pq;
    // https://stackoverflow.com/questions/33716948/how-to-store-value-into-arraylist-with-some-specific-index-in-java
    // you use array List and HaspMap
    // contains operation alludes to use a HashMap
    // store the index as a value corresponding to the key T item
    private Map<T, Integer> index;

    public ArrayHeapMinPQ() {
        pq = new ArrayList<>();
        pq.add(null);
        index = new HashMap<>();
    }

    private class Node<T> {
        private T item;
        private double priority;

        private Node(T i, double p) {
            item = i;
            priority = p;
        }
    }

    private int parent(int k) {
        if (k == 1) {
            return 1;
        }
        return k / 2;
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        pq.add(new Node(item, priority));
        index.put(item, size());
        swim(size());
    }

    public void swim(int k) {
        if (k == 1) {
            // root
            return;
        }
        if (pq.get(k).priority < pq.get(parent(k)).priority) {
            swap(k, parent(k));
        }
        swim(parent(k));
    }

    public void swap(int k, int p) {
        Node<T> up = pq.get(k);
        Node<T> down = pq.get(p);
        // update pq
        pq.set(p, up);
        pq.set(k, down);
        // update index
        index.put(up.item, p);
        index.put(down.item, k);
    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        // leave the hashing of item to HashMap
        // theta(1) runtime if evenly distributed
        return index.containsKey(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return pq.get(1).item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T minimum = getSmallest();
        pq.set(1, pq.get(size()));
        index.put(pq.get(1).item, 1);
        pq.remove(size());
        index.remove(minimum);
        heapify(1);
        return minimum;
    }

    private int left(int k) {
        return 2 * k;
    }

    private int right(int k) {
        return 2 * k + 1;
    }

    public void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int parent = i;
        if (left <= size() && pq.get(left).priority < pq.get(parent).priority) {
            parent = left;
        }
        if (right <= size() && pq.get(right).priority < pq.get(parent).priority) {
            parent = right;
        }
        if (parent != i) {
            swap(parent, i);
            heapify(parent);
        }
    }

    /* Returns the number of items in the PQ. */

    @Override
    public int size() {
        return pq.size() - 1;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int ind = index.get(item);
        pq.get(ind).priority = priority;
        // bug here: missing specify swim up or down!!!
        if (priority < pq.get(parent(ind)).priority) {
            swim(ind);
        } else {
            heapify(ind);
        }
    }
}
