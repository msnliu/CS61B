public static boolean mystery(int[] array) {
	array = mrpoolsort(array);
	int N = array.length;
	int curr = array[0];
	boolean unique = true;

	for (int i = 1; i < N; i+=1) {
		if (curr == array[i]) {
			/** encounter duplicate */
			/** not unique */
			unique == false;
		} else if (unique) {
			/** after ecnounter a different number, check its next */
			/** not equal and unique true */
			return false;
		} else {
			/** first time encounter a different number in the array 
			and set curr to that number */
			/** not equal and unique false */
			unique = true;
			curr = array[i];
		}
	}
	return true;
}

/** DOES NOT consider 1112 */