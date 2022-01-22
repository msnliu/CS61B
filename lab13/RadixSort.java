import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);

        // find the number of iteration
        int numCharsInAString = 0;
        for (String i : asciis) {
            numCharsInAString = numCharsInAString > i.length() ? numCharsInAString : i.length();
        }

        // padding the string on the right
        sorted = padding(sorted, numCharsInAString);

        // do numCharsInAString times of counting sort
        for (int d = 0; d < numCharsInAString; d++) {
            sorted = sortHelperLSD(sorted, d);
        }

        return sorted;
    }

    private static String[] padding(String[] sorted, int numCharsInAString) {
        String[] pad = new String[sorted.length];
        for (int i = 0; i < sorted.length; i++) {
            pad[i] = rightPadding(sorted[i], numCharsInAString, '_');
        }
        return pad;
    }

    private static String rightPadding(String word, int length, char ch) {
        return (length > word.length() ? rightPadding(word + ch, length, ch) : word);
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort

        // create a count array
        int[] counts = new int[256];
        for (String i : asciis) {
            int pos = (int) i.charAt(i.length() - index - 1);
            // Tip: Remember ASCII codes start from 0, not 1.
            counts[pos] += 1;
        }

        // create a start array
        int[] start = new int[256];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            start[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            int ind = (int) asciis[i].charAt(asciis[i].length() - index - 1);
            String item = asciis[i];
            int place = start[ind];
            sorted[place] = item;
            start[ind] += 1;
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] test = {"bcd", "cea", "abc", "kij", "lop", "x", "a"};
        String[] result = RadixSort.sort(test);

        System.out.println(Arrays.toString(result));
    }
}
