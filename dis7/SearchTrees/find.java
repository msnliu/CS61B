public find(int key) {
	return findHelper(key, head);
}

public findHelper(int key, Node n) {
	if (n == null) {
		return null;
	}
	if (key < n.key) {
		return findHelper(key, n.left);
	} else if (key > n.key) {
		return findHelper(key, n.right);
	} else {
		return n.value;
	}
}