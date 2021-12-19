/** For example, if we had 3 Iterators A, B, and C such that A contained the values
[1, 2, 3], B contained the values [4, 5, 6], and C contained the values [7, 8,
9] */

import java.util.*;
/** create an iterator out of iterators */
public class IteratorOfIterators implements Iterator<Integer> {
	LinkedList<Integer> list;
	/** populate the list in the constructor */
	public IteratorOfIterators(List<Iterator<Integer>> a) {
		list = new LinkedList<>();
		/** if curr.hasNext() is false, i.e., we have run through all the elements in an iterator
		we will just remove it (we don't add it to the end any more!) */
		while (!a.isEmpty) {
			/** get the first iterator */
			Iterator<Integer> curr = a.remove(0);
			if (curr.hasNext()) {
				list.add(curr.next());
				a.add(curr);
			}
		}
	}

	@Override
	public boolean hasNext() {
		return !list.isEmpty();
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return list.removeFirst();
	}

}