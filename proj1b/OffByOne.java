import edu.princeton.cs.algs4.ST;

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == 1 || y - x == 1) {
            return true;
        }
        return false;
    }
}
