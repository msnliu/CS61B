public class SLList {
	private class IntNode {
		public int item;
		public IntNode next;
		public IntNode(int item, IntNode next) {
			this.item = item;
			this.next = next;
		}
	}

	private IntNode first;

	public void addFirst(int x) {
		first = new IntNode(x, first);
	}

	public void insert(int item, int position) {
		if (first == null || position == 0) {
			addFirst(item);
			return;
		}
		IntNode ptr = first;
		while (position > 1 && ptr.next != null) {
			ptr = ptr.next;
			position -= 1;
		}
		ptr.next = new IntNode(item, ptr.next);
	}

	public void removeItem (int x) {
		first = removeItemHelper(x, first);
	}

	/** don't give users the ability to apply to subset of LinkedList */
	/** Hard */
	private IntNode removeItemHelper (int x, IntNode current) {
		if (current == null) {
			return current;
		} else if (current.item == x) {
			return removeItemHelper(x, current.next)
		} else {
			/** keep in the list */
			current.next = removeItemHelper(x, current.next);
		}
		return current;
	}

	/** Hard */
	public void reverse() {
		if (first == null || first.next == null) {
			return;
		}
		/** two pointers: first and ptr which is one node ahead of first */
		IntNode ptr = first.next;
		first.next = null;
		while (ptr != null) {
			/** save */
			IntNode temp = ptr.next;
			ptr.next = first;
			first = ptr;
			ptr = temp;
		}
	}
}