import java.util.*;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private static final int capacity = 16;
    private static final double loadFactor = 0.75;
    // Array to store buckets
    private ArrayList<Node<K, V>> buckets;
    // number of key-value pairs
    private int size = 0;
    // number of buckets
    private int num;
    // coefficient
    private double load;
    // for keySet and Iterators
    private Set<K> keys;

    public MyHashMap() {
        this(capacity, loadFactor);
    }

    public int getBucket(K key) {
        // why floorMod? overflow to negative + -1 % 4 = -1
        return Math.floorMod(key.hashCode(), num);
    }

    public class Node<K, V> {
        private K key;
        private V val;
        private Node next;

        public Node(K k, V v) {
            key = k;
            val = v;
            next = null;
        }
    }

    public MyHashMap(int initialSize) {
        this(initialSize, loadFactor);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        num = initialSize;
        load = loadFactor;
        buckets = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            buckets.add(null);
        }
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < num; i++) {
            buckets.set(i, null);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int i = getBucket(key);
        Node<K, V> head = buckets.get(i);
        while (head != null) {
            // if Key is of type String, String is reference type, == compares the address
            if (key.equals(head.key)) {
                return (V) head.val;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        ArrayList<Node<K, V>> temp = buckets;
        buckets = new ArrayList<>();
        num = num * 2;
        size = 0;
        for (int i = 0; i < num; i++) {
            buckets.add(null);
        }
        for (Node<K, V> n : temp) {
            while (n != null) {
                put(n.key, n.val);
                n = n.next;
            }
        }
    }

    @Override
    public void put(K key, V value) {
        int i = getBucket(key);
        Node<K, V> head = buckets.get(i);
        Node<K, V> temp = head;
        // key already present update value
        while (head != null) {
            if (key.equals(head.key)) {
                head.val = value;
                return;
            }
            head = head.next;
        }
        // key not present
        // Hard: add front
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = temp;
        buckets.set(i, newNode);

        size += 1;
        if ((1.0 * size) / num > load) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        keys = new HashSet<>();
        for (Node<K, V> n : buckets) {
            while (n != null) {
                keys.add(n.key);
                n = n.next;
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        int i = getBucket(key);
        Node<K, V> head = buckets.get(i);
        Node<K, V> prev = null;
        while (head != null) {
            if (key.equals(head.key)) {
                break;
            }
            prev = head;
            head = head.next;
        }
        // if key was not there
        if (head == null) {
            return null;
        }
        size -= 1;
        if (prev != null) {
            // matching after the first Node
            prev.next = head.next;
        } else {
            // matching on the first Node
            buckets.set(i, head.next);
        }
        return head.val;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {

        private List<K> list;
        private int index;
        private K curr;

        public HashMapIterator() {
            keys = keySet();
            list = new ArrayList<>(keys);
            index = 0;
        }

        public boolean hasNext() {
            return index < list.size();
        }

        public K next() {
            curr = list.get(index);
            index += 1;
            return curr;
        }
    }

    // test iterator
    public static void main(String[] args) {
        MyHashMap<String, Integer> test = new MyHashMap<>();
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);
        Iterator myiter = test.iterator();
        for (String temp : test) {
            System.out.println(temp);
        }
    }
}
