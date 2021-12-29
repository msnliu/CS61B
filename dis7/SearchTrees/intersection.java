public static int[] intersection(int[] A, int[] B) {
	HashSet<Integer> setofA = new HashSet<>();
	HashSet<Integer> intersectionSet = new HashSet<>();
	for (int num : A) {
		setofA.add(num);
	}
	for (int num : B) {
		if (setofA.contains(num)) {
			intersectionSet.add(num);
		}
	}
	int[] intersectionArray = new int[intersectionSet.size()];
	int i = 0;
	for (int num : intersectionSet) {
		intersectionArray[i] = num;
		i ++;
	}
	return intersectionArray;
}