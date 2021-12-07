/** array has underlying order, and we use that to retrieve the deque with true order
 think about the cave in lecture */

public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /** circular
     think about only using addFirst or addLast */
    public int plusOne(int index) {
        return (index + 1) % items.length;
    }

    public int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    public boolean isFull() {
        return(size == items.length);
    }

    public void addFirst(T x) {
        if (isFull()) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Hard */
    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        /** cannot use arraycopy here because we want to keep the order of the existing array.
         from 0 1 (starting point with true order) to 0 1 2 3 4 5 6 7 (true order not underlying order)
         Invariant: the array we started with should always have the true order */
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            a[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addLast(T x) {
        if (isFull()) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public void rMemory() {
        if ((items.length >= 16) && ((size - 1) / items.length < 0.25)) {
            resize(size / 2);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        rMemory();
        T first = get(plusOne(nextFirst));
        items[plusOne(nextFirst)] = null;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rMemory();
        T last = get(minusOne(nextLast));
        items[minusOne(nextLast)] = null;
        size -= 1;
        return last;
    }

    /** Hard */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return (T) items[(plusOne(nextFirst) + index) % items.length];
    }
}
