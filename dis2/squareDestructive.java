public static IntList squareDestructive(IntList L) { /** Destructive, Iterative */
	/** pass by reference, copy the bits (memory address) B and L point to the same IntList object */
	/** modify B also modifies L */
	IntList B = L;
	while (B != null) {
		B.first = B.first * B.first;
		B = B.rest;
	}
	return L;
}

public static IntList squareDestructive(IntList L) { /** Destructive, Recursive */
	/** Base Case */
	if (L == null) {
		return L;
	} else {
		L.first = L.first * L.first;
		squareDestructive(L.rest);
	}
	return L;
}