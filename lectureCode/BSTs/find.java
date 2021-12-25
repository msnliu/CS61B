static BST find(BST T, Key key) {
	if (T == null) {
		return null;
	}
	if (key.equals(T.key)) {
		return T;
	} else if (key < T.key) {
		return find(T.left, key);
	} else {
		return find(T.right, key);
	}
}