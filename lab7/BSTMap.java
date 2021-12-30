import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private int size;
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        Set<K> view = new HashSet<>();
        InOrder(root, view);
        return view;
    }

    public void InOrder(Node x, Set<K> keys) {
        if (x == null) {
            return;
        }
        InOrder(x.left, keys);
        keys.add(x.key);
        InOrder(x.right, keys);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V delete = get(key);
        root = remove(root, key);
        return delete;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        if (!get(key).equals(value)) {
            return null;
        }
        root = remove(root, key);
        return value;
    }

    public Node remove(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            /** left and right both NOT null */
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return new SetIterator();
    }

    public void InOrder(Node x, List<K> keys) {
        if (x == null) {
            return;
        }
        InOrder(x.left, keys);
        keys.add(x.key);
        InOrder(x.right, keys);
    }

    public class SetIterator implements Iterator<K> {
        private List<K> keys;
        private int Pos;

        /** constructor */
        public SetIterator() {
            keys = new LinkedList<>();
            InOrder(root, keys);
            Pos = 0;
        }

        @Override
        public boolean hasNext() {
            return Pos < keys.size();
        }

        @Override
        public K next() {
            K currentKey = keys.get(Pos);
            Pos += 1;
            return currentKey;
        }
    }

    public void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        printInOrder(x.left);
        System.out.println(x.key + " " + x.val);
        printInOrder(x.right);
    }

    public void printInOrder() {
        printInOrder(root);
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> test = new BSTMap<>();
        test.put("d", 4);
        test.put("b", 2);
        test.put("f", 5);
        test.put("a", 1);
        test.put("c", 3);
        test.printInOrder();
        System.out.println(test.keySet());
        Iterator<String> iter = test.iterator();
        /** first way to see iterator */
        while (iter.hasNext()) {
            String i = iter.next();
            System.out.print(i);
        }
        System.out.println("");
        /** second way to see iterator */
        for (String i : test) {
            System.out.print(i);
        }
    }
}


