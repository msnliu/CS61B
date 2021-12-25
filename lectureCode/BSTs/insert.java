static BST insert(BST T, Key ik) {
	if (T == null) {
		return new BST(ik);
	}
	if (ik < T.key) {
		T.left = insert(T.left, ik);
	} else if (ik > T.key) {
		T.right = insert(T.right, ik);
	} 
	return T;
}