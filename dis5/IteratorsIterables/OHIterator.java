import java.util.Iterator
public class OHIterator implements Iterator<OHRequest> {
	OHRequest curr;
	public OHIterator(OHRequest queue) {
		curr = queue;
	}

	public boolean isGood(String description) {
		return description != null && description.length() > 20;
	}

	@Override
	public boolean hasNext() {
		/** haven't hit the end of our list and haven't hit a good description */
		/** not null and good description: true */
		/** not null and not good description: next (skip one), true 
		curr = curr.next is not a common structure in hasNext (advancing index should be in next), here we used it for skipping bad entries */
		/** null: false */
		while (curr != null && !isGood(curr.description)) {
			curr = curr.next;
		}
		if (curr == null) {
			return false;
		}
		return true;
	}

	@Override
	public OHRequest next() {
		/** a walk through example: 
		good bad good 
		good: true return good next bad
		bad: curr = good true return good next null */
		
		/** 1/ advance to the next item 2/ to return current value */
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		OHRequest r = curr;
		curr = curr.next;
		return r;
	}
}