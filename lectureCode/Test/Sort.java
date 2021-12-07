public class Sort {
	/** Sorts strings destructively. */
	public static void sort(String[] x) {
		// Find the smallest item
		// Move it to the front
		// Selection sort the rest (using recursion??)
		int smallest = findSmallest(x, 0);
		swap(x, 0, smallest);
		// recursive call?
		sort(x, 0);
	}

	/** Sorts x starting at position start. */
	private static void sort(String[] x, int start) {
		if (start  == x.length) {
			return;
		}
		int smallestIndex = findSmallest(x, start);
		swap(x, start, smallestIndex);
		sort(x, start + 1);
	}

	public static void swap(String[] x, int a, int b){
		String temp = x[a];
		x[a] = x[b];
		x[b] = temp;
	}

	public static int findSmallest(String[] x, int start) {
		int smallestIndex = start;
		for (int i = start; i < x.length; i++) {
			int cmp = x[i].compareTo(x[smallestIndex]);
			if (cmp < 0) {
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}
}