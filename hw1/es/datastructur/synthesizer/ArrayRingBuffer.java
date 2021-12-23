package es.datastructur.synthesizer;
import java.util.ArrayList;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int capacity() {
        return rb.length;
    }    // return size of the buffer

    @Override
    public int fillCount() {
        return fillCount;
    }    // return number of items currently in the buffer

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */

    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last = (last + 1) % capacity();
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T item = rb[first];
        fillCount -= 1;
        first = (first + 1) % capacity();
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    @Override
    public Iterator<T> iterator() {
        return new setiterator();
    }

    private class setiterator implements Iterator<T> {

        private int index;
        private int num;

        public setiterator() {
            index = first;
            num = 0;
        }

        @Override
        public boolean hasNext() {
            return num < fillCount;
        }

        @Override
        public T next() {
            T item = rb[index];
            index = (index + 1) % capacity();
            num += 1;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {
        /** optimize */
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        Iterator<T> thisiterator = this.iterator();
        Iterator<T> otheriterator = other.iterator();
        while (thisiterator.hasNext() && otheriterator.hasNext()) {
            if (thisiterator.next() != otheriterator.next()) {
                return false;
            }
        }
        return true;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
