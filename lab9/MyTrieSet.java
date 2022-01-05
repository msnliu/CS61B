import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// HashTable alternative of implementing Trie
public class MyTrieSet implements TrieSet61B {

    private Node root;

    // initialize trie with a root with char null
    public MyTrieSet() {
        // do not need to store key in each Node, as the HashMap already stores character key!
        root = new Node(false);
    }

    private static class Node {
        private boolean isKey;
        private HashMap<Character, Node> table;
        private Node(boolean j) {
            isKey = j;
            table = new HashMap<>();
        }
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new Node(false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        Node t = root;
        for (int i = 0; i < key.length(); i++) {
            char temp = key.charAt(i);
            if (t.table.containsKey(temp)) {
                t = t.table.get(temp);
            } else {
                return false;
            }
        }
        return t.isKey == true;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        Node t = root;
        for (int i = 0; i < key.length(); i++) {
            char temp = key.charAt(i);
            if (!t.table.containsKey(temp)) {
                t.table.put(temp, new Node(false));
            }
            t = t.table.get(temp);
        }
        t.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        // find the node corresponding to prefix
        Node t = root;
        for (int i = 0; i < prefix.length(); i++) {
            char temp = prefix.charAt(i);
            if (!t.table.containsKey(temp)) {
                return null;
            } else {
                t = t.table.get(temp);
            }
        }
        // create an empty list
        List<String> result = new LinkedList<>();
        // for character in keys
        for (char i : t.table.keySet()) {
            colHelp(prefix + i, result, t.table.get(i));
        }
        return result;
    }

    public void colHelp(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (char i : n.table.keySet()) {
            colHelp(s + i, x, n.table.get(i));
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
