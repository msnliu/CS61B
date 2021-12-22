public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> list = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            list.add(word.charAt(i));
        }
        return list;
    }

    private boolean isPalindrome(Deque<Character> wordInDeque) {
        while (wordInDeque.size() > 1) {
            /** return a && b means "return a if a is false, return b if a is true".
             * if (a) return b;
             * else return a; */
            return wordInDeque.removeFirst() == wordInDeque.removeLast() && isPalindrome(wordInDeque);
        }
        /** size is 0 or 1 (input size is 0 or 1 OR in the final round even size -> 0, odd size -> 1) */
        return true;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        return isPalindrome(wordToDeque(word));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (cc == null) {
            return isPalindrome(word);
        }
        if (word == null) {
            return false;
        }
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> wordInDeque, CharacterComparator cc) {
        while (wordInDeque.size() > 1) {
            return cc.equalChars(wordInDeque.removeFirst(), wordInDeque.removeLast()) && isPalindrome(wordInDeque, cc);
        }
        return true;
    }
}
