public static IntList square(IntList L) { /** Non-destructive, Recursive */
	/** base case */
	if (L == null) {
		return L;
	} else {
		/** recursive 递 */
		IntList rest = square(L.rest);
		/** recursive 归 */
		IntList M = new IntList(L.first * L.first, rest);
		return M;
	}
}

public static IntList square(IntList L) { /** Non-destructive, Iterative */
	/** Because Non-destructive, we will have L, SqL. Because Iterative, we will have an extra pointer B.
	SqL and B point to the same IntList.
	Since we will need to return SqL, we will not go down on SqL. 
	B is used to fill in squared values, which are also reflected in SqL since they point to the same IntList. */
	IntList SqL = new IntList(L.first * L.first, null);
	/** pointer to move down the list in SqL */
	IntList B = SqL;
	/** parallel pointer in L */
	L = L.rest;
	while (L != null) {
		B.rest = new IntList(L.first * L.first, null);
		B = B.rest;
		L = L.rest;
	}
	return SqL;
}

