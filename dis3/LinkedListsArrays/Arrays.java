public static int[] insert(int[] arr, int item, int position) {
	/** because arrays have a fixed size, so to add an element, you need to create a new array. */
	int[] result = new int[arr.length + 1];
	/** position > arr.length, addlast, position < arr.length, add in the list */
	position = Math.min(arr.length, position);
	for (int i = 0; i < position; i++) {
		result[i] = arr[i];
	}
	result[position] = item;
	/** still need to append the terms starting from position from the original array */
	for (int i = position; i < arr.length; i++) {
		result[i + 1] = arr[i];
	}
	return result;
}

public static int[] replicate(int[] arr) {
	int total = 0;
	for (int item: arr) {
		total += item;
	}
	int[] result = new int[total];
	/** missing k in my solution */
	int k = 0;
	for (int i = 0; i < arr.length, i++) {
		for (int j = 0; j < arr[i]; j++) {
			result[k] = arr[i];
			k += 1;
		}
	}
	return result;
}
