public static int[] union(int[] A, int[] B) {
	HashSet<Integer> set = new HashSet<>();
	for (int num : A) {
		set.add(num);
	}
	for (int num : B) {
		set.add(num);
	}
	int[] unionArray = new int[set.size()];
	int i = 0;
	for (int num : set) {
		unionArray[i] = num;
		i ++;
	}
	return unionArray;
}