import java.util.*;

/**
 public interface Iterable<T> {
    Iterator<T> iterator();
 }

 public interface Iterator<T> {
     boolean hasNext();
     T next();
 }

 */

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size; // the next item to be added will be at position size

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. */
    public void add(T x) {
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** need to return an iterator */
    public Iterator<T> iterator() {
        /** ArraySetIterator is the real iterator */
        return new ArraySetIterator();
    }

    /**
     Extends : This is used to get attributes of a parent class into child class and may contain already defined methods that can be overridden in the child class.

     Implements : This is used to implement an interface (parent class with functions signatures only but not their definitions) by defining it in the child class.

     A class can only "implement" an interface. A class only "extends" a class. Likewise, an interface can extend another interface.

     A class can only extend one other class. A class can implement several interfaces.
     */

    /** Incompatible types. Found: 'ArraySet<T>.ArraySetIterator', required: 'java.util.Iterator<T>' */

    /** This is the case: A class can only "implement" an interface. */
    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;
        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    /*@Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }*/

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return "{" + String.join(", ", listOfItems) + "}";
    }

    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<>();
        for (Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // optimization
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item: this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Set<Integer> javaset = new HashSet<>();
        // javaset.add(5);
        // javaset.add(23);
        // javaset.add(42);

        // Iterator<Integer> seer = javaset.iterator();

        // while (seer.hasNext()) {
            /** next has twofold purposes 1/ to advance 2/ to return current value */
        //    int i = seer.next();
        //    System.out.println(i);
        //}

        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        System.out.println(aset);

        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));

        ArraySet<String> asetOfStrings = ArraySet.of("hi", "I'm", "here");
        System.out.println(asetOfStrings);

        Iterator<Integer> aseer = aset.iterator();

        while (aseer.hasNext()) {
            int i = aseer.next();
            System.out.println(i);
        }

        for (int i : aset) {
            System.out.println(i);
        }
    }
}