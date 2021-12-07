public static int[] flatten(int[][] x) {
	int totalLength = 0;
	for (int[] arr:x) {
		totalLength += arr.length;
	}
	int[] a = new int[totalLength];
	int aIndex = 0;
	for (int[] arr:x) {
		for (int elem:arr) {
			a[aIndex] = elem;
			aIndex += 1;
		}
	}
	return a;
}