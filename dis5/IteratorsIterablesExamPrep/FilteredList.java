/** after 1. implementing Iterator (access to hasNext and next), and 2. implements Iterable 
Once a class implements Iterable, it has access to for each
FilteredList<Integer> f
for (int i : f) {
}
*/

import java.util.Iterator;
import java.util.Iterable;
import java.util.NoSuchElementException;
public class FilteredList<T> implements Iterable<T> {
	List<T> list;
	Predicate<T> f;
	public FilteredList (List<T> L, Predicate<T> filter) {
		list = L;
		f = filter;
	}

	@Override
	public Iterator<T> iterator() {
		return new FilteredListIterator(list, f);
	}
	/** leave T out after FilteredListIterator */
	private class FilteredListIterator implements Iterator<T> {
		LinkedList<T> list;
		/** populate the list in the constructor */
		public FilteredListIterator(List<T> L, Predicate<T> f) {
			list = new LinkedList<>();
			for (T item : L) {
				if (f.test(item)) {
					list.add(item);
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !list.isEmpty();
		}

		@Override
		public T next() {
			return list.removeFirst();
		}
	}
}
