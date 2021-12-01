public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node<T> {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T i, Node next) {
            this.prev = prev;
            this.item = i;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        /** when defining sentinel, the program evaluates from right to left, the prev and next is sentinel itself.
            (which is not defined)
            therefore, it alludes to set them to null first and redefine in the following lines */
        sentinel = new Node<>(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public T getRecursive(int index) {
        if (size < index) {
            return null;
        }
        return (T) this.getRecursivehelper(sentinel.next, index);
    }

    public T getRecursivehelper(Node first, int index) {
        if (index == 0) {
            return (T) first.item;
        } else {
            first = first.next;
            index -= 1;
            return getRecursivehelper(first, index);
        }
    }

    public void addFirst(T item) {
        size += 1;
        /** update 4 arrows in total
            update 2 arrows from new node out */
        Node x = new Node(sentinel, item, sentinel.next);
        /** update 2 arrows into new node */
        sentinel.next.prev = x;
        sentinel.next = x;
    }

    public void addLast(T item) {
        size += 1;
        Node x = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = x;
        sentinel.prev = x;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T first = (T) sentinel.next.item;
        /** reconstruct two arrows */
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T last = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return last;
    }

    public T get(int index) {
        if (size < index) {
            return null;
        }
        int i = 0;
        /** sentinel.next is the real first node */
        Node temp = sentinel.next;
        while (i < index) {
            temp = temp.next;
            i += 1;
        }
        return (T) temp.item;
    }
}
